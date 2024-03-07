

const updateItemCode = document.querySelector('#updateItemCode').value; 
if(updateItemCode !=0){
    getDetail(updateItemCode);
}

 




function getDetail(itemCode){
    console.log(itemCode);
    fetch('/admin/selectItemDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
<<<<<<< HEAD
           'itemCode':itemCode
=======
            'itemCode':itemCode
>>>>>>> 9b1e33564e4cf34fc4ddf00d2aa022056a783923
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
          return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);
        console.log(data.itemDetail);
        console.log(data.cateList);


        const detail_div = document.querySelector('.detail-div');
        detail_div.innerHTML=''; // 지워주고 다시 추가

        let str=''; 
        str += `
                    
                    <h5>상품 기본 정보</h5>

                    <form action="/admin/updateItem" method="post">
                    <input type="hidden" name="itemCode" value="${data.itemDetail.itemCode}">
                    <div class="row mt-4" style="margin-bottom:5%;">
                        <div class="col-3">
                            카테고리
                        </div>
                        <div class="col-9">
                            <select class="form-select" name="cateCode" aria-label="Default select example">`;
                               

                            for(const category of data.cateList){
                                if(category.cateCode == data.itemDetail.cateCode){
                                    str+= `<option value="${category.cateCode}" selected>${category.cateName}</option>`;
                                }
                                else{
                                    str+= `<option value="${category.cateCode}">${category.cateName}</option>`;
                                }


                                

                            }

                      str+=      `</select>
                        </div>
                    </div>
                    <div class="row" style="margin-bottom:5%;">
                        <div class="col-3">
                            상품명
                        </div>
                        <div class="col-9">
                            <input name="itemName" type="text" class="form-control" value="${data.itemDetail.itemName}">
                        </div>
                    </div>
                    <div class="row" style="margin-bottom:5%;">
                        <div class="col-3">
                            상품 수량
                        </div>
                        <div class="col-9">
                            <input name="itemStock" type="text" class="form-control" value="${data.itemDetail.itemStock}">
                        </div>
                    </div>

                    <div class="row mb-3 align-items-center">
                    <label class="col-3 col-form-label">상품상태</label>
                    <div class="col-9">
                        <div class="form-check form-check-inline">`;


                        if(data.itemDetail.itemStatus == 1){
                            str+=`<input checked class="form-check-input" type="radio" name="itemStatus" value="1">`;
                        }
                        else{
                            str+=`<input class="form-check-input" type="radio" name="itemStatus" value="1">`;
                        }

                            
                       str+=      ` <label class="form-check-label">준비 중</label>
                        </div>
                        <div class="form-check form-check-inline">`;

                        if(data.itemDetail.itemStatus == 2){
                            str+=`<input checked class="form-check-input" type="radio" name="itemStatus" value="2">`;
                        }
                        else{
                            str+=`<input class="form-check-input" type="radio" name="itemStatus" value="2"></input>`;
                        }
                            

                       str+=    ` <label class="form-check-label">판매 중</label>
                        </div>
                        <div class="form-check form-check-inline">`;
                        if(data.itemDetail.itemStatus == 3){
                            str+=`<input checked class="form-check-input" type="radio" name="itemStatus" value="3">`
                        }
                        else{
                            str+=`<input class="form-check-input" type="radio" name="itemStatus" value="3">`
                        }

                            

                        str+=`    <label class="form-check-label">매진</label>
                        </div>
                    </div>
                </div>





                    <h5>상품 이미지 정보</h5>
                    <div class="row mt-4">
                        <div class="col-3">
                            메인 이미지
                        </div>
                        <div class="col-9">
                          `;
                            for(const img of data.itemDetail.imgList){
                                if(img.isMain=='Y'){
                                    str+=`<span onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>`;
                                }
                            }

            str+=           `
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-3">
                            상세 이미지
                        </div>
                        <div class="col-9">
                        `;
                     
                            let cnt = 0;
                            data.itemDetail.imgList.forEach(function(img, idx){

                                if(img.isMain == 'N'){
                                    if(cnt == 0){
                                        str += `
                                        <div class="col-9">
                                        <span onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>
                                        </div>
                                        `; 

                                        cnt++;
                                    }
                                    else{
                                        str += `
                                        <div class="offset-3 col-9">
                                        <span onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>
                                        </div>
                                        `; 
                                    }


                                  
                                }
                            });
                                    
                            

            str+=        ` 
            <div class="row mt-4">
            <div class="col text-end">
                <input type="submit"  value="변경" class="btn btn-primary">
            </div>
           </div>
    
    
            
           
            </div>
            </div>

            
            </div>
            </form>
           
`;
    

        detail_div.insertAdjacentHTML('afterbegin',str);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}



// 이미지 모달창 띄우기
function showModal(attachedFileName){
    console.log(attachedFileName);
    const img_modal =  new bootstrap.Modal('#imgModal');

    const img_tag = document.querySelector('#imgModal img')
    img_tag.src=`/upload/${attachedFileName}`;
    img_modal.show();
}
