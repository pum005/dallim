import {atom} from 'recoil';
//
interface UserData {
  point: number;
  mainCharacterIndex: number;
  mainPlanetIndex: number;
  characters: Character[];
  planets: Planet[];
}

interface Character {
  characterIndex: number;
  evolutionStage: number;
  level: number;
  exp: number;
  isPurchased: boolean;
}

interface Planet {
  planetIndex: number;
  isPurchased: boolean;
}

interface UserData {
  point: number;
  mainCharacterIndex: number;
  mainPlanetIndex: number;
  characters: Character[];
  planets: Planet[];
}

export const userDataState = atom<UserData | null>({
  key: 'userDataState',
  default: null,
});

export const userIdState = atom<number>({
  key: 'userIdState',
  default: 0,
});

export const userNicknameState = atom<string>({
  key: 'userNicknameState',
  default: '',
});

export const userPointState = atom<number>({
  key: 'userPointState',
  default: 0,
});

export const userLevelState = atom<number>({
  key: 'userLevelState',
  default: 1,
});

export const userExpState = atom<number>({
  key: 'userExpState',
  default: 0,
});

// 장착중
export const equippedCharacterIndexState = atom<number>({
  key: 'equippedCharacterIndexState',
  default: 0,
});

export const equippedEvolutionStageState = atom<number>({
  key: 'equippedEvolutionStageState',
  default: 0,
});

export const equippedCharacterLevelState = atom<number>({
  key: 'equippedCharacterLevelState',
  default: 1,
});

export const equippedPlanetIndexState = atom<number>({
  key: 'equippedPlanetIndexState',
  default: 0,
});

// 선택
export const selectedCharacterIndexState = atom<number>({
  key: 'selectedCharacterIndexState',
  default: 0,
});

export const selectedCharacterLevelState = atom<number>({
  key: 'selectedCharacterLevelState',
  default: 1,
});

export const selectedEvolutionStageState = atom<number>({
  key: 'selectedEvolutionStageState',
  default: 0,
});

export const selectedCharacterExpState = atom<number>({
  key: 'selectedCharacterExpState',
  default: 0,
});

export const selectedCharacterIsPurchasedState = atom<boolean>({
  key: 'selectedCharacterIsPurchasedState',
  default: false,
});

export const selectedPlanetIndexState = atom<number>({
  key: 'selectedPlanetIndexState',
  default: 0,
});

export const selectedPlanetIsPurchasedState = atom<boolean>({
  key: 'selectedPlanetIsPurchasedState',
  default: false,
});

export const isOnState = atom<boolean>({
  key: 'isOnState',
  default: false,
});
