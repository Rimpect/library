import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
public class FilePathChecker {
    private static Map<String, DictionaryInterface> dictionaryMap;

    public static void managemain() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Словарь №1(Английские буквы в количестве 4 штуки)");
            System.out.println("2. Словарь №2 Цифры в количестве 5 штук");
            System.out.println("0. Выход");
            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите число которое есть в консоли.");
                continue;
            }
            if (choice >= 1 && choice <= 2) {
                checker(dictionaryMap.get(String.valueOf(choice)));
            } else if (choice == 0) {
                System.exit(0);
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите число которое есть в консоли.");
            }
        }
    }

    public static void checker(DictionaryInterface dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу:");
        String filePath = scanner.nextLine();

        if (validatePath(filePath)) {
            System.out.println("Файл существует.");
            dictionary.readFromFile(filePath);
            Manager manager = new Manager(dictionary);
            manager.manage();
        } else {
            System.out.println("Файл не существует.");
        }
        scanner.close();
    }
    private static boolean validatePath(String filePath) {
        Path path = Paths.get(filePath);


        return Files.exists(path) && Files.isRegularFile(path);
    }
    private static boolean validateFilePath(String filePath) {
        return Files.exists(Paths.get(filePath)) && !Files.isDirectory(Paths.get(filePath));
    }
    public static void main(String[] args) {
        dictionaryMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу ");
        String filePath = scanner.nextLine();
        if (!validateFilePath(filePath)) {
            System.out.println("Неверный путь к файлу.");
            System.exit(1);
        }
        dictionaryMap.put("1", new EnglishDictionary(filePath));
        dictionaryMap.put("2", new NumberDictionary(filePath));
        managemain();
    }
}