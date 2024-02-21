package com.green.CarManage.service;

import com.green.CarManage.vo.CarVO;
import com.green.CarManage.vo.SalesVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carService")
public class CarServiceImpl implements CarService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertCar(CarVO carVO) {
        sqlSession.insert("carMapper.insertCar", carVO);
    }

    @Override
    public List<CarVO> selectCarList() {
        return sqlSession.selectList("carMapper.selectCarList");
    }

    @Override
    public void insertSaleInfo(SalesVO salesVO) {
        sqlSession.insert("carMapper.insertSaleInfo",salesVO);
    }

    @Override
    public List<SalesVO> selectSaleList() {
        return sqlSession.selectList("carMapper.selectSaleList");
    }
}





