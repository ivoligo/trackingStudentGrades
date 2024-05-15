import model.Fio;
import model.Student;
import service.DataService;
import service.FileService;
import service.StudentService;
import service.impl.FileServiceImpl;
import service.impl.StudentServiceImpl;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class App {

    private static final String studentsGradesFile = "data/studentsGrades.json";
    private static final String MENU_OPTION_ADDED_NEW_STUDENT = "a. Добавить нового ученика.";
    private static final String MENU_OPTION_DELETE_STUDENT = "b. Удалить ученика.";
    private static final String MENU_OPTION_UPDATE_GRADE_STUDENT = "c. Обновить оценку ученика.";
    private static final String MENU_OPTION_VIEW_ALL_GRADES = "d. Просмотр оценок всех учеников.";
    private static final String MENU_OPTION_VIEW_STUDENT_GRADES = "e. Просмотр оценок ученика.";
    final static FileService fileService = new FileServiceImpl();
//    final static DataService dataService = new DataServiceImpl();
    final static StudentService studentService = new StudentServiceImpl();

    public static void main(String[] args) throws URISyntaxException, IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Загрузить существующий файл или создать новый?");
        System.out.println("Нажмите 1 если необходимо загрузить существующий файл или 2 если создать новый. \nЕсли хотите выйти нажмите 0.");
        var pathForWork = choosingFileWork(scanner);
//        if (Files.readString(pathForWork).isEmpty()) {
            workWithFile(Files.readString(pathForWork).isEmpty());
//        }

        // todo: работа с файлом и мапой
    }

    private static void workWithFile(boolean isNewFile) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите опцию из меню:");
        System.out.println(MENU_OPTION_ADDED_NEW_STUDENT);
        System.out.println(MENU_OPTION_DELETE_STUDENT);
        System.out.println(MENU_OPTION_UPDATE_GRADE_STUDENT);
        System.out.println(MENU_OPTION_VIEW_ALL_GRADES);
        System.out.println(MENU_OPTION_VIEW_STUDENT_GRADES);
        boolean isChoiceCorrected = false;
        while (!isChoiceCorrected) {
            String selectedOption = scanner.nextLine();
            switch (selectedOption) {
                case "a":
                            System.out.println("введите фамилию ученика:");
                            var surname = scanner.nextLine();
                            System.out.println("введите имя ученика:");
                            var name = scanner.nextLine();
                            System.out.println("введите отчество ученика");
                            var patronymic = scanner.nextLine();
                            StringBuilder sb = new StringBuilder();
//                    var stringForGenerateId = sb.append(surname).append(name).append(patronymic).toString();
                            Fio fio = new Fio(surname, name, patronymic);
                            Student student = new Student();
//                            student.setId(UUID.fromString(fio.toString()));
                            student.setId(UUID.randomUUID());
                            student.setFio(fio);
                            student.setGrades(new ArrayList<>());
                            studentService.addStudent(student);

                            break;
                case "d":   var list = studentService.getAllStudents();
                            System.out.println(list);
                            break;

                case "0": isChoiceCorrected = true;
                default:
                    System.out.println("Выберите опцию из меню:");
                    System.out.println(MENU_OPTION_ADDED_NEW_STUDENT);
                    System.out.println(MENU_OPTION_DELETE_STUDENT);
                    System.out.println(MENU_OPTION_UPDATE_GRADE_STUDENT);
                    System.out.println(MENU_OPTION_VIEW_ALL_GRADES);
                    System.out.println(MENU_OPTION_VIEW_STUDENT_GRADES);
            }
        }
    }

    private static Path choosingFileWork(Scanner scanner) throws URISyntaxException {
        Path path = null;
        boolean isChoiceCorrected = false;
        while (!isChoiceCorrected) {
            String selectedOption = scanner.nextLine();
            switch (selectedOption) {
                case "1": //todo прочитать существующий файл;
                    isChoiceCorrected = true;
                    path = Paths.get(App.class.getClassLoader().getResource(studentsGradesFile).toURI());
                    break;
                case "2":
                    isChoiceCorrected = true;
                    path = fileService.createdFile(studentsGradesFile);
                    break;
                case "0":
                    isChoiceCorrected = true;
                    break;
                default:
                    System.out.println("Введите либо 1, либо 2, либо 0 для выхода");
            }
        }
        return path;
    }

}
