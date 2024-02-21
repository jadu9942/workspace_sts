package com.green.FetchStudent.controller;

//비동기 통신 학습용 컨트롤러
// 비동기 통신 방법 다양하다
//javascript-> fetch 라는 기능을 써서 비동기 통신을 만든다
//jquery-> ajax /자바 스크립트를 간단하게 만들어 놓은 언어 but 성능이 떨어져서 사용X
//$()->  jquery 문법 사용 x
//react(화면 그리는 tool)  ->  axios

import com.green.FetchStudent.vo.StuVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/study")
public class FetchController {

    @GetMapping("/t1")
    public String t1(){
        return "fetch_test";
    }

    //@ResponseBody :해당 메소드는 비동기 통신을 사용하기 때문에
    //메소드의 마지막 return으로 페이지 전환 하지 않겠다고 스프링에게 알려주는 코드
    @ResponseBody
    @PostMapping("/t2")
    public String t2(@RequestParam(name="name")String name, @RequestParam(name="age")int age){
        System.out.println("t1 메소드 실행");
        System.out.println("name="+name);
        System.out.println("age"+age);
        return "hello";
//        return ""; 작성x -> 비동기 통신 페이지 이동 X  이걸 어노테이션으로 써야함 ->@ResponseBody
    // 페이지 이동 없고 변화는 인텔리제이 console창에서 확인
    //return 쓰면? fetch가 실행되면 컨트롤러와서 할 거 다하고 then으로 가는데 그때 return 가지고 감(페이지 개발자 console창에서 확인)
    // 그리고 then 영역에 data라는 변수에 그 데이터가 들어간다
        // 이때 return 값 뭐 던져줄 때마다 달라짐

    //그럼 이 메소드 실행하면 뭐하나?

    }
    @ResponseBody
    @PostMapping("/t3")
    public StuVO t3(StuVO vo){
        // 넘어온 데이터 커맨드객체로 받아오기
        System.out.println(vo);
        System.out.println("t3 메소드 실행");

        StuVO stuVO= new StuVO();
        stuVO.setStuNum(1);
        stuVO.setStuName("김자바");
        stuVO.setClassCode(1);
        stuVO.setClassName("자바반");

        return stuVO;
    }

    @ResponseBody
    @PostMapping("/t4")
    public List<StuVO> t4() {
        System.out.println("t4 메소드 실행");

        List<StuVO> list = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            StuVO stuVO = new StuVO();
            stuVO.setStuNum(i);
            stuVO.setStuName("자바_" + i);
            stuVO.setClassCode(1 + i);
            stuVO.setClassName("자바반" + i);
            list.add(stuVO);

        }
        return list;
        //자바 스크립트로 던지는 데이터

    }


}
