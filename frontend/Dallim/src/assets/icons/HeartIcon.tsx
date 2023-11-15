import Svg, {Path, G, ClipPath, Rect, Defs} from 'react-native-svg';

interface Props {
  width: number;
  height: number;
  color: string;
}

function HeartIcon({width, height, color}: Props) {
  return (
    <Svg width={width} height={height} viewBox="0 0 24 24" fill="none">
      <Path
        fill-rule="evenodd"
        clip-rule="evenodd"
        d="M2.25003 8.28028C2.27927 5.42838 4.54489 3 7.45687 3C9.24426 3 10.559 3.88475 11.398 4.71777C11.6316 4.94972 11.8321 5.18108 12 5.39441C12.1679 5.18108 12.3683 4.94972 12.602 4.71777C13.441 3.88475 14.7557 3 16.5431 3C19.4551 3 21.7207 5.42838 21.75 8.28028L21.75 8.28049C21.8054 13.8427 17.3361 17.6908 12.8437 20.7403C12.5949 20.9095 12.3009 21.0001 12 21.0001C11.699 21.0001 11.4051 20.9095 11.1562 20.7403C6.66343 17.6908 2.19407 13.8427 2.25003 8.28042L2.25003 8.28028ZM12.6699 7.08725C12.5425 7.34027 12.2834 7.5 12 7.5C11.7166 7.5 11.4575 7.34029 11.3301 7.08729C11.33 7.08698 11.3296 7.08638 11.3292 7.0855C11.3283 7.08372 11.3268 7.08081 11.3246 7.0768C11.3235 7.07467 11.3222 7.07223 11.3208 7.0695C11.3123 7.0537 11.2983 7.02796 11.2787 6.9937C11.2394 6.92508 11.1781 6.82288 11.0947 6.69831C10.9272 6.44793 10.6755 6.11413 10.3412 5.78223C9.66941 5.11525 8.71261 4.5 7.45687 4.5C5.42265 4.5 3.77142 6.20966 3.74996 8.29551M12.6699 7.08725C12.6701 7.08694 12.6704 7.08635 12.6708 7.0855C12.6722 7.08278 12.675 7.07739 12.6792 7.0695C12.6876 7.0537 12.7017 7.02796 12.7213 6.9937C12.7606 6.92508 12.8219 6.82288 12.9053 6.69831C13.0728 6.44793 13.3245 6.11413 13.6588 5.78223C14.3306 5.11525 15.2874 4.5 16.5431 4.5C18.5773 4.5 20.2285 6.20963 20.25 8.29545C20.2968 12.9826 16.5324 16.4235 12.0007 19.4996L12 19.5001L11.9993 19.4996C7.46709 16.4235 3.70287 12.9827 3.74995 8.29566"
        fill={color}
      />
      <Path
        fill-rule="evenodd"
        clip-rule="evenodd"
        d="M9.81449 6.75279C10.1208 6.77923 10.38 6.98998 10.4684 7.2845L12.1749 12.9731L13.5792 10.1646C13.7062 9.91052 13.9659 9.75001 14.25 9.75001C14.5341 9.75001 14.7938 9.91052 14.9208 10.1646L16.2135 12.75H21.75C22.1642 12.75 22.5 13.0858 22.5 13.5C22.5 13.9142 22.1642 14.25 21.75 14.25H15.75C15.4659 14.25 15.2062 14.0895 15.0792 13.8354L14.25 12.1771L12.6708 15.3354C12.5333 15.6104 12.2419 15.7737 11.9355 15.7472C11.6292 15.7208 11.37 15.51 11.2816 15.2155L9.57506 9.52695L8.17082 12.3354C8.04378 12.5895 7.78408 12.75 7.5 12.75H2.25C1.83579 12.75 1.5 12.4142 1.5 12C1.5 11.5858 1.83579 11.25 2.25 11.25H7.03647L9.07918 7.1646C9.21669 6.88958 9.50814 6.72635 9.81449 6.75279Z"
        fill={color}
      />
    </Svg>
  );
}

export default HeartIcon;