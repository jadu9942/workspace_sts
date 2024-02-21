package com.green.First.controller;

import com.green.First.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//anotation 관재탑 역할하는 클래스
@Controller
public class FirstController {
    //데이터 가져오기
    @GetMapping("main")
    public String main(){
        return "first";
        //first.html 보여줌 first 리턴타입 String
         //localhost.8081/main

    }
    //@RequestParam은 html에서 넘어오는 데이터를 받을 때 사용
    //name= 넘어오는 데이터의 이름
    @GetMapping("second")
    public String second(@RequestParam(name="name") String name, @RequestParam(name="age") int age){
        System.out.println("@@@"+name);
        System.out.println(age);
        return "abc";
    }

    //넘어오는 데이터의 이름과 동일한 변수를 가진 클래스 객체로 데이터를 받을 수 있다
    @GetMapping("third")
    public String third(MemberVO memberVO, Model model){//클래스 만들어서 한번에 전송하기
        System.out.println(memberVO);      //매개변수 선언해줘야 model.addAttribute(,);쓸수있음
        //데이터를 html로 전달
        model.addAttribute("score",80);
                           //80이라는 실제값을 score라는 이름으로 abc.html로 보낸다
        return "abc";

    }
}





