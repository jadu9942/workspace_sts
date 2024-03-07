package com.green.StudentManager.controller;

import com.green.StudentManager.service.StuService;
import com.green.StudentManager.service.StuServiceImpl;
import com.green.StudentManager.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    @Resource(name= "stuService")
    private StuServiceImpl stuService;

    //학생 목록 페이지로 이동
    @GetMapping("/")
    public String stuList(Model model){
        //학생 목록 조회
        List<StuVO> list =stuService.selctStuList();

        //조회한 목록을 html로 전달

        model.addAttribute("stuList",list);
        //전달할 데이터 뭐로 보낼래
        return "stu_list";
//        html파일로 전달 바로 위 가지고 갈 데이터 있음

    }
    @GetMapping("/regStu")
    public String regStu(){
        return "reg_student";
    }

    @PostMapping("/regStu")
    public String reg(StuVO stuVO){
        //학생 등록
        stuService.insertStu(stuVO);


        //mapper가서 쿼리 작성
        return "redirect:/";
        //return "redirect:/"; controller로 다시 가라
        // return "stu_list"; 이것만 쓰면 가지고 갈 데이터 없기 때문에 화면 출력 안됨

    }

    @GetMapping("/stuDetail")
    public String stuDetail(@RequestParam(name = "stuNo")int stuNo, Model model){
        //학생의 상세정보 조회
        StuVO stu=stuService.selectStu(stuNo);
        model.addAttribute("stuInfo", stu);

        //조회한 데이터를 html로 전달
        return "student_detail";

    }
    @GetMapping("/goDelete")
    public String delete(StuVO stuVO){
        //학생 삭제
        //학생 목록 데이터 조회
        stuService.deleteStu(stuVO.getStuNo());
        return "redirect:/";
        // controller로 이동
    }





}

