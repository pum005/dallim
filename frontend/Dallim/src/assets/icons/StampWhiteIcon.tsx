import Svg, { Path, Rect } from 'react-native-svg';
interface Props {
  width: number;
  height: number;
  color: string;
}

function StampWhiteIcon({ width, height, color }: Props) {
  return (
    <Svg width={width} height={height} viewBox="0 0 30 25" fill="none">
      <Path d="M20 12.7778V10.5556C20 10.2609 19.8829 9.97826 19.6746 9.76988C19.4662 9.56151 19.1836 9.44444 18.8889 9.44444H17.7778V10.5556C17.7778 10.8502 17.6607 11.1329 17.4523 11.3412C17.244 11.5496 16.9614 11.6667 16.6667 11.6667C16.372 11.6667 16.0894 11.5496 15.881 11.3412C15.6726 11.1329 15.5556 10.8502 15.5556 10.5556V9.44444H6.66667V10.5556C6.66667 10.8502 6.5496 11.1329 6.34123 11.3412C6.13286 11.5496 5.85024 11.6667 5.55556 11.6667C5.26087 11.6667 4.97826 11.5496 4.76988 11.3412C4.56151 11.1329 4.44445 10.8502 4.44445 10.5556V9.44444H3.33333C3.03865 9.44444 2.75603 9.56151 2.54766 9.76988C2.33929 9.97826 2.22222 10.2609 2.22222 10.5556V12.7778H20ZM20 15H2.22222V21.6667C2.22222 21.9614 2.33929 22.244 2.54766 22.4523C2.75603 22.6607 3.03865 22.7778 3.33333 22.7778H18.8889C19.1836 22.7778 19.4662 22.6607 19.6746 22.4523C19.8829 22.244 20 21.9614 20 21.6667V15ZM17.7778 7.22222H18.8889C19.7729 7.22222 20.6208 7.57341 21.2459 8.19853C21.871 8.82365 22.2222 9.6715 22.2222 10.5556V21.6667C22.2222 22.5507 21.871 23.3986 21.2459 24.0237C20.6208 24.6488 19.7729 25 18.8889 25H3.33333C2.44928 25 1.60143 24.6488 0.976311 24.0237C0.351189 23.3986 0 22.5507 0 21.6667L0 10.5556C0 9.6715 0.351189 8.82365 0.976311 8.19853C1.60143 7.57341 2.44928 7.22222 3.33333 7.22222H4.44445V6.11111C4.44445 5.81643 4.56151 5.53381 4.76988 5.32544C4.97826 5.11706 5.26087 5 5.55556 5C5.85024 5 6.13286 5.11706 6.34123 5.32544C6.5496 5.53381 6.66667 5.81643 6.66667 6.11111V7.22222H15.5556V6.11111C15.5556 5.81643 15.6726 5.53381 15.881 5.32544C16.0894 5.11706 16.372 5 16.6667 5C16.9614 5 17.244 5.11706 17.4523 5.32544C17.6607 5.53381 17.7778 5.81643 17.7778 6.11111V7.22222Z" fill={color} />
      <Rect x="12.2207" y="16.1113" width="2.22222" height="2.22222" fill={color} />
      <Rect x="3.33203" y="16.1113" width="2.22222" height="2.22222" fill={color} />
      <Rect x="7.77734" y="16.1113" width="2.22222" height="2.22222" fill={color} />
      <Rect x="3.33203" y="19.4448" width="2.22222" height="2.22222" fill={color} />
      <Rect x="7.77734" y="19.4448" width="2.22222" height="2.22222" fill={color} />
      <Path d="M14.002 21.3901L18.5254 16.5424L19.4332 16.5831L19.3378 17.3004L14.8143 22.1482L14.002 21.3901Z" fill={color} />
      <Path d="M14.7676 22.2046L12.6331 20.2604L12.6036 19.4677L13.3813 19.439L15.5158 21.3831L14.7676 22.2046Z" fill={color} />
      <Path d="M24.6148 10.7436L22.6321 8.95197C22.7258 8.98789 22.836 8.96507 22.9073 8.88627L24.9367 6.64541C25.3513 6.69589 25.7766 6.62162 26.1591 6.43247C26.5855 6.22167 26.9368 5.87916 27.1593 5.45785C27.3818 5.03646 27.4641 4.55774 27.3907 4.09522C27.3172 3.6324 27.0918 3.21219 26.7478 2.90137C26.4038 2.59056 25.9627 2.4085 25.4944 2.3817C25.0263 2.3549 24.5577 2.48454 24.1602 2.74784C23.7628 3.01109 23.4567 3.39447 23.2893 3.83927C23.1391 4.2384 23.1076 4.66848 23.1993 5.07552L21.17 7.31638C21.0986 7.39518 21.0869 7.50697 21.132 7.59649L19.1493 5.80489C18.916 5.59401 18.606 5.49082 18.2899 5.51434C17.9743 5.53781 17.6781 5.68534 17.4641 5.92167L16.8153 6.6381C16.7226 6.74044 16.7305 6.89845 16.8329 6.99101L23.6648 13.1644C23.7672 13.2569 23.9254 13.249 24.0181 13.1467L24.6669 12.4302C24.8809 12.1939 24.9983 11.8847 24.9901 11.5688C24.982 11.2522 24.8482 10.9544 24.6148 10.7436Z" fill={color} stroke={color} stroke-width="0.5" stroke-linejoin="round" />
    </Svg>


  );
}

export default StampWhiteIcon;
