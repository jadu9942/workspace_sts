package com.green.shop.admin.controller;

import com.green.shop.admin.service.AdminService;
import com.green.shop.admin.service.AdminServiceImpl;
import com.green.shop.admin.vo.SearchBuyVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.member.vo.MemberVO;
import com.green.shop.util.ConstantVariable;
import com.green.shop.util.UploadUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.codehaus.groovy.classgen.ReturnAdder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource(name = "itemService")
    private ItemServiceImpl itemService;

    @Resource(name = "adminService")
    private AdminServiceImpl adminService;

    //상품 등록 페이지로 이동
    @GetMapping("/regItemForm")
    public String regItemForm(Model model){
        //카테고리 목록 조회
        List<CategoryVO> categoryList = itemService.selectCategoryList();
        model.addAttribute("categoryList", categoryList);

        model.addAttribute("page",2);
        return "content/admin/reg_item_form";
    }

    //상품 등록
    @PostMapping("/regItem")
    public String regItem(ItemVO itemVO
                        , @RequestParam(name = "mainImg") MultipartFile mainImg
                        , @RequestParam(name = "subImgs") MultipartFile[] subImgs){
        //---------------------파일 첨부 기능-----------------//
        //메인 이미지 하나 업로드
        ImgVO mainImgVO = UploadUtil.uploadFile(mainImg);

        //상세 이미지들 업로드
        List<ImgVO> imgList = UploadUtil.multiUploadFile(subImgs);

        //-------- 다음에 들어갈 ITEM-CODE 조회 -------//
        int itemCode = adminService.selectNextItemCode();

        //------------------ 상품 등록 쿼리 실행 ---------------//
        itemVO.setItemCode(itemCode);


        //------------------ 상품 이미지 정보 등록 쿼리 실행 ---------------//

        //현재 itemVO는 상품명, 가격, 상품소개, cateCode

        //이미지 등록시 채워줘야하는 모든 데이터를 갖는 리스트 생성

        mainImgVO.setItemCode(itemCode);
        for(ImgVO img : imgList){
            img.setItemCode(itemCode);
        }
        imgList.add(mainImgVO);
        itemVO.setImgList(imgList);

        System.out.println(itemVO);
        adminService.insertItem(itemVO);

        return "redirect:/admin/regItemForm";
    }

    //구매 목록 조회
    @RequestMapping("/history")
    public String history(Model model, SearchBuyVO searchBuyVO){
        // 관리자용 구매 목록 조회

        System.out.println("aaaaaaaa"+searchBuyVO);

        List<BuyVO> buyList =adminService.selectBuyList(searchBuyVO);
        model.addAttribute("buyList", buyList);

        model.addAttribute("page",1);

        return "content/admin/admin_history";

    }
    //상세 구매 내역 조회
    @ResponseBody
    @PostMapping("/selectBuyDetail")
    public BuyVO selectBuyDetail(@RequestParam(name="buyCode")int buyCode){
        //구매 상세 내역 조회
        BuyVO buyVO =adminService.selectBuyDetail(buyCode);
        return buyVO;


    }
    //상품 정보 변경
    @RequestMapping("/itemChange")
    public String itemChange(Model model, @RequestParam(name="itemCode", required = false, defaultValue ="0")int itemCode, ItemVO itemVO){

        model.addAttribute("itemLists", adminService.selectItemList());

//        List<ItemVO> itemList= adminService.itemList();
//        model.addAttribute("itemList", itemList);

         model.addAttribute("page",4);
         model.addAttribute("updateItemCode", itemCode);
        return "content/admin/update_item";
    }

    //상품 정보 변경 화면의 상품 목록 테이블의 행 클릭 시
    //상품의 상세 정보를 조회 하는 비동기
    @ResponseBody
    @PostMapping("/selectItemDetail")
    public Map<String, Object> selectItemDetail(@RequestParam(name="itemCode")int itemCode){
        //상품 상세조회
       ItemVO itemDetail= adminService.selectItemDetail(itemCode);

       //카레고리 목록 조회
        List<CategoryVO> cateList =itemService.selectCategoryList();

        //위 두 데이터를 담을 수 있는 map 객체 생성
        Map<String, Object> map= new HashMap<>();
        map.put("itemDetail", itemDetail);
        map.put("cateList", cateList);

        return map;
    }

    //상품 정보 변경
    @PostMapping("/updateItem")
    public String updateItem(ItemVO itemVO){
        adminService.updateItem(itemVO);
        return "redirect:/admin/itemChange?itemCode=" +itemVO.getItemCode();
    }


    }



