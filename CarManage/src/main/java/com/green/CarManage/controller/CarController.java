package com.green.CarManage.controller;

import com.green.CarManage.service.CarServiceImpl;
import com.green.CarManage.vo.CarVO;
import com.green.CarManage.vo.SalesVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class
CarController {
@Resource(name="carService")
private CarServiceImpl carService;

    @GetMapping("/")
    public String page (){

        return "content/car/main";
    }
    //차량 관리 화면
    @GetMapping("/manage")
    public String manage(Model model){
         List<CarVO> carList = carService.selectCarList();
        System.out.println(carList);
        model.addAttribute("carList", carList);


        return "content/car/car_manage";
    }
    //차량 등록
    @PostMapping("/carManage")
    public String carManage(CarVO carVO){

        carService.insertCar(carVO);
        System.out.println(carVO);
        return "redirect:/manage";
    }

    //판매 정보 등록
    @GetMapping("/salesInfo")
    public String salesInfo(Model model){
        List<CarVO> carList = carService.selectCarList();
        model.addAttribute("carList", carList);

        return "content/car/sales_info";
    }


    //판매 목록(?)
    @PostMapping("/carList")
    public String carList(Model model, SalesVO salesVO){
        carService.insertSaleInfo(salesVO);


        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<SalesVO> list=carService.selectSaleList();
        model.addAttribute("list",list);
        return "content/sales/car_list";
    }



}
