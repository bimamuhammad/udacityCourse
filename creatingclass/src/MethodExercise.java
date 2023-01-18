import java.util.HashMap;
import java.util.Map;

public class MethodExercise {
    public static void main(String[] args){
        System.out.println(MethodExercise.uniqueCharcters("iiiii"));
    }

    public static Map<Character, Integer> uniqueCharcters(String queryString){
        Map<Character, Integer> counts = new HashMap<Character, Integer>();
        for(int i= 0; i<queryString.length(); i++){
            if(counts.containsKey(queryString.charAt(i))) {
                counts.put(queryString.charAt(i), counts.get(queryString.charAt(i))+1);
            }else{
                counts.put(queryString.charAt(i), 1);
            }
        }
        return counts;
    }
}
