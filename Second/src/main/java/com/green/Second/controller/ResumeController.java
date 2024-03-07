package com.green.Second.controller;

import com.green.Second.vo.ResumeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResumeController {

    @GetMapping("/resume")
    public String first() {
        return "resume";
        //html 위치가 resources 안 templates에 있어야 함
    }
//form타고 올 때 postMapping써야 함
    @PostMapping("/r2")
    public String resumeinfo(@RequestParam(name= "name") String name
                           , @RequestParam(name="tel")String tel,Model model) {
        //                                   name이라는 이름으로 받아오기/ 매개변수 안에 마지막부분에 model 인터페이스 넣기
        System.out.println("name="+name);
        System.out.println("tel="+tel);

        model.addAttribute("name", name);
        model.addAttribute("tel", tel);

        return "resumeinfo";
        // 이동해야 하는 html
    }

    @PostMapping("/r3")
   public String third(ResumeVO resumeVO){
        //넘어오는 데이터와 이름이 동일한 변수 그리고 getter setter만 있으면 됨
        System.out.println(resumeVO);
        return "";
   }







}
