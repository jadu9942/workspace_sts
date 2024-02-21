package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements  CartService{
    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public void insertCart(CartVO cartVO) {
        sqlSession.insert("cartMapper.insertCart",cartVO);
    }

    @Override
    public List<CartViewVO> selectCartList(String memberId) {
        return sqlSession.selectList("cartMapper.selectCartList", memberId);
    }

    @Override
    public void deleteCart(int cartCode) {
         sqlSession.delete("cartMapper.deleteCart",cartCode);
    }
}
