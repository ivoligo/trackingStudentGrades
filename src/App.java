import service.MenuService;

public class App {

    public static void main(String[] args) {

        var filename = MenuService.choosingFileMenu();
        MenuService.mainMenu(filename);
    }

}
