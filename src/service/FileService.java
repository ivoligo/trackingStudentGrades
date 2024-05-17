package service;

import dao.impl.StudentDaoImpl;
import model.Student;

import java.io.*;
import java.util.Arrays;
import java.util.Set;

public class FileService {

    private static final String DATA_DIR = "data";
    public static final String studentsGradesFile = "studentsGrades.dat";

    public static void writeToFile(String filename) {

        File file = new File(DATA_DIR + "/" + filename);
        System.out.println(file);
        System.out.println(file.isFile());
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(StudentDaoImpl.studentsGrades);
            oos.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException("файл не найден");
        } catch (IOException e) {
            throw new RuntimeException("Проблема при записи в файл");
        }
    }

    public static void readFromFile(String filename) {

        if (filename.isEmpty()) filename = studentsGradesFile;
        File file = new File(DATA_DIR + "/" + filename);

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            StudentDaoImpl.studentsGrades = (Set<Student>) ois.readObject();

        } catch (FileNotFoundException e) {
            throw new RuntimeException("файл не найден");
        } catch (IOException e) {
            throw new RuntimeException("Проблема при записи в файл");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("проблема при чтении объекта");
        }
    }

    public static File[] getFilesName() {
        File[] files;
        File dir = new File(DATA_DIR);
        files = dir.listFiles() != null ? dir.listFiles() : new File[0];
        Arrays.stream(files)
                .filter(File::isFile)
                .forEach(file -> System.out.println(file.getName()));
        return files;
    }

    public static boolean isExists(String filename, File[] files) {


        return Arrays.stream(files)
                .anyMatch(file -> file.getName().equals(filename))
                ||
                filename.isEmpty();
    }
}
