import java.util.InputMismatchException;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        System.out.println("프로그램 시작");

        Scanner sc= new Scanner(System.in);

        //예외가 발생할 수 있는 코드 작성
        try{
            int num1 =sc.nextInt();
            int num2 =sc.nextInt();
            System.out.println(num1/num2); //여기서부터 오류 발생시 밑에 출력문 실행 안되고 바로 catch로 이동
            System.out.println(1111);
        }catch (ArithmeticException e){//예외에 대한 정보 들어있음
            System.out.println("모든 수는 0으로 나눌 수 없어요");
            //예외 발생 시 실행할 코드 작성
            //예외 발생하지 않으면 실행 X
//            System.out.println("예외발생!");
//            System.out.println(e.getMessage()); //예외 발생 이유를 알려줌
//            e.printStackTrace(); //예외 발생 원인
        }catch (InputMismatchException e){
            System.out.println("숫자를 입력받으에요");
        }finally {
            //예외 발생 유무와 상관없이 무조건 꼭 실행되야 하는 코드
        }


        System.out.println("프로그램 종료"); //예외 발생하면 그 시점부터 프로그램 진행 X
    }
}
