package com.green.FetchStudent.controller;

import com.green.FetchStudent.service.StuServiceImpl;
import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.DetailVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StuController {
    @Resource(name= "stuService")

    private StuServiceImpl stuService;

    //객체 만든 것
    @GetMapping("/")
    public String main(Model model, StuVO stuVO){
//                             StuVO는 classCode는 int


        //학급 목록 데이터 조회해서 데이터 넘겨주기
        List<ClassVO> classList =stuService.selectClassList(); // classCode, className
        model.addAttribute("classList", classList);

        //학생 목록 데이터 조회
        List<StuVO> stuList= stuService.selectStuList(stuVO);
        model.addAttribute("stuList",stuList);

        return "stu_manage";
    }
    @ResponseBody
    @PostMapping("/fetchSelect")
    public List<StuVO> fetchSelect(StuVO stuVO){
        //학생 목록 데이터 조회
        List<StuVO> stuList= stuService.selectStuList(stuVO);
        return stuList;

    }
    @ResponseBody
    @PostMapping("/detail")
    public DetailVO detail(@RequestParam(name="stuNum")int stuNum){
         // 클릭한 학생의 상세 정보 조회
        StuVO stuInfo=stuService.selectStuDetail(stuNum);


         // 클릭한 학생의 점수 정보도 조회
        ScoreVO scoreInfo= stuService.selectScoreInfo(stuNum);

        DetailVO result=new DetailVO();
        result.setStuVO(stuInfo);
        result.setScoreVO(scoreInfo);

        //조회한 데이터를 가지고 자바 스크립트로 이동


        //자바 기본으로는 리턴되는 데이터는 하나밖에 안되지만(각각)
        //배열 자료형으로 넘겨줄 수 있다(대신에 똑같은 자료형으로)
        // 그래서 클래스로 넘길 수 있다(가져갈 통으로 만들기)
        return result;
    }
    @ResponseBody
    @PostMapping("/insertScore")
    public void insertScore(ScoreVO scoreVO){
        stuService.insertScore(scoreVO);
    }

    }

