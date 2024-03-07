package com.green.shop.admin.controller;

import com.green.shop.item.service.ItemService;
import com.green.shop.item.vo.CategoryVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 이 컨트롤러 안에 들어가는 건 전부 비동기됨
@RequestMapping("/rest")
public class ReactController {
    @Resource(name="itemService")
    private ItemService itemService;

    @GetMapping("/test1")
    public String test1(){

        return "test.html"; // 데이터 보내주는 거
    }

    @GetMapping("/test2")
    public List<CategoryVO> test2(){
        itemService.selectCategoryList();

        return itemService.selectCategoryList();
    }
}
