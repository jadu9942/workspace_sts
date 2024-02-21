package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;

import java.util.List;

public interface StuService {
    //SELECT 리턴: 매번 바뀜
    //INSERT, DELETE,UPDATE: void 혹은 int(정석) 사용
    // EX) INSERT된 개수 영향 받은 데이터의 개수

    //리턴타입: 쿼리 실행 결과를 받아오는 것
    //insert, update,delete는 쿼리 실행 결과가
    //몇개의 행이 삽입, 삭제, 수정 되었는지 보여줌
    //그래서 위 세개의 쿼리 결과 리턴 타입은 무조건 int로 고정 혹은 void

    //select는 어떤 쿼리인지에 따라 리턴타입이 달라짐
    //조회 결과 데이터가 0행 혹은 1행: vo클래스 사용
    //조회 결과 데이터 행의 개수가 매번 다르다: List<VO>

    //매개변수: 쿼리 실행 시 빈값을 채우는 역할(실행 전 실행시키기 위해서 기입)
    //빈 값을 채울 데이터가 0개, 1개, 여러 개 구분 해야 함
    //빈 값 0개: 매개 변수 필요없음
    //빈 값 1개: 두가지 경우 있음
    //1) 빈값의 자료형 숫자인 경우
    //   매개변수로 int 자료형 하나
    //2) 빈값의 자료형이 문자열인 경우
   //    매개변수로 String 자료형 하나
   //빈값이 여러 개인 경우는 vo객체

    //학생 등록
    int insertStu(StuVO stuVO);

    //학생목록조회
    List<StuVO> selctStuList();

    //학생 상세정보 조회
    StuVO selectStu(int stuNo);

    //학생 정보   삭제
    int deleteStu(int stuNo);



//    SELECT *
//    FROM stu
//    WHERE STU_NO=? AND STU_NAME=?
//   메소드>> StuVO fdfd(StuVO stuVO);



}
