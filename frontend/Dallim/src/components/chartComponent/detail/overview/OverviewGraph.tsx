import * as S from './OverviewGraph.styles';
import {useState, useEffect} from 'react';
import {LineChart} from 'react-native-gifted-charts';
import {HeartChartDataType, PaceChartDataType} from '@/apis/ChartApi';
import {itemType} from 'react-native-gifted-charts/src/LineChart/types';
import {colors} from '@/components/common/globalStyles';

interface Props {
  title: string;
  data?: PaceChartDataType[] | HeartChartDataType[] | itemType[];
  data2?: PaceChartDataType[] | itemType[];
  color1: string;
  color2?: string;
}

function OverviewGraph({title, data, data2, color1, color2}: Props) {
  const [parentWidth, setParentWidth] = useState<number>(0);
  const [parentHeight, setParentHeight] = useState<number>(0);
  const [maxValue, setMaxValue] = useState<number>();

  const onLayout = (event: any) => {
    const {width, height} = event.nativeEvent.layout;
    setParentWidth(width);
    setParentHeight(height);
  };

  useEffect(() => {
    let max = 0;
    data?.map(d => {
      max = max > d.value ? max : d.value;
    });
    if (data2) {
      data2.map(d => {
        max = max > d.value ? max : d.value;
      });
    }
    setMaxValue(max * 1.1);
  }, []);

  return (
    <S.Container>
      <S.TitleContainer>
        <S.Title>{title}</S.Title>
      </S.TitleContainer>
      {maxValue && data && (
        <S.Chart onLayout={onLayout}>
          <LineChart
            width={parentWidth}
            height={parentHeight}
            areaChart
            curved
            data={data} // 데이터
            startFillColor={color1}
            startOpacity={0.7}
            endFillColor={color1}
            endOpacity={0}
            color={color1}
            data2={data2 ? data2 : undefined} // 같이달리기 컴포넌트
            startFillColor2={color2}
            startOpacity2={0.7}
            endFillColor2={color2}
            endOpacity2={0}
            color2={color2}
            hideDataPoints // 점 숨기기
            hideYAxisText // y 라벨 없애기
            hideAxesAndRules // 내부 선 및 y선 x선 없애기
            initialSpacing={0}
            adjustToWidth // width에 데이터 크기 맞추기
            maxValue={maxValue}
          />
        </S.Chart>
      )}
    </S.Container>
  );
}

export default OverviewGraph;
