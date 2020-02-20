package com.freelec.book.springboot.config.auth;

import com.freelec.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity//Spring Security 설정 활성화시켜주는 역할
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 해동 옵션들을 disable 해야줘야 한다
                .and()
                .authorizeRequests() //URL별 권한 관리르 설정하는 옵션의 시작점이다
                //authorizeRequests가 선언되어야만 antMatchers 사용 가능
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()//권환 관리 대상을 지정하는 옵션
                //URL, HTTP메소드별로 관리가 가능
                //"/"등 지정된 URL들은 permitAll()옵션을 통해 전체 열람 권한을 주었습니다
                //POST 메소드이면서 "/api/v1/**"주소를 가진 API는 USER권한을 가진 사람만 가능하도록 했습니다
                .and()
                .logout()
                .logoutSuccessUrl("/") //로그아웃 기능에 대한 여러 설정의 진입점
                //로그아웃 성공시 "/" 주소로 이동
                .and()
                .oauth2Login() //OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() //OAuth2로그인 성공 후 사용자 정보를 가져올 때의 설정들을 담당
                .userService(customOAuth2UserService); //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
        //리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있음
    }
}
