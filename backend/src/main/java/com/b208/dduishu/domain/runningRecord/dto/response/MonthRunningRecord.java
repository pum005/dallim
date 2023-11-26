package com.b208.dduishu.domain.runningRecord.dto.response;

import com.b208.dduishu.domain.character.entity.Character;
import com.b208.dduishu.domain.runningRecord.dto.request.RunningRecordOverview;
import com.b208.dduishu.domain.user.entity.User;
import com.b208.dduishu.util.Util;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class MonthRunningRecord {
    private int year;
    private int month;
    private String runningMateNickName;
    private int runningMateCharacterIndex;
    private int evolutionStage;
    private int runningMateLevel;
    private int totalCount;
    private double totalDistance;
    private int totalTime;
    private List<RunningRecordOverview> records;

    @Builder
    public MonthRunningRecord(int year, int month, User user, Character runningMateCharacter, int totalCount, double totalDistance, int totalTime, List<RunningRecordOverview> records) {
        this.year = year;
        this.month = month;
        if (user != null) {
            this.runningMateNickName = user.getNickname();
            this.runningMateCharacterIndex = Util.getCharacterIndexByUser(user);
            this.runningMateLevel = user.getUserLevel().getLevel();
        }
        if (runningMateCharacter != null) {
            this.evolutionStage = Util.getEvolutionStage(runningMateCharacter.getCharacterLevel().getLevel());
        }
        this.totalCount = totalCount;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
        this.records = records;
    }
}
