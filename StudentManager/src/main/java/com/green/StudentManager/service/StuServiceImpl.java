package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StuServiceImpl implements StuService{
    @Autowired
    private SqlSessionTemplate sqlSession;
    @Override
    public int insertStu(StuVO stuVO ) {
       int result= sqlSession.insert("insertStu",stuVO);
        return result;
    }

    @Override
    public List<StuVO> selctStuList() {
        List<StuVO> list =sqlSession.selectList("selectStuList");
//        (실행시킬 쿼리 아이디,쿼리 빈값 채울 것 )
        return list;
//       = return sqlSession.selectList("selectStuList");
    }

    @Override
    public StuVO selectStu(int stuNo) {
        return sqlSession.selectOne("selectStu",stuNo);
    }

    @Override
    public int deleteStu(int stuNo) {
        return sqlSession.delete("deleteStu",stuNo);
    }
}
