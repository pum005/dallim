package com.b208.dduishu.util;


import com.b208.dduishu.domain.character.entity.Character;
import com.b208.dduishu.domain.characterInfo.dto.CharacterName;
import com.b208.dduishu.domain.planet.entity.Planet;
import com.b208.dduishu.domain.planet.entity.PlanetName;
import com.b208.dduishu.domain.user.entity.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Util {
    private static final Map<CharacterName, Integer> characterNameToValue = Map.of(
            CharacterName.RABBIT, 0,
            CharacterName.Penguin, 1,
            CharacterName.Panda, 2,
            CharacterName.Chicken, 3
    );

    private static final Map<PlanetName, Integer> planetNameToValue = Map.of(
            PlanetName.BLACK, 0,
            PlanetName.YELLOW, 1,
            PlanetName.BLUE, 2,
            PlanetName.PUPPLE, 3,
            PlanetName.RED, 4
    );

    public static int getCharacterIndexByCharacter(Character character) {
        if (character.getCharacterInfo().getName().equals(CharacterName.RABBIT)) {
            return 0;
        } else if (character.getCharacterInfo().getName().equals(CharacterName.Penguin)) {
            return 1;
        } else if (character.getCharacterInfo().getName().equals(CharacterName.Panda)) {
            return 2;
        } else if (character.getCharacterInfo().getName().equals(CharacterName.Chicken)) {
            return 3;
        }
        return 0;
    }

    public static int getCharacterIndexByCharacterName(CharacterName name) {

        if (name.equals(CharacterName.RABBIT)) {
            return 0;
        } else if (name.equals(CharacterName.Penguin)) {
            return 1;
        } else if (name.equals(CharacterName.Panda)) {
            return 2;
        } else if (name.equals(CharacterName.Chicken)) {
            return 3;
        }
        return 0;
    }

    public static int getMainPlanetIndex(Planet planet) {
        if (planet.getPlanetInfo().getName().equals(PlanetName.BLACK)) {
            return 0;
        } else if (planet.getPlanetInfo().getName().equals(PlanetName.YELLOW)) {
            return 1;
        } else if (planet.getPlanetInfo().getName().equals(PlanetName.BLUE)) {
            return 2;
        } else if (planet.getPlanetInfo().getName().equals(PlanetName.PUPPLE)) {
            return 3;
        } else if (planet.getPlanetInfo().getName().equals(PlanetName.RED)) {
            return 4;
        }
        return 0;
    }

    public static int getMainPlanetIndexByName(PlanetName planetName) {
        if (planetName.equals(PlanetName.BLACK)) {
            return 0;
        } else if (planetName.equals(PlanetName.YELLOW)) {
            return 1;
        } else if (planetName.equals(PlanetName.BLUE)) {
            return 2;
        } else if (planetName.equals(PlanetName.PUPPLE)) {
            return 3;
        } else if (planetName.equals(PlanetName.RED)) {
            return 4;
        }
        return 0;
    }

    public static int getCharacterIndexByUser(User user) {
        return user.getCharacterList().stream()
                .filter(Character::isMainCharacter)
                .map(character -> characterNameToValue.getOrDefault(character.getCharacterInfo().getName(), 0))
                .findFirst()
                .orElse(0);
    }

    public static int getPlanetIndexByPlanets(List<Planet> planets) {
        return planets.stream()
                .filter(Planet::isMainPlanet)
                .map(planet -> planetNameToValue.getOrDefault(planet.getPlanetInfo().getName(), 0))
                .findFirst()
                .orElse(0);
    }

    public static CharacterName getCharacterNameByIndex(int characterIndex) {
        if (characterIndex == 0) {
            return CharacterName.RABBIT;
        } else if (characterIndex == 1) {
            return CharacterName.Penguin;
        } else if (characterIndex == 2) {
            return CharacterName.Panda;
        } else if (characterIndex == 3) {
            return CharacterName.Chicken;
        }
        return CharacterName.RABBIT;
    }

    public static PlanetName getPlanetNameByIndex(int planetIndex) {
        if (planetIndex == 0) {
            return PlanetName.BLACK;
        } else if (planetIndex == 1) {
            return PlanetName.YELLOW;
        } else if (planetIndex == 2) {
            return PlanetName.BLUE;
        } else if (planetIndex == 3) {
            return PlanetName.PUPPLE;
        }else if (planetIndex == 4) {
            return PlanetName.RED;
        }
        return PlanetName.BLACK;
    }

    public static int getEvolutionStage(int level) {
        if (level < 10) {
            return 0;
        } else {
            return 1;
        }
    }

}
