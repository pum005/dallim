import { characterData } from '@/recoil/data/CharacterData';
import * as S from './RankInfoBox.styles';
import { meterToKMOrMeter } from '@/recoil/data/RunningData';
import { useState, useEffect } from 'react';
import LinearGradient from 'react-native-linear-gradient';
import RadialGradient from 'react-native-radial-gradient';
import { colors } from '../common/globalStyles';
import { LevelData } from '@/recoil/data/LevelData';

type RankInfoBoxProps = {
  userId: number;
  rank: number;
  characterIndex: number;
  evolutionStage: number;
  nickname: string;
  cumulativeDistance: number;
  level: number;
  follower: boolean;
  navigation: any;
};

function RankInfoBox({
  userId,
  rank,
  characterIndex,
  evolutionStage,
  nickname,
  cumulativeDistance,
  level,
  follower,
  navigation,
}: RankInfoBoxProps) {
  const displayDistance = Math.floor(cumulativeDistance);
  const selectedCharacter =
    characterData[characterIndex].Evolutions[evolutionStage].Main;
  const [startColor, setStartColor] = useState<string>(
    colors.buttonColor.firstDepth,
  );
  const [endColor, setEndColor] = useState<string>(
    colors.buttonColor.firstDepth,
  );

  useEffect(() => {
    if (rank === 1) {
      setStartColor(colors.all.goldMedal.linear.start);
      setEndColor(colors.all.goldMedal.linear.end);
    } else if (rank === 2) {
      setStartColor(colors.all.silverMedal.linear.start);
      setEndColor(colors.all.silverMedal.linear.end);
    } else if (rank === 3) {
      setStartColor(colors.all.bronzeMedal.linear.start);
      setEndColor(colors.all.bronzeMedal.linear.end);
    } else {
      setStartColor(colors.buttonColor.firstDepth);
      setEndColor(colors.buttonColor.firstDepth);
    }
  }, [rank]);

  function getLevelImageIndex(userLevel: number) {
    if (userLevel <= 10) return 0;
    if (userLevel <= 20) return 1;
    if (userLevel <= 30) return 2;
    if (userLevel <= 40) return 3;
    return 4; // 50 이하인 경우
  }
  const LevelImage = LevelData[getLevelImageIndex(level)].Base;


  return (
    <S.Container>
      <S.BoxShadow
        distance={4}
        startColor="rgba(0, 0, 0, 0.2)"
        endColor="rgba(0, 0, 0, 0.2)"
        offset={[1, 4]}>
        <S.Box rank={rank}>
          <LinearGradient
            start={{ x: 1, y: 0 }}
            end={{ x: 0.5, y: 1 }}
            colors={[startColor, endColor]}
            style={{
              height: '100%',
              width: '100%',
              borderRadius: 15,
              flexDirection: 'row',
            }}>
            <S.Left>
              <S.RankText rank={rank}>{rank}</S.RankText>
            </S.Left>
            <S.Middle
              onPress={() =>
                navigation.navigate('UserDetailStack', { userId: userId })
              }>
              <S.Header>
                <S.DistanceText>
                  {meterToKMOrMeter(displayDistance)}
                </S.DistanceText>
              </S.Header>
              <S.Body>
                <S.NickNameText>{nickname}</S.NickNameText>
                <S.LevelBox>
                  <S.LevelImage
                    source={LevelImage} resizeMode='contain' />
                </S.LevelBox>
                <S.LevelText>Lv. {level}</S.LevelText>
              </S.Body>
            </S.Middle>
            <S.right>
              <S.ImageBox>
                <S.CharacterImage
                  source={selectedCharacter}
                  resizeMode="contain"
                />
              </S.ImageBox>
            </S.right>
          </LinearGradient>
        </S.Box>
      </S.BoxShadow>
    </S.Container>
  );
}

export default RankInfoBox;
