import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PersonUtils {
    public static List<Person> alustaHenkilot(){
        List<Person> people = new ArrayList<>();
        people.add(new Person("Anna", 28, 165));
        people.add(new Person("Mikko", 32, 180));
        people.add(new Person("Ella", 24, 170));
        people.add(new Person("Teemu", 35, 175));
        people.add(new Person("Laura", 30, 168));
        return people;
    }

    public static void lajitteleHenkilot(List<Person> people, int choice){
        if (people != null) {
            switch (choice) {
                case 1 -> people.sort(Comparator.comparing(person -> person.name));
                case 2 -> people.sort(Comparator.comparingInt(person -> person.age));
                case 3 -> people.sort(Comparator.comparing(person -> person.height));
                default -> System.out.println("Virheellinen valinta, lajittelua ei suoritettu.");
            }
        }
        else
            System.out.println("Ei toimi");
    }

    public static void tulostaHenkilot(List<Person> people){
        for (Person person : people) {
            System.out.println(person);
        }
    }
}
