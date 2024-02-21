package com.green.shop.admin.controller;

import com.green.shop.admin.service.AdminService;
import com.green.shop.admin.service.AdminServiceImpl;
import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.util.ConstantVariable;
import com.green.shop.util.UploadUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

}
