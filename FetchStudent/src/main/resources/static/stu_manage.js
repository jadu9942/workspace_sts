
function goSelect(){
    const classCode =document.querySelector('#class-selector').value;
    
    location.href=`/?classCode=${classCode}`;
    // ?:내가 선택한 학급 코드 데이터 가져가야함
}

//셀렉트 값이 바뀔 때마다(반이름 선택 바꿀 때)
function fetchSelect(){
    const classCode =document.querySelector('#class-selector').value;
// 내가 선택한 학급에 대한 학번 데이터 가져올 거야

    fetch('/fetchSelect', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           classCode: classCode //변수
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
        //기존 테이블 내용 삭제
        //tbody 태그를 선택 
        const tbodyTag= document.querySelector('#stu-list-table > tbody'); 
        // 아이디가 stu-list-table의 자식 태그 

        //tbody 태그 안의 모든 내용을 삭제
        tbodyTag.innerHTML='';
        // innerHTML :내가 선택한 태그 안에 있는 내용들을 빈값으로 바꾸겠다
        // tbodyTag.remove(); 티바디 태그 자체가 사라짐

        //새로 조회한 데이터로 tbody 안의 내용을 채워 줌(다시 테이블 채워주는 것)
        let str= ``;
        
        // 자바에서 for each문은 (:) 자바 스크립트에서는 ( of )
        //    StuVO 자료형               const 자료형                         
       //str= str+``;
        // for(const stu of data){     
        //     str+=`<tr>
        //             <td></td>
        //             <td>${stu.className}</td>
        //             <td>${stu.stuNum}</td>
        //             <td>${stu.stuName}</td>
        //           </tr>`; 
        // }

        //() 안에 넣는 내용이 반복
        // element: 데이터에서 하나씩 빼서 뭐라고 할래 
        //index: 반복횟수
        //자바 스크립트에서 반복문(for each)
        data.forEach(function(stu, i){
            str+=`<tr>
                    <td>${data.length-i}</td>
                    <td>${stu.className}</td>
                    <td>${stu.stuNum}</td>
                    <td>
                        <span onclick="goDetail(${stu.stuNum})" id="name-span">
                        ${stu.stuName}
                    </td>
                  </tr>`; 
        });

        tbodyTag.insertAdjacentHTML('afterbegin',str);
            //내가 선택한 태그 기준으로 어떤 코드 추가할 때
            // afterbegin 티 바디태그 시작할 때



        // for(let i=0; i<data.length;i++){
        //     str= `<tr>
        //     <td>${data.length+i}</td>
        //     <td>${data[i].className}</td>
        //     <td>${data[i].stuNum}</td>
        //     <td>${data[i].stuName}</td>
        //     </tr>`;

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

} 

function goDetail(stuNum){
    fetch('/detail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'stuNum': stuNum
        })
    })
    //자바에서 넘어오는 데이터 최초로 받는 response 
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용 (class 자료형)
    })

    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        //조회된 데이터로 그림 그리기
        console.log(data)
        // console.log(data.stuVO.className) className만 출력하기

        const detail_div= document.querySelector('.stu-detail-div')
        //그림 그릴 태그
        detail_div.innerHTML='';

        let str=`
        <div>
        <div>학생기본정보</div>
        <div>
            <table>
                <thead>
                    <tr>
                        <td>학번</td>
                        <td>소속반</td>
                        <td>학생명</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="stuNumTd">${data.stuVO.stuNum}</td>
                        <td>${data.stuVO.className}</td>
                        <td>${data.stuVO.stuName}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div>
        <div>학생점수정보</div>
        <div>
            <table>
                <thead>
                    <tr>
                        <td>국어점수</td>
                        <td>영어점수</td>
                        <td>수학점수</td>
                        <td>평균</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="scoreTd">${data.scoreVO== null ? 0:data.scoreVO.korScore}</td>
                        <td class="scoreTd">${data.scoreVO== null ? 0:data.scoreVO.engScore}</td>
                        <td class="scoreTd">${data.scoreVO== null ? 0:data.scoreVO.mathScore}</td>

                        <td>${data.scoreVO==null?0.0 :data.scoreVO.korScore+data.scoreVO.engScore+ data.scoreVO.mathScore}</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div>
        <input id="btn" type="button" value="점수입력" onclick="setInput()">
        </div>
    
    </div>
        `; 
        detail_div.insertAdjacentHTML('afterbegin', str);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function setInput(){
    const btn= document.querySelector('#btn');
    if(btn.value=='점수입력'){

        const tds= document.querySelectorAll('.scoreTd');
    // querySelectorAll 해야지 국어 영어 수학 점수 다 가져옴 all없으면 하나만 받아옴

    // for(let i=0;i<tds.length;i++){
    //     tds[i].textContent='<input type="text">'
    // }

    // for(const e of tds){
    //     e.textContent= '<input type="text">'
    // }
    tds.forEach(function(e, i){
        e.innerHTML= '<input type="text" class="scoreInput">'
    })// 반복문은 이걸로 추천
    document.querySelector('#btn').value='저장';
    }

    else if(btn.value=='저장'){
        const result=confirm('입력한 정보로 점수를 등록할까요?'); //확인과 취소가 뜸 확인 눌렀는지 취소 눌렀는지 값도 있다 확인은 true 취소는 false
        if(result){
            insertScore(); //확인 누르면 실행 취소 누르면 실행 안되도록 한 것
        }
        
    }

}

function insertScore(){
    const inputs=document.querySelectorAll('.scoreInput');
    const stuNum=document.querySelector('.stuNumTd').textContent;

    // alert(inputs[0].value);   데이터 조회   
    // alert(inputs[1].value);
    // alert(inputs[2].value);
    // alert(stuNum);
    
    //input태그들

    fetch('/insertScore', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'korScore':inputs[0].value,
           'engScore':inputs[1].value,
           'mathScore':inputs[2].value,
           'stuNum': stuNum
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
        alert('점수가 등록되었습니다.');
        goDetail(stuNum);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}


