package com.freelec.book.springboot.config;

import com.freelec.book.springboot.config.auth.LoginUserArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//HandlerMethodArgumentResolver 를 스프링에서 인식할 수 있도록 하기 위한 작업
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    public WebConfig(LoginUserArgumentResolver loginUserArgumentResolver) {
        this.loginUserArgumentResolver = loginUserArgumentResolver;
    }
}
