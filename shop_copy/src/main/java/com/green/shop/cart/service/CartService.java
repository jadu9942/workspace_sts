package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;

import java.util.List;

public interface CartService {

    //장바구니 등록
    void insertCart(CartVO cartVO);

    //장바구니 목록 조회
    List<CartViewVO> selectCartList(String memberId);



    //장바구니 기존 상품 개수 조회


    //장바구니 상품 하나 삭제
    void deleteCart(int cartCode);
}
