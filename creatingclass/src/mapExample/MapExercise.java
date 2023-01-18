package mapExample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercise {
    public static void  main(String[] args){
        Map<String, Person> mapOfPeople = new HashMap<String, Person>();
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Sally", "Bud@Sdy"));
        personList.add(new Person("Zealy", "Pers@on"));
        personList.add(new Person("Iyabo", "Bos@e"));
        personList.add(new Person("Alhassan", "Fr@ank"));

        for(Person person: personList){
            mapOfPeople.put(person.email, person);
        }

        System.out.println(mapOfPeople.get("Fr@ank"));
    }
}
