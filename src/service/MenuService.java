package service;

import model.Student;

import java.util.Scanner;


public class MenuService {

    //Main menu
    private static final String MAIN_MENU_ADDED_NEW_STUDENT = "a. Добавить нового ученика.";
    private static final String MAIN_MENU_DELETE_STUDENT = "b. Удалить ученика.";
    private static final String MAIN_MENU_UPDATE_GRADE_STUDENT = "c. Обновить оценку ученика.";
    private static final String MAIN_MENU_VIEW_ALL_GRADES = "d. Просмотр оценок всех учеников.";
    private static final String MAIN_MENU_VIEW_STUDENT_GRADES = "e. Просмотр оценок ученика.";
    private static final String MAIN_MENU_ADD_GRADE_STUDENT = "f. Добавить оценку ученика.";
    // all menu
    private static final String MENU_EXIT = "0. Выход";
    //file menu
    private static final String FILE_MENU_CURRENT = "1. Использовать существующий файл.";
    private static final String FILE_MENU_NEW = "2. Создать новый файл.";
    private static final String FILE_MENU_QUESTION = "Загрузить существующий файл или создать новый?";
    private static final String FILE_MENU_CHOOSING_FILE = "Введите название файла или будет использован файл по умолчанию:";
    // auxiliary text main menu
    private static final String AUXILIARY_TEXT_FOR_NAME_STUDENT = "Введите имя ученика:";
    private static final String AUXILIARY_TEXT_SEQUENCE_NUMBER_GRADE = "Введите порядковый номер оценки для изменения:";
    private static final String AUXILIARY_TEXT_NEW_GRADE = "Введите оценку:";

    private final static StudentService studentService = new StudentService();

    public static String choosingFileMenu() {
        String filename = FileService.studentsGradesFile;
        Scanner scanner = new Scanner(System.in);
        fileMenu();
        boolean isChoiceCorrected = false;
        while (!isChoiceCorrected) {
            String selectedOption = scanner.nextLine();
            switch (selectedOption) {
                case "1":
                    System.out.println("есть следущие файлы:");
                    var files = FileService.getFilesName();
                    System.out.println(FILE_MENU_CHOOSING_FILE);
                    filename = scanner.nextLine();
                    if (!FileService.isExists(filename, files)) {
                        System.out.println("Введен не существующий файл");
                        break;
                    }
                    FileService.readFromFile(filename);
                    isChoiceCorrected = true;
                    break;
                case "2":
                    System.out.println(FILE_MENU_CHOOSING_FILE);
                    filename = scanner.nextLine().isEmpty() ? filename : scanner.nextLine();
                    isChoiceCorrected = true;
                    break;
                case "0":
                    System.exit(0);
                default:
                    fileMenu();
            }
        }
        return filename;
    }

    public static void mainMenu(String filename) {

        Scanner scanner = new Scanner(System.in);
        mainMenuText();
        boolean isChoiceCorrected = false;
        while (!isChoiceCorrected) {
            String selectedOption = scanner.nextLine();
            switch (selectedOption) {
                case "a":
                    System.out.println(AUXILIARY_TEXT_FOR_NAME_STUDENT);
                    var name = scanner.nextLine();
                    Student student = new Student();
                    student.setName(name);
                    studentService.addStudent(student);
                    break;
                case "b":
                    System.out.println(AUXILIARY_TEXT_FOR_NAME_STUDENT);
                    String id = scanner.nextLine();
                    studentService.deleteStudent(id);
                    break;
                case "c":
                    System.out.println(AUXILIARY_TEXT_FOR_NAME_STUDENT);
                    String idForU = scanner.nextLine();
                    studentService.getStudent(idForU);
                    System.out.println(AUXILIARY_TEXT_SEQUENCE_NUMBER_GRADE);
                    int gradeInd = scanner.nextInt();
                    System.out.println(AUXILIARY_TEXT_NEW_GRADE);
                    int newGrade = scanner.nextInt();
                    studentService.update(idForU, gradeInd, newGrade);
                    break;
                case "d":
                    studentService.getAllStudents().forEach(System.out::println);
                    break;
                case "e":
                    System.out.println(AUXILIARY_TEXT_FOR_NAME_STUDENT);
                    name = scanner.nextLine();
                    studentService.getStudent(name);
                    break;
                case "f":
                    System.out.println(AUXILIARY_TEXT_FOR_NAME_STUDENT);
                    name = scanner.nextLine();
                    System.out.println(AUXILIARY_TEXT_NEW_GRADE);
                    String grade = scanner.nextLine();
                    studentService.addGrade(name, grade);
                    break;
                case "0":
                    System.out.println(filename);
                    FileService.writeToFile(filename);
                    isChoiceCorrected = true;
                    break;
                default:
                    mainMenuText();
            }
        }
    }

    private static void mainMenuText() {
        System.out.println("Выберите опцию из меню:");
        System.out.println(MAIN_MENU_ADDED_NEW_STUDENT);
        System.out.println(MAIN_MENU_DELETE_STUDENT);
        System.out.println(MAIN_MENU_UPDATE_GRADE_STUDENT);
        System.out.println(MAIN_MENU_VIEW_ALL_GRADES);
        System.out.println(MAIN_MENU_VIEW_STUDENT_GRADES);
        System.out.println(MAIN_MENU_ADD_GRADE_STUDENT);
        System.out.println(MENU_EXIT);
    }

    private static void fileMenu() {
        System.out.println(FILE_MENU_QUESTION);
        System.out.println(FILE_MENU_CURRENT);
        System.out.println(FILE_MENU_NEW);
        System.out.println(MENU_EXIT);
    }

}

