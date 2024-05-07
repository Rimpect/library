import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FilePathChecker {
    public  String filePath1;
    public  static String regex;
    public FilePathChecker(String filePath1) {
        this.filePath1 = filePath1;
    }
    public  boolean validatePaths() {
        Path path1 = Paths.get(filePath1);
        return Files.exists(path1) && Files.isRegularFile(path1);
    }
    public static void managemain() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Словарь №1");
            System.out.println("2. Словарь №2");
            System.out.println("3. Словарь №3");
            System.out.println("4. Добавить в словарь");
            System.out.println("5. Выход");
            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите число от 1 до 5.");
                continue;
            }
            if (choice < 1 || choice > 5) {
                System.out.println("Неверный ввод. Пожалуйста, введите число от 1 до 5.");
                continue;
            }
            switch (choice) {
                case 1:
                    regex = "^[a-zA-Z]{4}$";
                    aaaaaa();
                    break;
                case 2:
                    regex = "^[0-9]{5}$";
                    aaaaaa();
                    break;
                case 3:
                    regex = "";
                    aaaaaa();
                    break;
                case 4:
                    regex = "";
                    aaaaaa();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }
    public static void aaaaaa()
    {
        Scanner scanner = new Scanner(System.in);
        int maxAttempts = 3;
        int attempts = 0;
        do {
            System.out.println("Введите путь к первому файлу:");
            String filePath1 = scanner.nextLine();

            FilePathChecker filePathChecker = new FilePathChecker(filePath1);

            if (filePathChecker.validatePaths()) {
                System.out.println("Файл существует.");
                Manager manager = new Manager(filePath1, regex);
                manager.manage();
                break;
            } else {
                System.out.println("Файл не существует.");
                attempts++;
                if (attempts < maxAttempts) {
                    System.out.println("Попробуйте еще раз. Осталось попыток: " + (maxAttempts - attempts));
                }
            }
        } while (attempts < maxAttempts);
        if (attempts == maxAttempts) {
            System.out.println("Превышено максимальное количество попыток. Программа завершена.");
        }
        scanner.close();
    }
    public static void main(String[] args) {
        managemain();

    }
}