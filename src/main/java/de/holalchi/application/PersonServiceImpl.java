package de.holalchi.application;

import de.holalchi.domain.model.Person;
import de.holalchi.infrastructure.FileService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final FileService fileService;

    public PersonServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public Person getPerson(String personFile) {
        return fileService.readFromFile(personFile);
    }

    @Override
    public void savePerson(Person person) throws IOException {
        fileService.writeToFile(person);
    }

    @Override
    public List<String> getAllRecords() {
        return fileService.getAllFileNames();
    }
}
