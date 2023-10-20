package com.b208.dduishu.domain.runningMate.service;

import com.b208.dduishu.domain.runningMate.document.RunningMate;
import com.b208.dduishu.domain.runningMate.dto.request.CreateRunningMateInfo;
import com.b208.dduishu.domain.runningMate.dto.request.RunningMateInfo;
import com.b208.dduishu.domain.runningMate.repository.RunningMateRepository;
import com.b208.dduishu.domain.runningRecord.document.RunningRecord;
import com.b208.dduishu.domain.runningRecord.repository.RunningRecordRepository;
import com.b208.dduishu.domain.user.GetUser;
import com.b208.dduishu.domain.user.entity.User;
import com.b208.dduishu.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RunningMateService {

    private final UserRepository userRepository;
    private final GetUser getUser;
    private final RunningRecordRepository runningRecordRepository;
    private final RunningMateRepository runningMateRepository;

    public void createRunningMate(CreateRunningMateInfo req) {
        User user = getUser.getUser();

        RunningRecord record = runningRecordRepository.findById((new ObjectId(req.getObjectId()))).orElseThrow(() -> {
            throw new NullPointerException();
        });

        RunningMate runningMate = RunningMate.builder()
                .user(user)
                .rivalRecord(record)
                .build();

        runningMateRepository.save(runningMate);
    }
}
