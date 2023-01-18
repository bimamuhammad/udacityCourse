import inheritanceExample.Person;
import inheritanceExample.Student;
import inheritanceExample.StudentEmployee;

public class Main {
    public static void main(String[] args)
    {
        Person[] persons = new Person[3];
        Person bob = new Person("Bima", "Muhammad");
        persons[0] = bob;

        Student mo = new Student("Christian", "Idiodi", "T-0012345");
        persons[1] = mo;

        StudentEmployee mua = new StudentEmployee("Sean", "Grimmes", "T-1111032", 50.0, "12345");
        persons[2] = mua;

        for (Person person : persons) {
            System.out.println(person);
        }


    }
}