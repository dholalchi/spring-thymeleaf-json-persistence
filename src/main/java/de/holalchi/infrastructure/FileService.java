package de.holalchi.infrastructure;

import de.holalchi.domain.model.Person;

import java.io.IOException;
import java.util.List;

public interface FileService {
    void writeToFile(Person person) throws IOException;

    Person readFromFile(String personFile);

    List<String> getAllFileNames();
}
