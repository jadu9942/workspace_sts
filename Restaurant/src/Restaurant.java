public class Restaurant {
   Cookable chef=new Chef();
//인터페이스의 객체는 해당 인터페이스를 구현한 클래스의 생성자 호출 가능

    public void job(){
        chef.cook();
    }


}
