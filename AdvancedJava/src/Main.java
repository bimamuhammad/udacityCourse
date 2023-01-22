import functional_interface.BinaryOperation;
import functional_interface.Calculator;
import functional_interface.StatementOperation;

public class Main {
    public static void main(String[] args) {

        BinaryOperation add = Integer::sum;
        System.out.println(add.apply(2,4));

        StatementOperation statementOperation = (x)->"The statement made is"+x;
        System.out.println(statementOperation.makeStatement("Internationa influence"));

        Calculator calculator = new Calculator();
        calculator.registerOperation("+", (a,b)->a+b);
        calculator.registerOperation("-", (a,b)->a-b);
        calculator.registerOperation("/", (a,b)->a/b);
        calculator.registerOperation("*", (a,b)->a*b);


        System.out.println(calculator.calculate(2, "*", 3));
    }
}