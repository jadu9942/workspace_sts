package com.green.Board2.service;

import com.green.Board2.vo.ReplyVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLPermission;
import java.util.List;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    private SqlSessionTemplate sqlSession;
    //Always copy&paste

    @Override
    public void replyInput(ReplyVO replyVO) {
       sqlSession.insert("replyMapper.replyInput",replyVO);
    }

    @Override
    public List<ReplyVO> replySelect(int boardNum) {
        return sqlSession.selectList("replyMapper.replySelect",boardNum);

    }


}
