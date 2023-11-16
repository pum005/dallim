package com.dallim.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Chronometer;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.dallim.R;
import com.dallim.activity.RunningActivity;
import com.dallim.database.RunningDataConverters;
import com.dallim.model.RunDetail;
import com.dallim.util.Conversion;
import com.dallim.util.MyApplication;
import com.dallim.util.TtsUtil;
import com.dallim.view.RunningMateRecordViewModel;
import com.dallim.view.RunningViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TimerService extends Service {
    private Handler timerHandler;
    private Runnable timerRunnable;
    private long startTime;
    private static final int NOTIFICATION_ID = 10;
    private static final String CHANNEL_ID = "RunningService";
    public static final String TIMER_BR = "com.runapp.service.timerbroadcast";
    private RunningViewModel runningViewModel;
    private RunningMateRecordViewModel runningMateRecordViewModel;
    private int speedCountTime = 0;
    private double totalSpeed = 0;
    private List<Double> mateRunningDetail = new ArrayList<>();
    private Double lastDistance;
    private Double kmLastDistance;
    private boolean check = false;
    private int seconds = 0;
    private Conversion conversion = new Conversion();
    private boolean overTime = false;
    private long curTime = 0;
    private TtsUtil ttsUtil;
    private boolean tts5kmCheck = false;
    private boolean tts3kmCheck = false;
    private boolean tts1kmCheck = false;
    private boolean tts0_5kmCheck = false;
    private boolean tts0_1kmCheck = false;
    private boolean myTts0_5kmCheck = false;
    private double lastTtsDistance = 0.0;


    @SuppressLint("InvalidWakeLockTag")
    @Override
    public void onCreate() {
        super.onCreate();
        ttsUtil = new TtsUtil(getApplicationContext());

        timerHandler = new Handler();
        runningViewModel = new ViewModelProvider((MyApplication) getApplication()).get(RunningViewModel.class);
        // check가 true면 함께달리기
        check = runningViewModel.getPairCheck().getValue();
        if (check){
            runningMateRecordViewModel = new ViewModelProvider((MyApplication) getApplication()).get(RunningMateRecordViewModel.class);
            mateRunningDetail = runningMateRecordViewModel.getMateRecord().getValue().getDistance();
            lastDistance = mateRunningDetail.get(mateRunningDetail.size() - 1);
            kmLastDistance = conversion.mToKM(lastDistance);
        }
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "달림",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    private Notification getNotification() {

        Intent notificationIntent = new Intent(this, RunningActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setContentTitle("기록중")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentText("당신의 달리기를 기록하고 있어요")
                .setOngoing(true);
        return builder.build();
    }

    public void startTimer() {
        curTime = System.nanoTime();
        startTime = curTime;
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long elapsedTime = curTime - startTime;
                updateRunDetailList(elapsedTime);
                Intent intent = new Intent(TIMER_BR);
                intent.putExtra("elapsedTime", elapsedTime);
                sendBroadcast(intent);
                // 1초마다 현재 Runnable을 다시 실행하도록 예약
                timerHandler.postDelayed(this, 1000);
                curTime++;
            }
        };
        timerHandler.post(timerRunnable);
    }

    private void updateRunDetailList(long elapsedTime) {
        seconds = (int) elapsedTime;

        // 함께달리기인 경우에만 거리 차이 계산
        if (check){
            if (runningViewModel.getDistance().getValue() != null){
                double curDistance = kmLastDistance - runningViewModel.getDistance().getValue();
                curDistance = Math.round(curDistance * 100.0) / 100.0;
                // tts 알림
                if (curDistance == 5 && !tts5kmCheck){
                    ttsUtil.speak("현재 남은 목표 거리는 5km 입니다.");
                    tts5kmCheck = true;
                } else if (curDistance == 3 && !tts3kmCheck){
                    ttsUtil.speak("현재 남은 목표 거리는 3km 입니다.");
                    tts3kmCheck = true;
                } else if (curDistance == 1 && !tts1kmCheck){
                    ttsUtil.speak("현재 남은 목표 거리는 1km 입니다.");
                    tts1kmCheck = true;
                } else if (curDistance <= 0.5 && !tts0_5kmCheck){
                    ttsUtil.speak("현재 남은 목표 거리는 500m 입니다.");
                    tts0_5kmCheck = true;
                } else if (curDistance <= 0.1 && !tts0_1kmCheck){
                    ttsUtil.speak("현재 남은 목표 거리는 100m 입니다.");
                    tts0_1kmCheck = true;
                }
            }
            // 아직 상대방 시간보다 낮은 경우
            if(!overTime){
                if(seconds >= mateRunningDetail.size() - 1){
                    Log.e("상태", "상대 시간 초과");
                    overTime = true;
                }
                if (runningViewModel.getOriDistance().getValue() != null && runningViewModel.getOriDistance().getValue() != 0) {
                    Double mateDistance = mateRunningDetail.get(seconds);
                    Double curDistance = runningViewModel.getOriDistance().getValue();
                    runningViewModel.setDistanceDifference(Math.round((curDistance - mateDistance) * 10) / 10.0);

                    // 이긴 경우
                    if(curDistance >= lastDistance){
                        Log.e("상태", "이김");
                        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
                        Intent intent = new Intent(TIMER_BR);
                        intent.putExtra("finish_activity", true);
                        localBroadcastManager.sendBroadcast(intent);
                    }
                }
            }
            // 상대방 시간 초과한 경우
            else{
                Double curDistance = runningViewModel.getOriDistance().getValue();
                // 이긴 경우
                if(curDistance >= lastDistance){
                    Log.e("상태", "이김");
                    LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
                    Intent intent = new Intent(TIMER_BR);
                    intent.putExtra("finish_activity", true);
                    localBroadcastManager.sendBroadcast(intent);
                }
            }
        } else {
            if (runningViewModel.getOriDistance().getValue() != null){
                double tempDistance = runningViewModel.getOriDistance().getValue();
                if (tempDistance >= 500 && !myTts0_5kmCheck) {
                    myTts0_5kmCheck = true;
                    ttsUtil.speak("현재 달린 거리는 500m 입니다.");
                } else if (tempDistance >= 1000  && tempDistance >= lastTtsDistance + 1000){
                    lastTtsDistance = tempDistance;
                    tempDistance = Math.round(tempDistance/1000);
                    ttsUtil.speak("현재 달린 거리는 " + String.valueOf(tempDistance)+ "km 입니다.");
                }
            }
        }

        runningViewModel.setTotalTime((long) seconds);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        RunDetail detail = new RunDetail();
        if (runningViewModel.getOriDistance().getValue() != null) {
            detail.setDistance(runningViewModel.getOriDistance().getValue());
        }
        if (runningViewModel.getMsPaceToSecond().getValue() != null) {
            detail.setPace(runningViewModel.getMsPaceToSecond().getValue());
        }
        if (runningViewModel.getMsSpeed().getValue() != null) {
            double speed = runningViewModel.getMsSpeed().getValue();
            detail.setSpeed(speed);
            if (speed <= 0.2) {
                detail.setState("STOP");
            } else if (speed > 0.2 && speed <= 1.5) {
                detail.setState("WALK");
            } else if (speed > 1.5 && speed <= 3.0) {
                detail.setState("RACEWALK");
            } else {
                detail.setState("RUN");
            }
        }
        if (runningViewModel.getHeartRate().getValue() != null) {
            detail.setHeartRate(runningViewModel.getHeartRate().getValue());
        }
        detail.setSecond(elapsedTime);

        if (runningViewModel.getMsSpeed().getValue() != null) {
            speedCountTime++;
            runningViewModel.setSpeedCountTime(speedCountTime);
            totalSpeed += runningViewModel.getMsSpeed().getValue();
            runningViewModel.setTotalSpeed(totalSpeed);
        }
        List<RunDetail> runDetails = runningViewModel.getRunDetailList().getValue();
        if (runDetails == null) {
            runDetails = new ArrayList<>();
        }
        runDetails.add(detail);
        runningViewModel.setRunDetailList(runDetails);

        runningViewModel.setElapsedTime(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
    }

    public void stopTimer() {
        if (timerHandler != null && timerRunnable != null) {
            timerHandler.removeCallbacks(timerRunnable);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.hasExtra("running_mate_record")) {
            String runningMateRecordJson = intent.getStringExtra("running_mate_record");
            Log.e("출력", runningMateRecordJson);
            mateRunningDetail = RunningDataConverters.doubleFromString(runningMateRecordJson);
        }
        Log.d("알림 로그", "들어옴");
        startForeground(NOTIFICATION_ID, getNotification());
        startTimer();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        stopTimer();
        ttsUtil.stop();
        super.onDestroy();
        stopForeground(true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
