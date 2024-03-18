package com.green.shop.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//로그인 성공 시 실행되는 클래스
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();



    //로그인 성공 시 자동으로 호출되는 메소드
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("!!!!!!!!!!!!!!!!!!");
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        //로그인 성공 시 이동할 페이지
        setDefaultTargetUrl("/");


        // 로그인 시 필요한 코드 여기서 작성
        //ex) 세션에 데이터 저장 , 로그인 하자마자 관리하는 기능 여기서
        HttpSession session= request.getSession();
//        session.setAttribute();
//        session.getAttribute();

        //로그인 정보를 통한 로직 구성
        User user= (User)authentication.getPrincipal(); //로그인 한 사람의 정보 security로 받아옴
        List<GrantedAuthority> authoList = new ArrayList<>(user.getAuthorities()); //권한 정보 받기 // 회원의 권한 하나 이상일 때가 많아서 list로 받아옴
        List<String> aList= new ArrayList<>();

        for (GrantedAuthority authority: authoList){
            aList.add(authority.getAuthority());
        }

        boolean b = aList.contains("ADMIN");

        if(b){
            redirectStrategy.sendRedirect(request, response, "/admin/regItem");
        }



        //이전 페이지 혹은 가려던 페이지가 있는 경우
        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl(); //원래 가려던 페이지 찾아서
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }else{ // setDefaultTargetUrl("/") 이 url로 이동
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }
    }
}
