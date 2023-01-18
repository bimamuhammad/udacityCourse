package genericExample;

import java.util.ArrayList;

public class GenericsExercise {
    public static void main(String[] args){
        ArrayList<Object> variable = new ArrayList<Object>();
        variable.add(1.5);
        variable.add("Sally");
        variable.add(1);
        variable.add('a');
        for(Object obj: variable){
            displayClassName(obj);
        }
    }
    static <T> void displayClassName(T variable){
        System.out.println(variable.getClass().getName());
    }
}
