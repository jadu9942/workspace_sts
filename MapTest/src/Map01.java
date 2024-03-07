import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Map01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("aaa"); // list 중복 데이터 가능, 순서 존재
        list.get(1);

        // key: 중복가능 중복해서 쓰면 마지막에 쓴 데이터로 덮어씀
        // value: 중복 불가능
        // 순서 x
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "java");
        map.put(2, "python");
        map.put(3, "c++");

        map.get(1); //key값으로 데이터 접근하고



    }

    public Map<String, Object> aaa(){
        Aaa a = new Aaa();
        Bbb b = new Bbb();

        Map<String, Object> map= new HashMap<>();
        map.put("A 객체", a);
        map.put("B 객체", b);
        return map;
    }
}

 class Aaa{}
 class Bbb{}
 class Ccc{}











