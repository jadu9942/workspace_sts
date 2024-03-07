package com.green.shop.buy.vo;

import com.green.shop.item.vo.ItemVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BuyDetailVO {
    private int buyDetailCode;
    private int itemCode;
    private int buyCnt;
    private int totalPrice;
    private int buyCode;
    private ItemVO itemVO;
}
