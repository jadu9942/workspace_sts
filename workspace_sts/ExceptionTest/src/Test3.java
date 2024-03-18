import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test3 {
    public static void main(String[] args) {
        try {
            m1(); //메인에서는 예외처리 해줘야 함
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void m1() throws  IOException{
    m2();//예외 처리 전가됨 try로 감싸거나 throws해줘야 함
    }

    //throws Exception 여기서 발생하는 오류 다른데서 처리
    public static void m2() throws IOException {
        Path file= Paths.get("c:\\javastudy\\text.txt");
        BufferedWriter writer = null;

        writer= Files.newBufferedWriter(file); //예외 처리 안되면 예외처리 안되었다는 오류 발생
        writer.write('a');
        writer.write('b');
    }
}
