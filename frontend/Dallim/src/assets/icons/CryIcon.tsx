import Svg, {Path, G, Defs, ClipPath, Rect} from 'react-native-svg';

interface Props {
  width: number;
  height: number;
  color: string;
}

function CryIcon({width, height, color}: Props) {
  return (
    <Svg width={width} height={height} viewBox="0 0 24 24" fill="none">
      <G clip-path="url(#clip0_396_21587)">
        <Path
          d="M9 10H9.01"
          stroke={color}
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
        />
        <Path
          d="M15 10H15.01"
          stroke={color}
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
        />
        <Path
          d="M9.5 15.25C9.82588 14.9174 10.2148 14.6531 10.6441 14.4728C11.0734 14.2924 11.5344 14.1995 12 14.1995C12.4656 14.1995 12.9266 14.2924 13.3559 14.4728C13.7852 14.6531 14.1741 14.9174 14.5 15.25"
          stroke={color}
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
        />
        <Path
          d="M17.566 17.606C17.2933 17.8867 17.1088 18.2412 17.0356 18.6256C16.9623 19.0101 17.0035 19.4076 17.154 19.7689C17.3045 20.1302 17.5576 20.4394 17.8821 20.6582C18.2066 20.877 18.5882 20.9958 18.9795 20.9998C19.3709 21.0039 19.7548 20.893 20.0838 20.681C20.4127 20.4689 20.6723 20.165 20.8302 19.807C20.9881 19.4489 21.0375 19.0523 20.9722 18.6664C20.907 18.2805 20.7299 17.9222 20.463 17.636L19 16L17.566 17.606V17.606Z"
          stroke={color}
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
        />
        <Path
          d="M20.865 13.517C20.9531 13.0161 20.9983 12.5086 21 12C21 10.22 20.4722 8.47991 19.4832 6.99987C18.4943 5.51983 17.0887 4.36628 15.4442 3.68509C13.7996 3.0039 11.99 2.82567 10.2442 3.17294C8.49836 3.5202 6.89472 4.37737 5.63604 5.63604C4.37737 6.89472 3.5202 8.49836 3.17294 10.2442C2.82567 11.99 3.0039 13.7996 3.68509 15.4442C4.36628 17.0887 5.51983 18.4943 6.99987 19.4832C8.47991 20.4722 10.22 21 12 21C12.69 21 13.36 20.924 14 20.778"
          stroke={color}
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
        />
      </G>
      <Defs>
        <ClipPath id="clip0_396_21587">
          <Rect width={width} height={height} fill="white" />
        </ClipPath>
      </Defs>
    </Svg>
  );
}

export default CryIcon;
