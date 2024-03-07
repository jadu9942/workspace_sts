package com.green.Board2.vo;

//페이지 정보를 담고 있는 클래스
public class PageVO {
    private int nowPage; //현재 선택된 페이지 번호
    private int totalDataCnt; //전체 데이터 수(전체 게시글 수)
    private int displayDataCnt; //한 페이지에 보여지는 데이터 수(게시글 개수)
    private int displayPageCnt; //한 페이지에 보여지는 페이지 수(페이지 넘버 수)
    private int beginPage; //화면에 보이는 첫번째 페이지 번호(한 페이지에 보여지는 페이지 넘버수 중 첫번째)
    private int endPage; // 화면에 보이는 마지막 페이지 번호
    private boolean prev; //이전 버튼의 유무 (페이지 첫번째 페이지에서 다음 페이지 넘겼을 때 있는 이전 버튼)
    private boolean next; //다음 버튼의 유무


//    생성자 객체를 만들기만 하면 초기값만 가짐 그리고 public void setPageInfo 메소드 호출해야 함
    public PageVO(){
        nowPage=1; // 초기값 1 /페이지 정보가 생성이되면 첫 페이지 1번으로 하겠다
        displayDataCnt=5; // 한 페이지에 게시글 개수 보여주기
        displayPageCnt=10; // 한 페이지에 페이지 개수 보여주기
    }

    public void setPageInfo(){

        //displayPageCnt ,nowPage
        //올림(현재 페이지번호/한 페이지에 보이는 페이지수)* 한 페이지에 보이는 페이지수
        //화면에 보이는 마지막 페이지 번호 세팅
        endPage=(int)Math.ceil(nowPage/(double)displayPageCnt)*displayPageCnt; //올림 리턴 타입이 실수
        //화면에 보이는 첫번째 페이지 번호 세팅
        beginPage= endPage - displayPageCnt+1;

        //전체 페이지 수
        //올림(전체 데이터 수/ 한 페이지에 보이는 데이터수)
       int totalPageCnt= (int)Math.ceil(totalDataCnt/(double)displayDataCnt);


       //next 버튼의 유무
        if(endPage< totalPageCnt){
            next=true;
        }
        else{
            next=false;
            endPage=totalPageCnt;
        }

        //prev버튼의 유무
        prev= beginPage==1? false :true; //  첫번째 페이지가 1이
    }

 // setter
    public void setTotalDataCnt(int totalDataCnt){
        this.totalDataCnt=totalDataCnt;
    }
    //getter 페이징 코드에 필요
    public int getBeginPage(){
        return beginPage;
    }
    public int getEndPage(){
        return endPage;
    }

    public boolean getPrev(){
        return prev;
    }
    public boolean getNext(){
        return next;
    }
    //setter
    public void setNowPage(int nowPage){this.nowPage=nowPage;}

    //getter
    public int getDisplayDataCnt(){
        return displayDataCnt;
    }

    public int getNowPage(){
        return nowPage;
    }

}
