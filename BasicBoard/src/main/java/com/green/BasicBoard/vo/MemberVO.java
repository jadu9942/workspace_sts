package com.green.BasicBoard.vo;

import lombok.*;
import org.springframework.stereotype.Service;

//생성자를 구현하는 어노테이션
//@NoArgsConstructor //매개변수 없는 기본 생성자
//@AllArgsConstructor //멤버변수 모두를 매개변수로 받는 생성자
//@RequiredArgsConstructor // final


// -----------------------
//data builder 같이 만들면 기본 생성자 생성 안 됨.
@Data //기본생성자, setter, getter, toString 등을 생성
@Builder //기본생성자, 매개변수를 4개를 다 갖는 생성자 (다양항 생성자 쓰기 위해 필요)
@NoArgsConstructor //
@AllArgsConstructor
public class MemberVO {
    private String memberId; //final 상수
    private String memberName;
    private String memberPw;
    private String memberRoll;
}

class BuilderTest{
    public void test(){
        //id를 java라는 초기값으로 갖는 객체 생성
        MemberVO vo1= MemberVO.builder()
                            .memberId("java")
                            .build();

        MemberVO v2= MemberVO.builder()
                            .memberId("java")
                            .memberName("hong")
                            .build();

        MemberVO v3= MemberVO.builder()
                            .memberName("hong")
                            .memberPw("1111")
                            .memberRoll("USER")
                            .build();

        MemberVO v4= MemberVO.builder().build();
    }
}
