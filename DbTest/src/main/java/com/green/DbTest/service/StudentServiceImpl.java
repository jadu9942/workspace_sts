package com.green.DbTest.service;

import com.green.DbTest.vo.StudentVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//implements 클래스 구현
@Service("stuService")
public class StudentServiceImpl implements StudentService{
    //Mybatis에서 제공하는 데이터베이스 쿼리 기능이 정의되어있는 객체(쓰면 쿼리 작업가능 )
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    //학생 한 명을 저장하는 기능
    //디비작업: 조회 삽입 삭제 수정
    //삽입: sqlSession.insert("실행 할 쿼리 id",[쿼리에서 빈 값을 채울 데이터]);
    //삭제: sqlSession.delete("실행 할 쿼리 id",[쿼리에서 빈 값을 채울 데이터]);
    //수정: sqlSession.update("실행 할 쿼리 id",[쿼리에서 빈 값을 채울 데이터]);
    //조회: sqlSession.selectOne("실행 할 쿼리 id",[쿼리에서 빈 값을 채울 데이터]);
    //   : sqlSession.selectList("실행 할 쿼리 id",[쿼리에서 빈 값을 채울 데이터]);   둘 중 하나 필요에 맞게 사용
    //                                   mapper inert id에서, 값 없으면 안 들어가도 됨
    // selectOne, selectList 차이
    //selectOne: 조회 결과 데이터가 0행 혹은 1행일 경우
    //selectList: 조회 할 때마다 조회 되는 행의 개수가 매번 다른 경우
    public void insertStudent() {
        sqlSession.insert("insertStudent");
    }

    @Override
    public void deleteStudent() {
        sqlSession.delete("deleteStudent");
    }

    @Override
    public void delete(int stuNo) {
        sqlSession.delete("delete",stuNo);
    }

    @Override
    public String selectName() {
        String name= sqlSession.selectOne("selectName");
        return name;
    }

    @Override
    public int selectScore() {
        int score= sqlSession.selectOne("selectScore");
        return score;
    }
//쿼리실행하려고 만든 메소드
    @Override
    public StudentVO selectStu() {
        StudentVO stu= sqlSession.selectOne("selectStu");
        return stu;
    }

    @Override
    public List<StudentVO> selectStuList() {
        List<StudentVO> list =sqlSession.selectList("selectStuList");
        return list;
    }

}
