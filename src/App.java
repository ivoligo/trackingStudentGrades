import org.json.simple.parser.ParseException;
import service.FileService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {

    final static FileService fileService = new FileService();

    public static void main(String[] args) throws URISyntaxException, IOException, ParseException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Загрузить существующий файл или создать новый?");
        System.out.println("Нажмите 1 если необходимо загрузить существующий файл или 2 если создать новый. \nЕсли хотите выйти нажмите 0.");
        var pathForWork = choosingFileWork(scanner);
//        if (Files.readString(pathForWork).isEmpty()) {
            Utils.Menu.mainMenu(Files.readString(pathForWork).isEmpty());
//        }

        // todo: работа с файлом и мапой
    }

    private static Path choosingFileWork(Scanner scanner) throws URISyntaxException {
        Path path = null;
        boolean isChoiceCorrected = false;
        while (!isChoiceCorrected) {
            String selectedOption = scanner.nextLine();
            switch (selectedOption) {
                case "1":
                    isChoiceCorrected = true;
                    path = Paths.get(FileService.studentsGradesFile);
                    break;
                case "2":
                    isChoiceCorrected = true;
                    path = fileService.createdFile(FileService.studentsGradesFile);
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
