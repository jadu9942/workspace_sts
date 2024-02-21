package com.green.shop.buy.service;

import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.vo.CartVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("buyService")
public class BuyServiceImpl implements BuyService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int selectNextBuyCode() {
        return sqlSession.selectOne("buyMapper.selectNextBuyCode");
    }

    @Transactional(rollbackFor = Exception.class) //쿼리 둘 다 한꺼 번에 실행하기 위해
    @Override
    public void insertBuys(BuyVO buyVO, CartVO cartVO) {
        sqlSession.insert("buyMapper.insertBuy", buyVO);
        sqlSession.insert("buyMapper.insertBuyDetails", buyVO);
        sqlSession.delete("cartMapper.deleteCarts", cartVO);

    }

    @Transactional(rollbackFor = Exception.class) // select는 transactional 필요없음. 데이터에 변화 없기 때문
    @Override
    public void insertBuy(BuyVO buyVO, BuyDetailVO buyDetailVO) { // 쿼리 두개 실행
        sqlSession.insert("buyMapper.insertBuy",buyVO);
        sqlSession.insert("buyMapper.insertBuyDetail", buyDetailVO);
    }

    @Override
    public List<BuyVO> selectBuyList(String memberId) {
        return sqlSession.selectList("buyMapper.selectBuyList",memberId);
    }
}
