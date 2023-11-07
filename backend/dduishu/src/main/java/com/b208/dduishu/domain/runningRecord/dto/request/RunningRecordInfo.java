package com.b208.dduishu.domain.runningRecord.dto.request;

import com.b208.dduishu.domain.character.dto.request.CharacterOverview;
import com.b208.dduishu.domain.planet.entity.Planet;
import com.b208.dduishu.domain.runningRecord.document.*;
import com.b208.dduishu.domain.runningRecord.dto.CharacterRecordInfo;
import com.b208.dduishu.domain.user.dto.request.UserInfo;
import com.b208.dduishu.domain.user.entity.User;
import com.b208.dduishu.domain.character.entity.Character;
import lombok.Data;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Data
public class RunningRecordInfo {

    private static final double SLOW_WALK_THRESHOLD = (double) 6000 / 3600;
    private static final double FAST_WALK_THRESHOLD = (double) 9000 / 3600;

    private static final double HEART_RATE_SECTION_1 = 136;
    private static final double HEART_RATE_SECTION_2 = 150;
    private static final double HEART_RATE_SECTION_3 = 162;
    private static final double HEART_RATE_SECTION_4 = 175;


    private Long userId;
    private Date date;
    private String formattedDate;
    private Long characterId;
    private RunningType type;
    private ObjectId rivalRecordId;
    private int totalTime;
    private double totalDistance;
    private double averageSpeed;
    private double averageHeartRate;
    private double averagePace;
    private double stepCount;
    //러닝 데이터 받아오기
    // 운동일시, 총 시간, 평균 속력, 이동 거리, 평균 심박수
    // + 위치정보
    // + 생체정보 +a
    private List<RunningRecordOverallInfo> runningRecordInfos;

    public RunningRecord toRunningRecord(User user, Planet planet, Character character, RunningRecord rivalRecord){

        UserInfo userInfo = new UserInfo(user);
        CharacterRecordInfo characterInfo = new CharacterRecordInfo(character, planet);
        RivalRunningRecordInfo rivalRunningRecordInfo = null;
        if (rivalRecord != null) {
            rivalRunningRecordInfo = new RivalRunningRecordInfo(rivalRecord);
        }

//        String location = getLocation();
        List<Integer> secondPerSpeed = getSecondPerSpeed(this.runningRecordInfos);
        PaceInfo pace = getPaceInfo(this.runningRecordInfos);
        HeartRateInfo heartRate = getHeartRateInfo(this.runningRecordInfos);


        RunningRecord build = RunningRecord.builder()
                .user(userInfo)
                .pace(pace)
                .location("서울, 석촌호수")
                .heartRate(heartRate)
                .secondPerSpeed(secondPerSpeed)
                .character(characterInfo)
                .type(this.type)
                .rivalRecord(rivalRunningRecordInfo)
                .runningRecordInfos(this.runningRecordInfos)
                .totalTime(this.totalTime)
                .totalDistance(this.totalDistance)
                .averageSpeed(this.averageSpeed)
                .averagePace(this.averagePace)
                .averageHeartRate(this.averageHeartRate)
                .createdAt(this.date)
                .formattedDate(this.formattedDate)
                .stepCount(this.stepCount)
                .build();

        return build;
    }

    private HeartRateInfo getHeartRateInfo(List<RunningRecordOverallInfo> runningRecordInfos) {
        Integer[] heartRate = {0, 0, 0, 0, 0}; // 각 속도 범주에 대한 초를 계산하기 위한 배열

        runningRecordInfos.forEach(record -> {
            double heartRatePerMinute = record.getHeartRate(); // getSpeed()가 km/h 단위의 속도를 반환한다고 가정

            if (heartRatePerMinute < HEART_RATE_SECTION_1) {
                heartRate[0]++;
            } else if (heartRatePerMinute < HEART_RATE_SECTION_2) {
                heartRate[1]++;
            } else if (heartRatePerMinute < HEART_RATE_SECTION_3) {
                heartRate[2]++;
            } else if (heartRatePerMinute < HEART_RATE_SECTION_4) {
                heartRate[3]++;
            } else {
                heartRate[4]++;
            }
        });

        HeartRateInfo heartRateInfo = new HeartRateInfo();

        heartRateInfo.setAverageHeartRate(Arrays.stream(heartRate).mapToInt(Integer::intValue).average().orElse(0));
        heartRateInfo.setMaxHeartRate(Arrays.stream(heartRate).mapToInt(Integer::intValue).max().orElse(0));
        heartRateInfo.setSecondPerHeartRateSection(Arrays.asList(heartRate));

        return heartRateInfo;
    }

    private PaceInfo getPaceInfo(List<RunningRecordOverallInfo> runningRecordInfos) {
        List<PaceSectionInfo> sectionPaces = new ArrayList<>();
        PaceSectionInfo currentSection = new PaceSectionInfo();
        double nextSectionDistance = 1000.0; // 다음 구간까지의 거리

        for (RunningRecordOverallInfo record : runningRecordInfos) {
            double currentDistance = record.getDistance();

            if (currentDistance >= nextSectionDistance) {
                // 현재 레코드가 새로운 구간의 끝을 나타냄
                currentSection.setFinishTime(record.getSecond());
                currentSection.setPace(currentSection.getFinishTime() - currentSection.getStartTime());
                sectionPaces.add(currentSection);

                // 새로운 구간을 시작
                currentSection = new PaceSectionInfo();
                currentSection.setStartTime(record.getSecond());

                // 다음 구간 거리 업데이트
                nextSectionDistance += 1000.0;
            }
        }

        // 마지막 구간 추가 (스트림이 끝났을 때 마지막 구간을 처리)
        if (currentSection.getStartTime() != 0) {
            RunningRecordOverallInfo lastRecord = runningRecordInfos.get(runningRecordInfos.size() - 1);

            currentSection.setFinishTime(lastRecord.getSecond());

            double actualDistance = lastRecord.getDistance() - (nextSectionDistance - 1000.0);

            int timeSpentInSeconds = currentSection.getFinishTime() - currentSection.getStartTime();

            double paceInSecondsPerKm = (timeSpentInSeconds / actualDistance) * 1000.0;

            BigDecimal pace = new BigDecimal(paceInSecondsPerKm);
            pace = pace.setScale(2, RoundingMode.HALF_UP); // 소수점 두 자리까지 반올림
            currentSection.setPace(pace.doubleValue()); // double 값으로 설정

            sectionPaces.add(currentSection);
        }


        PaceInfo paceInfo = new PaceInfo();
        double averagePace = sectionPaces.stream()
                .mapToDouble(PaceSectionInfo::getPace)
                .average()
                .orElse(0);
        BigDecimal formattedAverage = new BigDecimal(averagePace).setScale(2, RoundingMode.HALF_UP);

        paceInfo.setAveragePace(formattedAverage.doubleValue());
        paceInfo.setMaxPace(sectionPaces.stream().mapToDouble(PaceSectionInfo::getPace).max().orElse(0));
        paceInfo.setSection(sectionPaces);

        return paceInfo;
    }

    private List<Integer> getSecondPerSpeed(List<RunningRecordOverallInfo> runningRecordInfos) {
        Integer[] secondPerSpeed = {0, 0, 0}; // 각 속도 범주에 대한 초를 계산하기 위한 배열

        runningRecordInfos.forEach(record -> {
            double speedKmH = record.getSpeed(); // getSpeed()가 km/h 단위의 속도를 반환한다고 가정

            if (speedKmH < SLOW_WALK_THRESHOLD) {
                secondPerSpeed[0]++;
            } else if (speedKmH < FAST_WALK_THRESHOLD) {
                secondPerSpeed[1]++;
            } else {
                secondPerSpeed[2]++;
            }
        });
        return Arrays.asList(secondPerSpeed);
    }
}