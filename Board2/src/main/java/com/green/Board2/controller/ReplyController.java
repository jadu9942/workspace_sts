package com.green.Board2.controller;

import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.ReplyVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Resource(name="replyService")
    //replyService 안에 있는 @replyServiceImpl 안에 있는 글자와 동일해야 함
    private ReplyServiceImpl replyService;

    //댓글 등록
    @PostMapping("/regReply")
    public String regReply(ReplyVO replyVO, HttpSession session){
      MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
      replyVO.setWriter(loginInfo.getMemberId());
      //로그인한 사람의 정보 중 작성자 데이터
        //(세션에 loginInfo) 로그인 한사람의 아이디 이름 관리자여부
        replyService.replyInput(replyVO);
        // 빈값채울 데이터 있어야 함. 여기 데이터는 내용과 글번호만 있음 작성자 데이터는 없음

        return "redirect:/board/boardDetail?boardNum=" + replyVO.getBoardNum();
                                  // replyVO 안에 있는 BoardNum 데이터 가져가기
    }

}
