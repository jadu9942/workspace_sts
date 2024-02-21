package com.green.Second.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


//@Controller: 해당 클래스 파일이 컨트롤러 역할을 하는 클래스임을 spring에서 인식
@Controller
public class MemberController {

    //@GetMapping, @postMapping
    //페이지 접속 정보
    //소괄호안의 글자와 localhost:8081 뒤의 글자가 일치하면
    //해당 메소드를 실행
    //@postMapping은 페이지 이동 방법 중에 form 태그로 이동하면서
    // form태그의 method 속성값이 post일때만 실행됨
    //get방식:
    //1. form 태그의 mehod 속성값이 get일 때
    //2. a 태그로 페이지가 이동될때
    //3. 주소창에 직접 url을 입력했을 때

@GetMapping("/")
    public String main(){
        // 리턴되는 문자열은 마지막에 실행되는 html 파일명
        //html 파일은 반드시 templates 폴더 안에 저장

        return "first" ; //first.html 실행

    }

    //@RequestParam 어노테이션으로 html에서 넘어오는 데이터를 전달 받을 수 있다
    //해당 어노테이션의 속성으로는 name,required, defaultValue가 있음
    //name : html에서 넘어오는 데이터의 이름
    //required: 넘어오는 데이터가 필수 데이터인지 설정 (true이면 무조건 넘어오고 false는 넘어올 수도 있고 아닐 수도 있다)
    //required: 속성을 적지 않으면 default값은 true
    //defaultValue: 데이터가 넘어오지 않을 때 설정되는 기본값

    //html로 데이터를 전달하기 위해서는 메소드의 매개변수로 Model(interface)의 객체를선언
    @GetMapping("/second")
    public String second(@RequestParam(name = "addr",required = false) String address,
                         @RequestParam(name = "age",required = false,defaultValue = "1") int age,
                         Model model){
    //                    요청으로 데이터 넘어왔는데 addr이름으로 넘어온 데이터 받겠다
        //                 @RequestParam가 있으면 넘어오는 데이터 무조건 있어야 함 없으면 required=false 써주기
        //                                                          age 이름있으면 받고 없으면 1로 받기
        System.out.println("addr ="+address);
        System.out.println("addr ="+age);

        //html로 데이터 전달하기
        model.addAttribute("addr",address);//받아오는 데이터 이름, 데이터 값
        model.addAttribute("age",age);
        model.addAttribute("name","홍길동"); //이 데이터 second.html로 전달



        return "second";


    }
}
