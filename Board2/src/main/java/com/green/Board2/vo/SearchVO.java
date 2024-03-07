package com.green.Board2.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVO extends PageVO{
    // 상속 SearchVO는 PageVO 가짐
    // 메소드 호출 가능하게 함
    private String searchType;
    private String searchValue;

}
