package re.ss13.repository;

import re.ss13.model.Person;

import java.util.List;

public interface IPersonRepository {

    List<Person> getAll();

    void save(Person person);

}
