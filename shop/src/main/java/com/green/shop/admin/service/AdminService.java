package com.green.shop.admin.service;

import com.green.shop.admin.vo.SearchBuyVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;

import java.util.List;

public interface AdminService {

    //상품 등록 + 이미지 등록
    void insertItem(ItemVO itemVO);

    //다음에 들어갈 ITEM_CODE 조회
    int selectNextItemCode();

    //관리자용 구매 목록 조회
    List<BuyVO> selectBuyList(SearchBuyVO searchBuyVO);

    //구매 상세 정보 조회(모달창)
   BuyVO selectBuyDetail(int buyCode);

//    //상품 변경 화면에서 상품 목록조회
//    List<ItemVO> itemList();

    //상품 변경 화면에서 상품 목록조회
    List<ItemVO> selectItemList();

    //상품 상세 정보 조회(정보변경에서)
    ItemVO selectItemDetail(int itemCode);

    //상품 정보 변경
    void updateItem(ItemVO itemVO);
}
