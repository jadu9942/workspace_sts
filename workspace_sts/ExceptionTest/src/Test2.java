public class Test2 {
    public static void main(String[] args) {
        m2();
    }
    public static void m1(int num1, int num2){
        int result= num1/num2;
    }
    public static void m2(){
        m1(10,0);
    }
}
