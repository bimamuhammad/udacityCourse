package setExample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetExercise {
    public static void main(String[] args){
        List<String> numbers = new ArrayList<String>();
        numbers.add("1");
        numbers.add("1");
        numbers.add("23");
        numbers.add("56");
        numbers.add("56");
        
        Set<String> uniqueNumbers = new HashSet<String>(numbers);
        Set<String> uniqueNumbers1 = uniqueNumbers;
        uniqueNumbers.add("23");
        System.out.println(uniqueNumbers1.contains("23"));
        uniqueNumbers.remove("23");
        System.out.println(uniqueNumbers1.contains("23"));
        System.out.println(uniqueNumbers.contains("23"));


        for (String number:
             uniqueNumbers) {
            System.out.println(number);
        }
    }
}
