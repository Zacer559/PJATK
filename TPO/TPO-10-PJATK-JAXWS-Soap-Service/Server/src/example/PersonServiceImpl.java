package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@WebService(endpointInterface = "example.PersonServiceI")
public class PersonServiceImpl implements PersonServiceI {

    private static final Map<Integer, Person> persons = new HashMap<>();

    private static <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> predicate) {
        return map.entrySet()
                .stream()
                .filter(entry -> predicate.test(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    @Override
    public boolean addPerson(Person p) {
        if (persons.get(p.getId()) != null) return false;
        persons.put(p.getId(), p);
        return true;
    }

    @Override
    public boolean deletePerson(int id) {
        if (persons.get(id) == null) return false;
        persons.remove(id);
        return true;
    }

    @Override
    public Person getPersonByIndex(int id) {
        return persons.get(id);
    }

    @Override
    public Person[] getAllPersons() {
        Person[] values = new Person[persons.values().size()];
        return persons.values().toArray(values);
    }

    @WebMethod
    public Person[] getPersonByLastName(String lastName) {
        Person[] values = new Person[0];
        Map<Integer, Person> filteredMap = filterByValue(persons, value -> value.getLastName().equals(lastName));
        if (!filteredMap.isEmpty()) {
            values = new Person[filteredMap.values().size()];
            return filteredMap.values().toArray(values);
        }
        return values;
    }

    @WebMethod
    public Person[] getPersonByBirthDate(Date date) {
        Person[] values = new Person[0];
        Map<Integer, Person> filteredMap = filterByValue(persons, value -> value.getBirthDate().getTime() == date.getTime());
        if (!filteredMap.isEmpty()) {
            values = new Person[filteredMap.values().size()];
            return filteredMap.values().toArray(values);
        }
        return values;
    }
}