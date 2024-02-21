package com.green.Board.vo;
//데이터 저장할 수 있는 변수 만들어 주기(변수명은 컬럼명과 동일하게)


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class BoardVO {
    private int boardNum;
    private String boardTitle;
    private String boardContent;
    private String writer;
    private String createDate;
    private int readCnt;

}
