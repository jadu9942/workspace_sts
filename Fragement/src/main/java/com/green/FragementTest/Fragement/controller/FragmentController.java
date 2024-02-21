package com.green.FragementTest.Fragement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

    @GetMapping("/")
    public String main(){
        return "content/content_1";
        //content.html 파일은 content폴더 안에 있음 폴더명을 넣어줘야 함
    }
    @GetMapping("/content2")
    public String content2(){
        return "content/content_2";

    }
}
