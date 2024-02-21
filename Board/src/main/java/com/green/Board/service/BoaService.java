package com.green.Board.service;

import com.green.Board.vo.BoardVO;

import java.util.List;

public interface BoaService {

   //게시글 목록 조회
   List<BoardVO> selectBoardList();
   //리턴 타입> 쿼리 실행결과 어떻게 가져오나
   //매개변수> 쿼리 빈공간 채우기
//   boardVO 안에 여러 개(데이터) 있으면 한번에 가져올 수 있음

   //게시글 등록
   void insertBoard(BoardVO boardVO);

   //게시글 상세조회
   BoardVO selectBoardDetail(int boardNum);

   //게시글 삭제
   void deleteBoard(int boardNum);

   //게시글 정보 수정
   void updateBoard(BoardVO boardVO);






}
