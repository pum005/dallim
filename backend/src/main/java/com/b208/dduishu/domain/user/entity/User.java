package com.b208.dduishu.domain.user.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.b208.dduishu.domain.character.entity.Character;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Indexed
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(nullable = false)
    private String accountType;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, unique = true)
    @Field
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Character> characterList = new ArrayList<>();

    private int cumulativeRunningDay;
    private double averageSpeed;
    private double cumulativeDistance;
    private int cumulativeRunningTime;
    private int cumulativeCalorie;
    private int point;
    @Column(nullable = false)
    private String privateAccess;
    @Column(nullable = false)
    private String accessToken;
    @Enumerated(EnumType.STRING)
    private UserState state;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_level_id")
    private UserLevel userLevel;
    @CreatedDate
    private LocalDateTime registDate;
    @LastModifiedDate
    private LocalDateTime lastLoginDate;
    private boolean isAdmin;

    public User() {
    }

    @Builder
    public User(Long userId, boolean isAdmin, UserState state, UserLevel userLevel, String accountType, String email, String nickname, int cumulativeRunningDay, float averageSpeed, float cumulativeDistance,int cumulativeRunningTime,int cumulativeCalorie,int point, String privateAccess, LocalDateTime registDate, LocalDateTime lastLoginDate, String accessToken) {
        this.userId = userId;
        this.isAdmin = isAdmin;
        this.state = state;
        this.accountType = accountType;
        this.email = email;
        this.nickname = nickname;
        this.cumulativeRunningDay = cumulativeRunningDay;
        this.averageSpeed = averageSpeed;
        this.cumulativeDistance = cumulativeDistance;
        this.cumulativeRunningTime = cumulativeRunningTime;
        this.cumulativeCalorie = cumulativeCalorie;
        this.point = point;
        this.privateAccess = privateAccess;
        this.accessToken = accessToken;
        this.registDate = registDate;
        this.lastLoginDate = lastLoginDate;
        this.userLevel = userLevel;
    }

    public void updateNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void updateLastLoginDate() {
        this.lastLoginDate = LocalDateTime.now();
    }

    public void updateAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public void updatePrivateAccessToken(String privateAccessToken){
        this.privateAccess = privateAccessToken;
    }
    public void updateAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public void addPoint(double point) {
        this.point += point;
    }
    public void addCumulativeRunningDay(int day) {
        this.cumulativeRunningDay += day;
    }
    public void addCumulativeDistance(double distance){
        this.cumulativeDistance += distance;
    }

    public void addCumulativeRunningTime(int time){
        this.cumulativeRunningTime += time;
    }

    public void addCumulativeCalorie(int calorie){
        this.cumulativeCalorie += calorie;
    }

    public void reducePoint(int point) {
        this.point -= point;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

}
