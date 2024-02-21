package com.green.Board.service;

import com.green.Board.vo.BoardVO;
import lombok.Setter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
//BoaServiceImpl가 기본 패키지 안에 있을 때 @Service 어노테이션 사용 가능
public class BoaServiceImpl implements BoaService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<BoardVO> selectBoardList() {
        List<BoardVO> list= sqlSession.selectList("boardMapper.selectBoardList");
        //(실행할 쿼리 아이디(쿼리 아이디 뿐만 아니라 네임스페이스까지 넣어주는 것), )
        //<mapper namespace="boardMapper">
        return list;
        //board Controller에서 객체생성
    }

    @Override
    public void insertBoard(BoardVO boardVO) {
        sqlSession.insert("boardMapper.insertBoard",boardVO);
    }

    @Override
    public BoardVO selectBoardDetail(int boardNum) {
        BoardVO result =sqlSession.selectOne("boardMapper.selectBoardDetail",boardNum);
        return result;
    }

    @Override
    public void deleteBoard(int boardNum) {
        sqlSession.delete("boardMapper.deleteBoard",boardNum);
    }

    @Override
    public void updateBoard(BoardVO boardVO) {
        sqlSession.update("boardMapper.updateBoard", boardVO);
    }
}
