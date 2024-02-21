setFinalPrice(); //페이지 뜨면 바로 실행 (밑에 만들어놓은 함수 호출)



//총 가격 계산 함수
function setFinalPrice(){
    //체크된 장바구니 상품의 총 가격을 모두 더해서 계산

    //클래스가 chk인 태그 중에서 체크가 된 태그만 선택
    const chks= document.querySelectorAll('.chk:checked'); //.chk:checked-> chk중에서 체크 된 것 만 가지고 온다.

    let finalPrice=0;
    chks.forEach(function(chk, i){//chks중에서 chk로 반복, i=>반복 횟수
        // chk 각각의 같은 행에 있는 총 가격 데이터를 찾아가기
        const strPrice=chk.closest('tr').children[5].textContent; // closest현재 내가 감싸고 있는 태그 중 가장 가까운 태그 찾아가기/ 자식 태그 중6번째 

        //정규식을 사용해서 쉼표와 원화표시를 제거
        const regex=/[^0-9]/g;
        const price = parseInt(strPrice.replace(regex,'')); // ￦20,000 -> 20000    숫자가 아닌 것 빈값으로 바꾸겠다
        finalPrice= finalPrice + price;

    }); 

    document.querySelector('#finalPrice-span').textContent = '￦'+ finalPrice.toLocaleString(); //toLocaleString: 원화표시







    // const chkArr=[];
    // for(const chk of cks){  // 체크된 건 true 체크 안 된 건 false
    //     if(chks.checked){
    //         chkArr.push(chk);
    //     }
    // }

}

//제목 줄 체크 박스 체크 및 해제 시 모든 체크 박스 체크 및 해제
function checkAll(){
    const chkAll = document.querySelector('#chkAll'); //전체 체크박스
    const chks = document.querySelectorAll('.chk'); //밑에 있는 체크 박스들

    if(chkAll.checked){
        for(const chk of chks){
            chk.checked =true;
        }
    }
    else{
        for(const chk of chks){
            chk.checked =false;
        }
    }

    setFinalPrice();
}

//삭제 버튼 클릭 시 장바구니에서 삭제




//장바구니 상품의 수량 변경 








