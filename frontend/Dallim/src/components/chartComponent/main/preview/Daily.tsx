import {useState, useEffect} from 'react';
import {Dimensions} from 'react-native';
import * as S from './Daily.styles';
import {FlatList} from 'react-native-gesture-handler';
import {CalendarType} from '@/recoil/CalendarData';
import {DailyRecord} from '../Preview';
import {useNavigation} from '@react-navigation/native';

const screenWidth = Dimensions.get('window').width;
const cardWidth = screenWidth * 0.8;

interface Props {
  date?: CalendarType;
  isShow: boolean;
  records?: DailyRecord[];
}

function PreviewDaily({date, isShow, records}: Props) {
  const navigation = useNavigation();
  const [flatListKey, setFlatListKey] = useState(0);

  useEffect(() => {
    setFlatListKey(flatListKey + 1);
  }, [records]);
  return (
    <S.Container isShow={isShow}>
      <S.Title>
        {date?.year}년 {date?.month}월 {date?.day}일
      </S.Title>
      <FlatList
        horizontal
        data={records}
        key={flatListKey} // 이걸 이용해서 records가 변경될 때마다 flat리스트가 재 랜더링되도록 함
        renderItem={({item}) => (
          <RunningCard item={item} navigation={navigation} isWatch={true} />
        )}
        showsHorizontalScrollIndicator={false} // 가로 스크롤바 표시
        contentContainerStyle={{
          paddingHorizontal: screenWidth / 12,
        }} // 왼쪽과 오른쪽에 여백 추가
        initialScrollIndex={0}
      />
    </S.Container>
  );
}

function RunningCard({
  item,
  navigation,
  isWatch,
}: {
  item: DailyRecord;
  navigation: any;
  isWatch: boolean;
}) {
  const secondsToHoursAndMinutes = (seconds: number) => {
    const hours = Math.floor(seconds / 3600);
    const remainingSeconds = seconds % 3600;
    const minutes = Math.floor(remainingSeconds / 60);
    return {hours, minutes};
  };
  const [spend, setSpend] = useState<string>('');
  useEffect(() => {
    const hours = Math.floor(item.time / 3600); // 시간 계산
    const minutes = Math.floor((item.time % 3600) / 60); // 분 계산
    const seconds = item.time % 60; // 초 계산
    if (item.time >= 3600) {
      setSpend(`${hours}시간 ${minutes}분 ${seconds}초`);
    } else if (item.time >= 60) {
      setSpend(`${minutes}분 ${seconds}초`);
    } else {
      setSpend(`${seconds}초`);
    }
  }, [item]);
  return (
    <S.Card
      width={cardWidth}
      onPress={() => navigation.push('ChartDetail', {id: item.id})}>
      <S.CardImage
        source={
          isWatch
            ? require('@/assets/images/RunWithWatch.png')
            : require('@/assets/images/RunWithPhone.png')
        }
        resizeMode="contain"
      />
      <S.CardTexts>
        <S.CardTitle>{item.location}</S.CardTitle>
        <S.CardDatas>
          <S.CardData>{item.distance}m</S.CardData>
          <S.CardData>
            {item.hour}시 {item.minute}분 시작
          </S.CardData>
          <S.CardData>{spend}</S.CardData>
        </S.CardDatas>
      </S.CardTexts>
    </S.Card>
  );
}

export default PreviewDaily;