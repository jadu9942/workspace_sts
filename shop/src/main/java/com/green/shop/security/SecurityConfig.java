package com.green.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 인증 인가에 대한 설정을 위한 클래스
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private LoginFailHandler loginFailHandler;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    //인증과 인가에 대한 설정 내용이 있는 메소드를 구현
    //반드시 리턴타입은 SecurityFilterChain
    //메소드의 매개변수로 httpSecurity 객체가 필요
    @Bean //객체 생성 어노테이션
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{

        security.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        c -> { //인증 인가
                            c.anyRequest().permitAll();

//                            c.requestMatchers(
//                                   new AntPathRequestMatcher("/"),
//                                   new AntPathRequestMatcher("/item/list"),
//                                   new AntPathRequestMatcher("/member/loginForm"), //로그인 하러 가는 페이지
//                                   new AntPathRequestMatcher("/member/join"),
//                                   new AntPathRequestMatcher("/member/login")
//                                   ).permitAll() //여기는 인증 허가(로그인 필요없이 가능)
//                            .requestMatchers(
//                                    new AntPathRequestMatcher("/admin/**")
//                            ).hasRole("/ADMIN") //어떤 권한(인가)이 있을 때만 접근 가능
//                            .anyRequest().authenticated();
//                                c.anyRequest().permitAll();
                        }
                )
                .formLogin(//로그인 어떻게 할 거냐
                        formLogin -> {
                            formLogin.loginPage("/member/loginForm")
                                    .loginProcessingUrl("/member/login") //이런 요청 들어오면 security가 알아서 로그인 실행(컨트롤러 가는 거 아님)
                                    //.defaultSuccessUrl("/") //true 없으면 로그인 성공 시 이전 페이지 혹은 가고자 했던 페이지로 이동/ true 있으면 "/"페이지로 이동
                                    //.failureUrl("/member/loginForm")
                                    .usernameParameter("memberId")
                                    .passwordParameter("memberPw")
                                    //로그인 성공 시 실행 시킬 클래스의 객체
                                    .successHandler(loginSuccessHandler)
                                    //로그인 실패 시 실행 시킬 클래스의 객체
                                    .failureHandler(loginFailHandler);
                        }
                )
                .logout(
                        logout -> {

                        }
                );

        return security.build();

    }

    //정적인 자원은 security가 인증 및 인가 관리에서 제외하도록 설정하는 메소드
    //정적인 자원? 정적인 파일 예를 들어 자바스크립트, css, image
    //-> resources 폴더 밑에 있는 모든 파일들을 의미한다
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(
                new AntPathRequestMatcher("/upload/**"), //upload파일 하위에 있는 것 들 전부 ignore
                new AntPathRequestMatcher("/css/**"),
                new AntPathRequestMatcher("/images/**"),
                new AntPathRequestMatcher("/js/**"),
                new AntPathRequestMatcher("/favicon.ico")

        );
    }
}
