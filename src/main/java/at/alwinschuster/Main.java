package at.alwinschuster;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        test1();
        test2();
        System.out.println("------------------- END    -------------------");
    }
    private static void test1(){
        ModelMapper mapper = new ModelMapper();
        var obj = new MyObject("bar", List.of("a", "b", "c", "d"));
        var updates = new MyObject(null, List.of("a", "b", "c"));

        // obj should be updated using the updates object. All null values should not be updated.

        System.out.println("------------------- TEST 1 -------------------");
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(obj, updates);

        System.out.println(updates);
        System.out.println("FAILED -- Excepted:  MyObject{myStringProperty='bar', myListProperty=[a, b, c]}");
    }
    private static void test2(){
        ModelMapper mapper = new ModelMapper();
        var obj = new MyObject("bar", List.of("a", "b", "c", "d"));
        var updates = new MyObject(null, List.of("a", "b", "c"));

        // obj should be updated using the updates object. All null values should not be updated.

        System.out.println("------------------- TEST 2 -------------------");
        // ctx.getDestination() -> Always null
        mapper.getConfiguration().setPropertyCondition(ctx -> ctx.getDestination() == null);

        mapper.map(obj, updates);

        System.out.println(updates);
        System.out.println("FAILED -- Excepted:  MyObject{myStringProperty='bar', myListProperty=[a, b, c]}");
    }
}