package collectionExample;

import java.util.ArrayList;
import java.util.Collections;

public class SortingExample {
    public static void main(String[] args){
        ArrayList<String> names = new ArrayList<String>();
        names.add("Bima");
        names.add("Ilham");
        names.add("Sahib");
        names.add("Aadil");
        names.add("Kaydee");
        for (String name: names
             ) {
            System.out.println(name);
        }

        Collections.sort(names);
        System.out.println("========== SORTED ==========");

        for (String name: names
        ) {
            System.out.println(name);
        }
    }
}
