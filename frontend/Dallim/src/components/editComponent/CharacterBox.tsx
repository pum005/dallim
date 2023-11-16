import * as S from './CharacterBox.styles';
import {characterData} from '@/recoil/data/CharacterData';

import {useRecoilValue} from 'recoil';
import {
  selectedCharacterIndexState,
  selectedEvolutionStageState,
  selectedCharacterIsPurchasedState,
} from '@/recoil/UserRecoil';

function CharacterBox() {
  const selectedCharacterIndex = useRecoilValue(selectedCharacterIndexState);
  const selectedEvolutionStage = useRecoilValue(selectedEvolutionStageState);
  const selectedCharacterIsPurchased = useRecoilValue(
    selectedCharacterIsPurchasedState,
  );

  const characterImage =
    characterData[selectedCharacterIndex].Evolutions[selectedEvolutionStage]
      .Main;

  return (
    <S.Container>
      <S.CharacterBox>
        {selectedCharacterIsPurchased ? (
          <S.CharacterImage source={characterImage} resizeMode="contain" />
        ) : (
          <S.BlurredCharacterImage
            source={characterImage}
            resizeMode="contain"
          />
        )}
      </S.CharacterBox>
    </S.Container>
  );
}
export default CharacterBox;
