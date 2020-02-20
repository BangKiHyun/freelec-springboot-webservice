package com.freelec.book.springboot.config.auth.dto;

import com.freelec.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;


//SessionUser 에는 인증된 사용자 정보만 필요로 합니다
//그러므로 필요한 정보만 필드로 선언합니다
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
