package com.green.shop.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

//로그인 실패 시 실행하는 클래스
// AuthenticationFailureHandler라는 인터페이스를 구현한 클래스를 생성하면
//onAuthenticationFailure() 라는 메소드를 정의해야 한다
//이 메서드가 로그인 실패 시 자동으로 호출된다.

@Component //객체 만들어 주는 어노테이션
public class LoginFailHandler implements AuthenticationFailureHandler {

    //로그인 실패 시 자동으로 호출되는 메소드
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
}
