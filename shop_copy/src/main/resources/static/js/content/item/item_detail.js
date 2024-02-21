
//수량이 변경 될때마다 총 가격을 세팅
function setTotalPrice(inputTag, itemPrice){
    //단가

    //수량
    const cnt = inputTag.value;

    if(cnt == ''){
        inputTag.value = 1;
        cnt = 1;
    }

    const totalPrice = parseInt(itemPrice) * parseInt(cnt);

    document.querySelector('.total-price-span').textContent = '￦' + totalPrice.toLocaleString();
    
}

function insertCart(){
  location.href='/cart/insertCart';
}

