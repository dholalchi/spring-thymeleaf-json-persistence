package de.holalchi.application;

import de.holalchi.domain.model.Person;

import java.io.IOException;
import java.util.List;

public interface PersonService {

    Person getPerson(String personFile);

    void savePerson(Person person) throws IOException;

    List<String> getAllRecords();
}
