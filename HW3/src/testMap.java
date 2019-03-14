import java.util.HashMap;
import java.util.Map;

public class testMap {
    public static void main(String[] args) {
        Map<Integer, Character> m = new HashMap<>();
        m.put(3, 'x');
        System.out.println(m.get(3));
        if(m.get(4) == null) {
            System.out.println("Here");
        }
    }
}
