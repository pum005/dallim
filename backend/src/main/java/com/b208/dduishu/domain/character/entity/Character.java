package com.b208.dduishu.domain.character.entity;

import com.b208.dduishu.domain.characterInfo.entity.CharacterInfo;
import com.b208.dduishu.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_info_id")
    private CharacterInfo characterInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_level_id")
    private CharacterLevel characterLevel;

    private boolean isMainCharacter;

    @Builder
    public Character(Long id, User user, CharacterInfo characterInfo, CharacterLevel characterLevel, boolean isMainCharacter) {
        this.id = id;
        this.user = user;
        this.characterInfo = characterInfo;
        this.characterLevel = characterLevel;
        this.isMainCharacter = isMainCharacter;
    }

    public Character() {}

    public void setMainCharacter(boolean isMainCharacter) {
        this.isMainCharacter = isMainCharacter;
    }

    public void setCharacterLevel(CharacterLevel characterLevel) {
        this.characterLevel = characterLevel;
    }

}
