package com.green.shop.security;


import com.green.shop.member.service.MemberService;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
    @Resource(name="memberService")
    private MemberService memberService;

    //security가 로그인 프로세스를 진행하면 가장 먼저 호출되는 메소드
    //실제 로그인을 처리하는 메소드는 아님
    //security가 로그인을 처리할 수 있도록 로그인 정보를 security에게 전달하는 역할!!!!!!!
    //->login.html에서 로그인 페이지 알려주면 security가 로그인 할 때 최초로 실행되는 메소드
    @Override
    public UserDetails loadUserByUsername(String username)  {
        //로그인을 시도하는 유저의 정보(id, pw, 권한)를 조회
        MemberVO loginInfo= memberService.login(username); //로그인 하려는 사람의 id/  우리가 input태그에 입력한 아이디를 username에 담아서 가져옴

        //아이디 조회 안돼서 null일 때 오류 발생
        if(loginInfo==null){
            throw new BadCredentialsException("error");//security로 로그인하는데 로그인 정보가 없을 때 오류 그래서 밑에 코드 실행 X
        }

        //로그인 하려는 user 정보를 security에 넘겨 줌
        User user = (User)User.builder()
                    .username(loginInfo.getMemberId()) //로그인 시도하려는 사람의 아이디
                    .password("{noop}"+loginInfo.getMemberPw())
                    .roles(loginInfo.getMemberRoll())
                    .build();


        return user; //리턴되는 데이터는 로그인 하려는 user 정보 그럼 security가 그 정보 받아가서 로그인
    }
}
