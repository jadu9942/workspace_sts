package com.green.DbTest.controller;

import com.green.DbTest.service.StudentService;
import com.green.DbTest.service.StudentServiceImpl;
import com.green.DbTest.vo.StudentVO;
import jakarta.annotation.Resource;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    @Resource(name="stuService")
    private StudentService studentService;
    //localhost:8081
    @GetMapping("/")
    public String insertTest(){
        //학생 한 명 등록
//        studentService.insertStudent();

        //학생 한 명 삭제
//        studentService.deleteStudent();
//        String name= studentService.selectName();
//        int score= studentService.selectScore();
//        System.out.println("name="+name);
//        System.out.println("score="+score);

//        StudentVO vo=studentService.selectStu();
//        System.out.println(vo);
        List<StudentVO> list= studentService.selectStuList();
        for(int i=0; i<list.size();i++){
            System.out.println(list.get(i));
        }
        //쿼리 작업하고 html 열리는 거>>
        return "insert";
    }
    @GetMapping("/delete")
    public String delete(){
        return "delete";
    }
    @PostMapping("/deleteResult")
    public String deleteResult(@RequestParam(name="stuNo") int stuNo){
        //학생 삭제 기능
        studentService.delete(stuNo);
        return "delete_result";

    }
}
//1. 이동하고자 하는 html로 갈 때 쿼리 기능이 필요한 지 판단
//2. 무조건 쿼리 작성 mapper.xml
//3. 작성한 쿼리를 실행시킬 메소드를 인터페이스에서 정의
//4. 인터페이스를 구현한 클래스에서 메소드를 구현
//5. controller의 메소드에서 쿼리 실행을 위해 만든 메소드를 호출