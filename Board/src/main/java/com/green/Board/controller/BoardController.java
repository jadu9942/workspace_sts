package com.green.Board.controller;

import com.green.Board.service.BoaServiceImpl;
import com.green.Board.vo.BoardVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {
    @Resource(name= "boardService")
    //밑에 선언한 변수에 대해서 만들어진 객체 있으면 사용해라
    private BoaServiceImpl boaService;


    //게시글 목록 페이지로 이동
    @GetMapping("/")
    public String boardList(Model model) {
        // 목록 데이터 조회 후 html 전달
        List<BoardVO> boardList =boaService.selectBoardList();
        model.addAttribute("boardList",boardList);
        return "board_list";
    }
    //글쓰기 페이지로 이동
    @GetMapping("/boardWriteForm")
    public String boardWriteForm(){
        return "board_write_form";
        //db작업 필요없음 데이터 가지고 갈 거 없어서

    }
    //글등록
    @PostMapping("/boardWrite")
    public String boardWrite(BoardVO boardVO){
        //게시글 insert 하기
        boaService.insertBoard(boardVO);
        return "redirect:/";
        //html이 아니라 뒤에 있는 요청 경로 컨트롤러로 감
    }
    //게시글 상세보기 페이지로 이동
    @GetMapping("/boardDetail")
    public String boardDetail(BoardVO boardVO, Model model){
        //게시글 상세 정보 조회 + 조회한 데이터를 html로 전달
        BoardVO board =boaService.selectBoardDetail(boardVO.getBoardNum());
//                                  조회하고자 하는 글의 번호
        model.addAttribute("board",board);
        return "board_detail";
    }
    @GetMapping("/deleteBoard")
    public String deleteBoard(BoardVO boardVO){
        //게시글 삭제
          boaService.deleteBoard(boardVO.getBoardNum());

          return "redirect:/";
    }
    //수정 페이지로 이동
    @GetMapping("/updateBoardForm")
    public String updateBoardForm(BoardVO boardVO,Model model){
        //수정하고자 하는 게시글의 데이터를 조회(수정 전 데이터 보여줘야 함)+ html로 전달
        BoardVO board = boaService.selectBoardDetail(boardVO.getBoardNum());
        model.addAttribute("board",board);

        return "update_form";
    }
    //게시글 수정
    @PostMapping("/updateBoard")
    public String updateBoard(BoardVO boardVO){
        //게시글 수정
        boaService.updateBoard(boardVO);

        return "redirect:/boardDetail?boardNum="+boardVO.getBoardNum();

    }


    }








