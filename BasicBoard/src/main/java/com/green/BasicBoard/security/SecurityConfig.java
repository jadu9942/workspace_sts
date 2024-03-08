package com.green.BasicBoard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//.authorizeHttpRequests(처음 로그인 만들 때 이렇게 작성 모든 페이지 접근 가능하게
//         c -> {
//               anyRequest().permitAll();
//          }

//경로
// /member/*
// /member/a , /member/b, /member/c, /member/abc/a(x)-여러개 들어오는 거 안 됨
// /member/**
// /member/a ,/member/b, /member/a/c, /member/a/b/c 가능



// 스프링 시큐리티 인증, 인가에 대한 프로세스를 정의
// 이 클래스가 시큐리티 설정 파일임을 인지 시켜주는 역할
@EnableWebSecurity
// 객체 생성 어노테이션 (ex. @Controller ,@Service) - 객체 만들어주는 기능을 동일 역할이 다름
@Configuration // 설정
public class SecurityConfig {

    //@Bean : 객체 생성 어노테이션
    //메소드의 실행 결과 리턴되는 데이터를 객체로 생성

    //암호화에 사용하는 객체 생성
    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }


    //@Bean : 객체 생성 어노테이션
    //메소드의 실행 결과 리턴되는 데이터를 객체로 생성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        //csrf: 공격에 대한 방어를 하지 않겠다
        security.csrf(AbstractHttpConfigurer::disable)
                //authorizeHttpRequests 메소드 안에서 인증 및 인가 관리
                .authorizeHttpRequests(
                        c -> {
                            c.requestMatchers(
                               new AntPathRequestMatcher("/"),
                               new AntPathRequestMatcher("/loginForm"),
                               new AntPathRequestMatcher("/joinForm"),
                               new AntPathRequestMatcher("/join"),
                               new AntPathRequestMatcher("/login"),
                               new AntPathRequestMatcher("/sample")
                               //new AntPathRequestMatcher("/member/**")
                            ).permitAll()
                             .requestMatchers(
                                     new AntPathRequestMatcher("/admin")
                             ).hasRole("ADMIN")
                                    .requestMatchers(
                                     new AntPathRequestMatcher("/manager")
                                    ).hasRole("MANAGER")
                                    .requestMatchers(
                                     new AntPathRequestMatcher("/boardWriteForm")
                                    ).hasAnyRole("USER","MANAGER") // 권한이 user이거나 manager인 경우 둘 중 아무거나 있으면 requestMatcher안에 들어갈 수 있다
                             .anyRequest().authenticated(); //그밖에 나머지 페이지는 로그인 필요
                        }


                )
                //로그인 form을 활용해서 할 것이고,
                //로그인 설정 내용도 포함
                .formLogin(
                        formLogin -> {
                            //로그인 페이지 url 설정  (인증이 안되면 로그인 페이지로 이동함)
                            formLogin.loginPage("/loginForm")
                                    //로그인 시 전달되는 id 및 비밀번호의 name 속성 값을 지정
                                    .usernameParameter("memberId")
                                    .passwordParameter("memberPw")
                                    //로그인 기능이 실행되는 url
                                    .loginProcessingUrl("/login")
                                    //로그인 성공 시 이동할 url
                                    //두번째 매개변수로 true를 넣으면 항상 지정한 url로
                                    //두번째 매개변수가 없으면 이전 페이지로 이동
                                    //이전 페이지가 없다면 지정한 url로 이동
                                    .defaultSuccessUrl("/")
                                    //로그인 실패시 이동할 url
                                    .failureUrl("/loginForm");
                        }
                )
                .logout(
                        logout -> {
                            //해당 url 요청이 들어오면 시큐리티가 로그아웃 진행
                            logout.logoutUrl("/logout")
                                    .logoutSuccessUrl("/") //로그아웃 성공시 이동할 url
                                    .invalidateHttpSession(true); //로그아웃 성공 시 session에 있는 데이터 모두 지워버림
                        }
                //예외 발생시 처리해야 하는 내용 작성
                ).exceptionHandling(
                        ex -> {
                            ex.accessDeniedPage("/deny"); //접근 거부된 페이지
                        }
                );
        return security.build();
    }


}
