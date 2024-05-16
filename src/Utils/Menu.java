package Utils;

import com.google.gson.Gson;
import dao.impl.StudentDaoImpl;
import model.Student;
import org.json.simple.parser.ParseException;
import service.StudentService;
import service.FileService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Menu {

    private static final String MENU_OPTION_ADDED_NEW_STUDENT = "a. Добавить нового ученика.";
    private static final String MENU_OPTION_DELETE_STUDENT = "b. Удалить ученика.";
    private static final String MENU_OPTION_UPDATE_GRADE_STUDENT = "c. Обновить оценку ученика.";
    private static final String MENU_OPTION_VIEW_ALL_GRADES = "d. Просмотр оценок всех учеников.";
    private static final String MENU_OPTION_VIEW_STUDENT_GRADES = "e. Просмотр оценок ученика.";
    private static final String MENU_OPTION_EXIT = "0. Выход";

    static StudentService studentService = new StudentService();
    final static FileService fileService = new FileService();

    public static void mainMenu(boolean isNewFile) throws IOException, ParseException {
        if (!isNewFile){

            var test = Utils.ParserJsonFile.parseJsonFile(fileService.getFile());
            Set<Student> myData = new HashSet<>();
            for (Object o : test) {
                Gson gson = new Gson();
                var st = gson.fromJson(o.toString(), Student.class);
                myData.add(st);
            }
            StudentDaoImpl.studentsGrades.addAll(myData);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите опцию из меню:");
        System.out.println(MENU_OPTION_ADDED_NEW_STUDENT);
        System.out.println(MENU_OPTION_DELETE_STUDENT);
        System.out.println(MENU_OPTION_UPDATE_GRADE_STUDENT);
        System.out.println(MENU_OPTION_VIEW_ALL_GRADES);
        System.out.println(MENU_OPTION_VIEW_STUDENT_GRADES);
        System.out.println(MENU_OPTION_EXIT);
        boolean isChoiceCorrected = false;
        while (!isChoiceCorrected) {
            String selectedOption = scanner.nextLine();
            switch (selectedOption) {
                case "a":
                    System.out.println("введите имя ученика:");
                    var name = scanner.nextLine();
                    Student student = new Student();
                    student.setId(UUID.randomUUID());
                    student.setName(name);
                    studentService.addStudent(student);
                    break;
                case "b":
                    System.out.println("введите id ученика для удаления:");
                    String id = scanner.nextLine();
                    studentService.deleteStudent(id);
                    break;
                case "c":
                    System.out.println("Введите id ученика у которого хотите изменить оценку");
                    String idForU = scanner.nextLine();
                    studentService.getStudent(idForU);
                    System.out.println("Введите порядковый номер оценки для изменения");
                    int gradeInd = scanner.nextInt();
                    System.out.println("Введите оценку на которую хотите изменить:");
                    int newGrade = scanner.nextInt();
                    studentService.update(idForU, gradeInd, newGrade);
                    break;
                case "d":
                    studentService.getAllStudents().forEach(System.out::println);
                    break;
                case "e":
                    System.out.println("введите id ученика:");
                    String idR = scanner.nextLine();
                    studentService.getStudent(idR);
                    break;
                case "0":
                            fileService.writeJsonFile();
                            isChoiceCorrected = true;
                default:
                    System.out.println("Выберите опцию из меню:");
                    System.out.println(MENU_OPTION_ADDED_NEW_STUDENT);
                    System.out.println(MENU_OPTION_DELETE_STUDENT);
                    System.out.println(MENU_OPTION_UPDATE_GRADE_STUDENT);
                    System.out.println(MENU_OPTION_VIEW_ALL_GRADES);
                    System.out.println(MENU_OPTION_VIEW_STUDENT_GRADES);
                    System.out.println(MENU_OPTION_EXIT);
            }
        }
    }
}
