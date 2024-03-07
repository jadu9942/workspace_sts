
function t1(){
    //fetch 실행 시 오류 발생하면 바로 catch 
    //오류 발생 없으면 then, then 까지 실행됨

    fetch('/study/t2', { //요청경로 
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({ 
           // 데이터명 : 데이터값
           name: 'java', //여러 개 가지고 가면 , 쓰고 더 써주면 됨
           age: 20
           //데이터 명으로 데이터 값 넘겨준다
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
    //    then((data)함수의 매개변수 함수는 지워지고 매개변수만 남은 형태
        console.log(data);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}

 function t2(){
    fetch('/study/t3', { //요청경로 
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // /데이터명 : 데이터값
           stuNum:1,
           stuName:'김자바',
           classCode:5,
           className:'자바반'
        })
    })
    .then((response) => {
        // /response 자바에서 받은 데이터
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }

        //   /   자바에서 받아온 데이터 자바스크립트에서 해석할 수 있도록 해줌 
        //   /   그리고 그 데이터 밑에 then의 data로 넘겨줌
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용 (클래스자료형)
        //  /자바에서 온 클래스 데이터 아래 then의 data로 넘겨줌
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
    //    then((data)함수의 매개변수 함수는 지워지고 매개변수만 남은 형태
        console.log(data);
        console.log(data.stuName);// 이름 데이터만 넘기기
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

 }

 function t3(){
    fetch('/study/t4', { //요청경로 
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // /데이터명 : 데이터값
        })
    })
    .then((response) => {
        // /response 자바에서 받은 데이터
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }

        //   /   자바에서 받아온 데이터 자바스크립트에서 해석할 수 있도록 해줌 
        //   /   그리고 그 데이터 밑에 then의 data로 넘겨줌
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용 (클래스자료형, list)
        //  /자바에서 온 클래스 데이터 아래 then의 data로 넘겨줌
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
    //    then((data)함수의 매개변수 함수는 지워지고 매개변수만 남은 형태
        console.log(data);
        console.log(data[1]); //배열 1번째 
        console.log(data[1].stuName); 

        //모든 학생의 학번의 합을 출력
        let sum=0;
        for(let i=0; i<data.length;i++){
            sum=sum+data[i].stuNum;
        }
        console.log(`모든 학생의 학번 총합=${sum}`)
        // / `` 문자열 안에 변수명 쓸 수 있도록 해줌
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

 }