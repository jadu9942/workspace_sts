package com.green.Board2.service;

import com.green.Board2.vo.ReplyVO;

import java.util.List;

public interface ReplyService {
    void insertReply(ReplyVO replyVO);
    List<ReplyVO> selectReplyList(int boardNum);


}
