import React from 'react';
import {Modal} from 'react-native';
import * as S from './PrivacyPolicyModal.styles';

type ModalComponentProps = {
  showModal: boolean;
  toggleModal: () => void;
};

const PrivacyPlicyModal = ({showModal, toggleModal}: ModalComponentProps) => {
  return (
    <Modal transparent={true} animationType="fade" visible={showModal}>
      <S.ModalContainer>
        <S.ModalContent>
          <S.ModalHeader></S.ModalHeader>
          <S.ModalBody></S.ModalBody>
          <S.ModalFooter></S.ModalFooter>
        </S.ModalContent>
      </S.ModalContainer>
    </Modal>
  );
};

export default PrivacyPlicyModal;
