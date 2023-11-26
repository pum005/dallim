package com.b208.dduishu.domain.runningRecordlog.service;

import com.b208.dduishu.domain.runningRecordlog.entity.RunningRecordLog;
import com.b208.dduishu.domain.runningRecordlog.repository.RunningRecordLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RunningRecordLogService {

    private final RunningRecordLogRepository runningRecordLogRepository;

    @Transactional
    public void saveRunningRecordLog(String id, long executeTime) {

        RunningRecordLog build = RunningRecordLog.builder()
                .runningRecordId(id)
                .executeTime(executeTime)
                .createdAt(LocalDateTime.now())
                .build();

        runningRecordLogRepository.save(build);
    }
}
