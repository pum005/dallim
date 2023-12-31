import styled from 'styled-components/native';

export const Container = styled.View<{isShow: boolean}>`
  width: 100%;
  flex: 1;
  display: ${props => (props.isShow ? 'block' : 'none')};
`;

export const Text = styled.Text`
  color: white;
`;
