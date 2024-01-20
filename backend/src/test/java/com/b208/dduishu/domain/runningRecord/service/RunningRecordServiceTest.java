package com.b208.dduishu.domain.runningRecord.service;

import com.b208.dduishu.domain.runningRecord.document.*;
import com.b208.dduishu.domain.runningRecord.dto.request.RunningRecordInfo;
import com.b208.dduishu.domain.runningRecord.dto.request.RunningRecordOverallInfo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

@SpringBootTest
class RunningRecordServiceTest {
    @Autowired
    private RunningRecordService runningRecordService;

    @BeforeEach
    public void setup() {
        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(6L, null, List.of(new SimpleGrantedAuthority("USER")));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

//     @Test
//     void saveRunning() {
//         int size = 3600;
//         List<RunningRecordOverallInfo> runningRecordOverallInfos = new ArrayList<>();
//         Random random = new Random();

//         double distance = 0.0;
//         double lastSpeed = 2; // 초기 속도 설정
//         double lastHeartRate = 100;

//         for (int i = 0; i < size; i++) {
//             // 속도 변경을 더 부드럽게 만들기 위해 이전 속도에 랜덤 값을 일부만 혼합
//             double randomChange = (random.nextDouble() - 0.5) * 0.1; // -0.05 ~ 0.05 사이의 변화량
//             BigDecimal formattedRandomDistance = new BigDecimal(randomChange).setScale(2, RoundingMode.HALF_UP);
//             double distanceChange = formattedRandomDistance.doubleValue();
//             double heartRateChange = randomChange * 20; // 속도 변화량에 20을 곱하여 심박수 변화량을 결정

//             double speed = lastSpeed + distanceChange;
//             double heartRate = lastHeartRate + heartRateChange;

//             // 속도가 0 이하가 되지 않도록 하고, 5를 초과하지 않도록 조정
//             speed = Math.max(0, Math.min(5, speed));
//             heartRate = Math.max(60, Math.min(180, heartRate));

//             lastSpeed = speed; // 다음 루프를 위해 마지막 속도 업데이트
//             lastHeartRate = heartRate;

//             distance += speed;

//             BigDecimal formattedDistance = new BigDecimal(distance).setScale(2, RoundingMode.HALF_UP);

//             double pace = 0.0;
//             if ( speed != 0) {
//                 pace = (1000 / speed);
//             }

//             BigDecimal formattedPace = new BigDecimal(pace).setScale(2, RoundingMode.HALF_UP);
//             double formattedPaceVal = formattedPace.doubleValue();

//             RunningRecordOverallInfo info = RunningRecordOverallInfo.builder()
//                     .second(i)
//                     .heartRate(heartRate)
//                     .pace(formattedPaceVal)
//                     .speed(speed)
//                     .distance(formattedDistance.doubleValue())
//                     .build();
//             runningRecordOverallInfos.add(info);
//         }

//         RunningRecordOverallInfo lastRecord = runningRecordOverallInfos.get(runningRecordOverallInfos.size()-1);

//         double averageSpeed = runningRecordOverallInfos.stream().mapToDouble(RunningRecordOverallInfo::getSpeed).average().orElse(0.0);
//         double averageHeartRate = runningRecordOverallInfos.stream().mapToDouble(RunningRecordOverallInfo::getHeartRate).average().orElse(0.0);
//         double averagePace = runningRecordOverallInfos.stream().mapToDouble(RunningRecordOverallInfo::getPace).average().orElse(0.0);

//         BigDecimal formattedAverageSpeed = new BigDecimal(averageSpeed).setScale(2, RoundingMode.HALF_UP);
//         BigDecimal formattedAverageHeartRate = new BigDecimal(averageHeartRate).setScale(2, RoundingMode.HALF_UP);
//         BigDecimal formattedAveragePace = new BigDecimal(averagePace).setScale(2, RoundingMode.HALF_UP);

//         RunningRecordInfo build = RunningRecordInfo.builder()
//                 .userId(6L)
//                 .characterIndex(1L)
//                 .watchOrMobile(WatchOrMobile.WATCH)
//                 .date(LocalDateTime.now())
//                 .formattedDate("11월 11일 (토)")
//                 .type(RunningType.PAIR)
//                 .rivalRecordId(new ObjectId("654f9c71ba08aa7460ea2503"))
//                 .totalTime(size)
//                 .totalDistance(lastRecord.getDistance())
//                 .averageSpeed(formattedAverageSpeed.doubleValue())
//                 .averageHeartRate(formattedAverageHeartRate.doubleValue())
//                 .averagePace(formattedAveragePace.doubleValue())
//                 .initLatitude(36.3551347)
//                 .initLongitude(127.2986507)
//                 .winOrLose(WinOrLose.GIVEUP)
//                 .runningRecordInfos(runningRecordOverallInfos)
//                 .build();

// //        runningRecordService.saveRunningRecord(build);
//     }
}
