package com.green.car.controller;

import com.green.car.service.CarService;
import com.green.car.service.CarServiceImpl;
import com.green.car.vo.CarVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CarController {
@Resource(name="carService")
private CarServiceImpl carService;

    @GetMapping("/")
    public String page (){

        return "content/car/main";
    }
    @GetMapping("/manage")
    public String manage(CarVO carVO){

        System.out.println(carVO);

        return "content/car/car_manage";
    }
    @PostMapping("/carManage")
    public String carManage(CarVO carVO){

        carService.insertCar(carVO);
        System.out.println(carVO);
        return "content/car/car_manage";
    }



}
