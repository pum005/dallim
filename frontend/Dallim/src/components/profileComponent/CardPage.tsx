import * as S from './CardPage.styles';
import {planetData} from '@/recoil/PlanetData';
import {characterData} from '@/recoil/CharacterData';

// Icon
import DistIcon from '@/assets/icons/DistIcon.png';
import TimeIcon from '@/assets/icons/TimeIcon.png';
import SpeedIcon from '@/assets/icons/SpeedIcon.png';
import ClearIcon from '@/assets/icons/ClearIcon.png';
import {meterToKMOrMeter} from '@/recoil/RunningData';

interface ICardPage {
  item: {
    id: string;
    characterIndex: number;
    evolutionStage: number;
    planetIndex: number;
    level: number;
    nickName: string;
    totalDistance: number;
    totalTime: number;
    createdAt: string;
    clear: boolean;
  };
  // style: ViewStyle;
}

export default function CardPage({item}: ICardPage) {
  const selectedCharacter = characterData[item.characterIndex];
  const selectedCharacterLevelData =
    selectedCharacter.evolutions[item.evolutionStage];

  const getFormattedDate = (isoDate: string) => {
    const date = new Date(isoDate);
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();

    return `${year}년 ${month}월 ${day}일`;
  };
  const formattedDate = getFormattedDate(item.createdAt);

  // 속력
  const speed = item.totalDistance / item.totalDistance;

  // 시간 변환 함수 ( 60분 이상 경우에 )
  const formatTime = (totalMinutes: number) => {
    if (totalMinutes >= 60) {
      const hours = Math.floor(totalMinutes / 60);
      const minutes = totalMinutes % 60;
      return `${hours}시간 ${minutes}분`;
    } else {
      return `${totalMinutes}분`;
    }
  };

  return (
    // <S.PageItem style={style}>
    <S.PageItem>
      <S.ImageWrapper>
        <S.StyledImage
          source={planetData[item.planetIndex].Cardtwo}
          resizeMode="cover">
          <S.Header>
            <S.HeaderLeft>
              <S.DateText>{formattedDate}</S.DateText>
            </S.HeaderLeft>
            <S.HeaderRight>
              <S.LevelText>Lv. {item.level}</S.LevelText>
            </S.HeaderRight>
          </S.Header>
          <S.Body>
            <S.NicknameText>{item.nickName}</S.NicknameText>
          </S.Body>
          <S.Footer>
            <S.TopBox>
              <S.Icon>
                <S.IconImage source={DistIcon} />
              </S.Icon>
              <S.FooterText>
                {meterToKMOrMeter(item.totalDistance)}
              </S.FooterText>
            </S.TopBox>
            <S.MiddleBox>
              <S.Icon>
                <S.IconImage source={TimeIcon} />
              </S.Icon>
              <S.FooterText>{formatTime(item.totalTime)}</S.FooterText>
            </S.MiddleBox>
            <S.BottomBox>
              <S.Icon>
                <S.IconImage source={SpeedIcon} />
              </S.Icon>
              <S.FooterText>{speed.toFixed(2)} Km/h</S.FooterText>
            </S.BottomBox>
          </S.Footer>

          {/* 클리어 도장 */}
          <S.ClearBox>
            {item.clear && (
              <S.ClearImage source={ClearIcon} resizeMode="contain" />
            )}
          </S.ClearBox>
        </S.StyledImage>
      </S.ImageWrapper>

      {/* 캐릭터 */}
      <S.BodyBottomCharacterImageBox>
        <S.CharacterImage
          source={selectedCharacterLevelData.front}
          resizeMode="contain"
        />
      </S.BodyBottomCharacterImageBox>
    </S.PageItem>
  );
}
