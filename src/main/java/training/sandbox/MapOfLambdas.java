package training.sandbox;


import java.util.Map;

import java.util.function.IntBinaryOperator;

public class MapOfLambdas {

    public void map() {
        Map<String, IntBinaryOperator> functionMap = Map.of(
                "sum", (x, y) -> x + y, // Can be replaced by  Integer::sum
                "subtract", (x, y) -> x - y
        );

        int result = functionMap.get("sum").applyAsInt(10, 4);
        System.out.println(result); // Output: 14


    }
}
