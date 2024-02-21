package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.ReplyVO;

import java.util.List;

public interface ReplyService {

    void replyInput(ReplyVO replyVO);
    //void input(BoardVO boardVO);//가능
    // 매개변수로 들어가는 빈값 채워주는 것-> boarVO에도 content writer boardNum 가지고 있기 때문

    List<ReplyVO> replySelect(int boardNum);
//    WHERE BOARD_NUM= #{boardNum}

}
