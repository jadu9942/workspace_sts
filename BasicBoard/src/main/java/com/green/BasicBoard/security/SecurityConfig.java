package com.green.BasicBoard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 스프링 시큐리티 인증, 인가에 대한 프로세스를 정의
// 이 클래스가 시큐리티 설정 파일임을 인지 시켜주는 역할
@EnableWebSecurity
// 객체 생성 어노테이션 (ex. @Controller ,@Service) - 객체 만들어주는 기능을 동일 역할이 다름
@Configuration // 설정
public class SecurityConfig {

    //@Bean : 객체 생성 어노테이션
    //메소드의 실행 결과 리턴되는 데이터를 객체로 생성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        //throws Exception : 예외 처리 다른데 넘기겠다
        //csrf: csrf 공격에 대해 어떻게 대처할 거냐 ? 여기서는 대체 없음(disable)
        security.csrf(AbstractHttpConfigurer::disable) //메소드참조
                // 아래 메소드 안에 인증, 인가에 대한 모든 설정 작성
                .authorizeHttpRequests(
                        c -> {
                            c.requestMatchers(
                                    new AntPathRequestMatcher("/"),
                                    new AntPathRequestMatcher("/loginForm"),
                                    new AntPathRequestMatcher("/joinForm")
                                    ).permitAll()
                                    .anyRequest().authenticated();
                        }
                )
                //로그인 설정 + 로그인 할 때 form 방식으로 쓰겠다.
                .formLogin(
                        formLogin -> {
                            // 로그인 페이지로 이동하는 url 설정
                            formLogin.loginPage("/loginForm")
                                    //실제 로그인 기능을 실행하는 url 설정
                                    .loginProcessingUrl("/login")
                                    //로그인 성공 시 기본적으로 이동하는 페이지
                                    .defaultSuccessUrl("/");
                        }
                );

        return security.build();
    }

}
