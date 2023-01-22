package functional_interface;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public final class Calculator {
    Map<String, BinaryOperation> operatorMap= new HashMap<>();

    public void registerOperation(String operator, BinaryOperation binaryOperation){
        operatorMap.put(operator, binaryOperation);
    }

    public int calculate(int num1, String opertor, int num2){
        return operatorMap.get(opertor).apply(num1, num2);
    }
}
