package com.b208.dduishu.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

    private Long userId;
    private String accountType;
    private String email;
    private String nickname;
    private String profileImage;

    public UserDTO() {
    }
}