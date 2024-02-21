package com.green.CarManage.service;


import com.green.CarManage.vo.CarVO;
import com.green.CarManage.vo.SalesVO;

import java.util.List;

public interface CarService {
    //차량 등록
    void insertCar(CarVO carVO);

    //차량 조회
    List<CarVO> selectCarList();

    // 판매 등록
    void insertSaleInfo(SalesVO salesVO);

    //판매 리스트 조회
    List<SalesVO> selectSaleList();






}
