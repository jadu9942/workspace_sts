package com.green.Board2.controller;

import com.green.Board2.service.BoardServiceImpl;
import com.green.Board2.service.ReplyService;
import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.ReplyVO;
import com.green.Board2.vo.SearchVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Resource(name="boardService")
    private BoardServiceImpl boardService;

    @Resource(name="replyService")
    private ReplyServiceImpl replyService;

    //목록조회, 글쓰기, 상세조회, 수정, 삭제

    //게시글 목록 페이지
    @RequestMapping("/list")
    // get postMapping 둘 다 가능

    // 커맨드 객체(컨트롤러에서 html로 넘어갈 때 데이터 받기 위해 가져온 것)가 자동으로 넘어왔기 때문에 데이터 쓸 수 있는 것
    //  객체.
    public String boardList(SearchVO searchVO, Model model){
        //전체 데이터 수 이게 있어야 밑에 페이지 정보 세팅 메소드 실행 할 수 있음
        int totalDataCnt= boardService.selectBoardCnt();
        searchVO.setTotalDataCnt(totalDataCnt);

        //페이지 정보 세팅 (위해서는 전체 데이터 수 조회해야 하고 데이터 조회 필요)
        searchVO.setPageInfo();

        model.addAttribute("boardList", boardService.selectBoardList(searchVO));
        return "list";
        //주소창 검색 클릭으로 올 수 있음
    }

    //글쓰기 페이지로 이동
    @GetMapping("/writeForm")
    public String writeForm(){
        return "write_form";
    }

    @PostMapping("/write")
    public String write(BoardVO boardVO, HttpSession session){
        //세션에 저장된 로그인한 유저의 아이디로 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        //boardVO에 작성자 데이터 저장
        boardVO.setWriter(loginInfo.getMemberId());
        boardService.insertBoard(boardVO);

        return "redirect:/board/list";
    }
    // 상세정보조회
    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam(name = "boardNum")int boardNum, Model model){


        //조회수 증가 (상세조회 갈 때 조회수 증가시켜주면 되니 여기 작성)
        boardService.updateReadCnt(boardNum);
        //게시글 조회수 늘리고 싶은 글번호
        //조회수부터 증가시키고 싶으면 먼저 작성

        //상세정보조회
        BoardVO vo = boardService.selectBoardDetail(boardNum);
        //                                         상세보기 할 글번호
        model.addAttribute("board", vo);

        //댓글 조회
        List<ReplyVO> replyList = replyService.replySelect(boardNum);
        model.addAttribute("replyList",replyList);


        return "board_detail";
        //상세보기할 때 쿼리 두개 실행
        //게시글 상세 정보 조회 그리고 조회수 증가
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(name="boardNum") int boardNum){
        boardService.deleteBoard(boardNum);
        return "redirect:/board/list";
    }


}
