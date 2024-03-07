package com.green.BasicBoard.controller;

import com.green.BasicBoard.service.BoardService;
import com.green.BasicBoard.service.BoardServiceImpl;
import com.green.BasicBoard.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
>>>>>>> 9b1e33564e4cf34fc4ddf00d2aa022056a783923
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Resource(name= "boardService")
    private BoardService boardService;

<<<<<<< HEAD
=======
    @Autowired
    private BCryptPasswordEncoder encoder;

>>>>>>> 9b1e33564e4cf34fc4ddf00d2aa022056a783923
    //회원가입 페이지 이동
    @GetMapping("/joinForm")
    public String joinForm(){

        return "join";
    }

    //회원가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
<<<<<<< HEAD
    boardService.join(memberVO);
=======
        //비밀번호 암호화

       String encodePw= encoder.encode(memberVO.getMemberPw());
       memberVO.setMemberPw(encodePw);

        boardService.join(memberVO);
>>>>>>> 9b1e33564e4cf34fc4ddf00d2aa022056a783923
        return "redirect:/loginForm";
    }
    //로그인 페이지 이동
    @GetMapping("/loginForm")
    public String loginForm(){
        return "login";
    }

<<<<<<< HEAD
    //로그인
    @PostMapping("/login")
    public String login(MemberVO memberVO,HttpSession session){
    MemberVO loginInfo= boardService.login(memberVO);

    if(loginInfo!=null){
        session.setAttribute("loginInfo",loginInfo);
    }
        return "redirect:/";
    }
    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");

        return "redirect:/";

    }
=======

>>>>>>> 9b1e33564e4cf34fc4ddf00d2aa022056a783923
}
