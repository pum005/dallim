package com.runapp.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.runapp.model.RunDetail;
import com.runapp.model.RunningData;

import java.util.ArrayList;
import java.util.List;


public class RunningViewModel extends ViewModel {

    private final MutableLiveData<Double> heartRate = new MutableLiveData<>();
    private final MutableLiveData<String> elapsedTime = new MutableLiveData<>();
    private final MutableLiveData<String> msPace = new MutableLiveData<>();
    private final MutableLiveData<Double> msSpeed = new MutableLiveData<>();
    private final MutableLiveData<Double> distance = new MutableLiveData<>();
    private final MutableLiveData<Double> StepCount = new MutableLiveData<>();
    private final MutableLiveData<Double> latitude = new MutableLiveData<>();
    private final MutableLiveData<Double> longitude = new MutableLiveData<>();
    private final MutableLiveData<Double> msPaceToSecond = new MutableLiveData<>();
    // 미터값
    private final MutableLiveData<Double> oriDistance = new MutableLiveData<>();
    private final MutableLiveData<Double> totalHeartRate = new MutableLiveData<>();
    private final MutableLiveData<Integer> heartCountTime = new MutableLiveData<>();
    private MutableLiveData<RunningData> runningData = new MutableLiveData<>();
    private final MutableLiveData<List<RunDetail>> runDetailList = new MutableLiveData<>();
    private final MutableLiveData<Long> totalTime = new MutableLiveData<>();
    private final MutableLiveData<Integer> speedCountTime = new MutableLiveData<>();
    private final MutableLiveData<Double> totalSpeed = new MutableLiveData<>();



    public MutableLiveData<RunningData> getRunningData() {
        return runningData;
    }
    public void setHeartRate(double rate) {
        heartRate.setValue(rate);
    }

    public LiveData<Double> getHeartRate() {
        return heartRate;
    }

    public void setDistance(double value) {
        distance.setValue(value);
    }
    public void setMsPace(String value) {
        msPace.setValue(value);
    }

    public LiveData<Double> getDistance() {
        return distance;
    }

    public void setElapsedTime(String time) {
        elapsedTime.setValue(time);
    }

    public LiveData<String> getElapsedTime() {
        return elapsedTime;
    }

    public MutableLiveData<String> getMsPace() {
        return msPace;
    }

    public MutableLiveData<Double> getMsSpeed() {
        return msSpeed;
    }

    public void setRunningData(MutableLiveData<RunningData> runningData) {
        this.runningData = runningData;
    }

    public void setStepCount(double value) {
        this.StepCount.setValue(value);
    }

    public void setMsSpeed(double value) {
        this.msSpeed.setValue(value);
    }

    public LiveData<Double> getStepCount() {
        return StepCount;
    }

    public MutableLiveData<Double> getLatitude() {
        return latitude;
    }

    public MutableLiveData<Double> getLongitude() {
        return longitude;
    }

    public MutableLiveData<Double> getMsPaceToSecond() { return msPaceToSecond; }
    public void setMsPaceToSecond(double second){
        this.msPaceToSecond.setValue(second);
    }
    public void setLatitude(double latitude){
        this.latitude.setValue(latitude);
    }
    public void setLongitude(double longitude){
        this.longitude.setValue(longitude);
    }
    public void setOriDistance(double oriDistance){
        this.oriDistance.setValue(oriDistance);
    }

    public MutableLiveData<Double> getOriDistance() {
        return oriDistance;
    }

    public MutableLiveData<Double> getTotalHeartRate() {
        return totalHeartRate;
    }
    public void setTotalHeartRate(double totalHeartRate){
        this.totalHeartRate.setValue(totalHeartRate);
    }
    public void setHeartCountTime(int heartCountTime){
        this.heartCountTime.setValue(heartCountTime);
    }
    public MutableLiveData<Integer> getHeartCountTime() {
        return heartCountTime;
    }
    public LiveData<List<RunDetail>> getRunDetailList() {
        if (runDetailList.getValue() == null) {
            runDetailList.setValue(new ArrayList<>());
        }
        return runDetailList;
    }
    public void setRunDetailList(List<RunDetail> dataList) {
        runDetailList.setValue(dataList);
    }

    public MutableLiveData<Long> getTotalTime() {
        return totalTime;
    }

    public MutableLiveData<Integer> getSpeedCountTime() {
        return speedCountTime;
    }

    public MutableLiveData<Double> getTotalSpeed() {
        return totalSpeed;
    }
    public void setTotalTime(Long value) {
        totalTime.setValue(value);
    }
    public void setTotalSpeed(Double value) {
        totalSpeed.setValue(value);
    }
    public void setSpeedCountTime(Integer value) {
        speedCountTime.setValue(value);
    }
}