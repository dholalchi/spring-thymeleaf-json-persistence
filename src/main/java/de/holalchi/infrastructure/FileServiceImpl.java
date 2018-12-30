package de.holalchi.infrastructure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.holalchi.domain.model.Person;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {

    private static final String JSON = ".json";
    private final Gson gson = new GsonBuilder().create();

    private static void deleteFileIfExists(String personName) throws IOException {
        File file = new File(personName);
        if (file.exists()) {
            String fileAbsolutePath = file.getAbsolutePath();
            Files.delete(Paths.get(fileAbsolutePath));
        }
    }

    @Override
    public void writeToFile(Person person) throws IOException {

        String personName = person.getFirstName() + "_" + person.getLastName() + JSON;

        deleteFileIfExists(personName);

        try (FileWriter fileWriter = new FileWriter(personName)) {
            gson.toJson(person, fileWriter);
        }
    }

    @Override
    public Person readFromFile(String personFile) {
        try (FileReader fileReader = new FileReader(personFile)) {
            return gson.fromJson(fileReader, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getAllFileNames() {
        try (Stream<Path> paths = Files.walk(Paths.get("."))) {
            return paths
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(name -> name.endsWith(".json"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
