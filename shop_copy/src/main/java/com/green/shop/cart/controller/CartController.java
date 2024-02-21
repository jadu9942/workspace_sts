package com.green.shop.cart.controller;

import com.green.shop.cart.service.CartService;
import com.green.shop.cart.service.CartServiceImpl;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Resource(name = "cartService")
    private CartServiceImpl cartService;


    //장바구니 상품 등록
//    @GetMapping("/insertCart")
//    public String insertCart(){
//        cartService.insertCart();
//    }



    //장바구니 목록 조회
    @GetMapping("/list")
    public String list(HttpSession session, Model model){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        List<CartViewVO> cartList = cartService.selectCartList(loginInfo.getMemberId());
        model.addAttribute("cartList", cartList);

        // 총 가격을 계산한 후 html로 전달
        int sum=0;
        for( CartViewVO e : cartList){
            sum= sum + e.getTotalPrice(); //상품 하나하나에 대한 총 가격

        }
        //model.addAttribute("finalPrice",sum); //총 가격 데이터 html로넘겨주기

        return "content/cart/cart_list";
    }

    //장바구니에서 상품 하나 삭제
    @GetMapping("/deleteCart") //location.href는 get방식으로 넘어옴
    public String deleteCart(@RequestParam(name= "cartCode")int cartCode){
        cartService.deleteCart(cartCode);

        return "redirect:/cart/list"; //장바구니 목록 페이지로 다시 이동
    }

}





class Aaa{
    public void aaa(){

    }
}

class Bbb{
    public void bbb(){
        Aaa a=new Aaa();
        a.aaa();
    }
}




