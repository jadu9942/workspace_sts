package com.green.shop.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//생성한 인터셉터 글래스의 적용 시점 정의
//WebMvcConfigurer 인터페이스를 구현 후
//addInterceptor() 메소드를 오버라이딩
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //interceptor가 실행되는 url지정
    //addInterceptor(): 이 메소드 안에 실행 시킬 인터셉터의 객체를 전달
    //addPathPatterns(): 인터셉터의 기능이 실행되는 url 지정
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getPrintInterceptor())
                .order(2)
//                .addPathPatterns("/**/**")// /전체 url모두
//                .addPathPatterns("/item/test2");
                .addPathPatterns("/item/**")// /item 밑에 url모두
                .excludePathPatterns("/item/test3"); //item controller에서 test3만 제외


        //******** 인터셉터는 비동기 통신 메소드에는 사용 불가!!!!!
        // 그래서 비동기로 실행되는 메소드는 반드시 인터셉터 실행에서 제외
        //비동기 통신으로 가는 컨트롤러에서는 무조건 excludePathPatterns에 제외 설정해줘야 함.

        registry.addInterceptor(getDbInterceptor())
                .order(1)// 인터셉터 실행 호출 순서
                .addPathPatterns("/item/test1")
                .addPathPatterns("/item/test3");
    }

    //Bean: 메소드의 리턴 데이터를 객체러 생성
    @Bean
    public PrintInterceptor getPrintInterceptor(){
        return new PrintInterceptor();
    }

    @Bean
    public DBInterceptor getDbInterceptor(){
        return new DBInterceptor();
    }
}
