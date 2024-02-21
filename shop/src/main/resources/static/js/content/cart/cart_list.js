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
function delteCart(cartCode){

    if(confirm('선택한 상품을 장바구니에서 삭제할까요?')){ //확인 누르면 true 그럼 if문 실행
        location.href= `/cart/deleteCart?cartCode=${cartCode}`;
    }
}



//장바구니 상품의 수량 변경 
function changeCnt(cartCode, selectedTag, itemPrice){ //controller로 넘겨주기 위해 매개변수로 cartCode넘겨주기
    const cartCnt =selectedTag.closest('.row').querySelector('input[type="number"]').value; //태그.querySelector
     console.log(cartCnt);

 fetch('/cart/changeCnt', { //요청경로
     method: 'POST',
     cache: 'no-cache',
     headers: {
         'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
     },
     //컨트롤러로 전달할 데이터
     body: new URLSearchParams({
        // 데이터명 : 데이터값
        'cartCode': cartCode ,
        'cartCnt': cartCnt

     })
 })
 .then((response) => {
     if(!response.ok){
         alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
         return ;
     }
 
     return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
     //return response.json(); //나머지 경우에 사용
 })
 //fetch 통신 후 실행 영역
 .then((data) => {//data -> controller에서 리턴되는 데이터!
    const totalPrice= itemPrice * cartCnt;   //수량 변경 버튼 누르면 컨트롤러가서 업데이트 쿼리 실행 상품별 총가격 변경

    //선택한 input태그를 감싸고 있는 가장 가까운  tr안에 class가  totalPrice-span인 텍스트에 값 변경
    //totalPrice-span만 선택하면 상품별 총가격이 모두 바뀜 그래서 closest를 써서 상품별 하나의 총가격만 바꿀 수 있게 해줌
    selectedTag.closest('tr').querySelector('.totalPrice-span').textContent= '₩' + totalPrice.toLocaleString();
    setFinalPrice();//(전체 체크된)총가격을 계산 시켜주는 함수

    alert('상품의 수량이 변경되었습니다.')
 })
 //fetch 통신 실패 시 실행 영역
 .catch(err=>{
     alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
     console.log(err);
 });
 
}

//선택 삭제
function deleteCarts(){
    // 만약에 체크된 상품이 하나도 없다면 alert으로 '삭제할 상품을 선택하세요'를 띄우기
    const chks=document.querySelectorAll('.chk:checked'); //제목줄 제외 본문에 있는 체크박스 
    if(chks.length==0){
        alert('삭제할 상품을 선택하세요');
        return; 
    }


    //컨트롤러로 넘겨줄 cartCode들 
    //체크된 체크박스들에서 cartCode값 출력
    const cartCodes=[]; 
    for(const chk of chks){
        cartCodes.push(chk.value);
    }


    location.href=`/cart/deleteCarts?cartCodeList=${cartCodes}`;

}


//선택 구매
function buys(){
  const chks = document.querySelectorAll('.chk:checked');
  if(chks.length == 0){
    alert('구매할 상품을 선택하세요'); 
    return;
  }

  const cartCodeList= [];
  for(const chk of chks){
      cartCodeList.push(chk.value);
  }

  location.href=`/buy/buyCarts?cartCodeList=${cartCodeList}`;
}
























//전체 체크 박스
// function allCheck(){
//     const head= document.querySelector('#chkAll');

//     const isChecked= head.checked;

//     const checks=document.querySelectorAll('input[type="checkbox"]')

//     if(isChecked){
//         for(const menu of checks){
//             menu.checked=true;
//         }
//     }
//     else{
//         for(const menu of checks){
//             menu.checked=false;
//         }
//     }

// }

