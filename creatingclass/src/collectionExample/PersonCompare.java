package collectionExample;

import inheritanceExample.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonCompare {
    public static void main(String[] args){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Sally", "Buddy"));
        personList.add(new Person("Zealy", "Person"));
        personList.add(new Person("Iyabo", "Bose"));
        personList.add(new Person("Alhassan", "Frank"));

        for(Person person: personList){
            System.out.println(person);
        }
        System.out.println("====== SORTED =====");

        Collections.sort(personList);
        for(Person person: personList){
            System.out.println(person);
        }

        System.out.print("====== REVERESED =======");
        Collections.sort(personList, Collections.reverseOrder());
        for(Person person: personList){
            System.out.println(person);
        }
    }
}
