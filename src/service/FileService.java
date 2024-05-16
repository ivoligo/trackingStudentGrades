package service;

import dao.impl.StudentDaoImpl;
import exception.FileServiceException;
import model.Student;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileService {

    public static final String studentsGradesFile = "data/studentsGrades.json";

    public Path createdFile(String filename) {
        try {
            if (Files.exists(Path.of(filename))) {
                Files.delete(Path.of(filename));
            }
            return Files.createFile(Path.of(filename));
        } catch (IOException ex) {
            throw new FileServiceException("файл не создан", ex);
        }
    }


    public OutputStream writeFile(String filename) {
        try {
            return Files.newOutputStream(Path.of(filename));
        } catch (IOException ex) {
            throw new FileServiceException("Данные не записаны в файл", ex);
        }

    }

    public void writeJsonFile() throws IOException {

        Collection<Student> studentsGrades = StudentDaoImpl.studentsGrades;
        List<JSONObject> jsonObjects = new ArrayList<>();
        for(Student student : studentsGrades) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", student.getId().toString());
            jsonObject.put("name", student.getName());
            jsonObject.put("grades", student.getGrades());
            jsonObjects.add(jsonObject);
        }

            Files.writeString(Path.of(studentsGradesFile), jsonObjects.toString());

    }

    public Path getFile() throws IOException {
        return Path.of(studentsGradesFile);
    }
}
