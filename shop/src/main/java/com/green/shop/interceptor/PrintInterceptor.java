package com.green.shop.interceptor;

import com.green.shop.member.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

//Interceptor 클래스
//HandlerInterceptor 인터페이스를 구현한 클래스는 Interceptor 클래스로 인식
//해당 클래스에는 반복되는 기능을 정의
//반복되는 기능의 호출 시점에 따라서
//HandlerInterceptor 인터페이스의 메소드를 오버라이딩
//오버라이딩 할 수 있는 메소드 3개
// preHandle(): controller 안의 메소드 실행 직전에 호출
// postHandle(): controller 안의 메소드 실행 후 html로 가기 전에 호출
// afterCompletion(): controller 안의 메소드 실행 후 html까지 다 실행 되면 호출

public class PrintInterceptor implements HandlerInterceptor {
    //preHandle : 주로 권한 체크용
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("PrintInterceptor: preHandle() 메소드 실행~~~~~");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("PrintInterceptor:postHandle() 메소드 실행~~~~~");

        //컨트롤러에서 전달한 데이터 확인
        Map<String, Object> modelData =modelAndView.getModel();
        String name =modelData.get("name").toString(); //model안에 들고 온 데이터 중 name이라는 키 뽑겠다
        MemberVO member=(MemberVO) modelData.get("member");

        modelAndView.addObject("addr","울산");
    }
}
