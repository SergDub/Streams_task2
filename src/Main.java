import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String args[]) throws IOException {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get((new Random().nextInt(families.size()))),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних:");
        System.out.println(count);

        List<String> list = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println("Список фамилий призывников:");
        System.out.println(list);

        List<String> list1 = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> {
                    if (person.getSex() == Sex.MAN) {
                        return person.getAge() <= 65;
                    } else return person.getAge() <= 60;
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .map(person -> person.toString())
                .collect(Collectors.toList());
        System.out.println("Cписок потенциально работоспособных людей с высшим образованием:");
        System.out.println(list1);
    }
}
