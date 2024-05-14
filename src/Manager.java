import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class Manager {
    private DictionaryInterface dictionary;

    public Manager(DictionaryInterface dictionary) {
        this.dictionary = dictionary;
    }

    public void manage() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Добавить запись");
            System.out.println("2. Удалить запись");
            System.out.println("3. Найти запись");
            System.out.println("4. Показать словарь");
            System.out.println("0. Выход");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Введите ключ:");
                    String addKey = scanner.nextLine();
                    System.out.println("Введите значение:");
                    String addValue = scanner.nextLine();
                    if (dictionary.addEntry(addKey, addValue)) {
                        System.out.println("Запись добавлена успешно.");
                    } else {
                        System.out.println("Неверный формат слова.");
                    }
                    break;
                case "2":
                    System.out.println("Введите ключ для удаления:");
                    String deleteKey = scanner.nextLine();
                    if (dictionary.deleteByKey(deleteKey)) {
                        System.out.println("Запись удалена успешно.");
                    } else {
                        System.out.println("Запись не найдена.");
                    }
                    break;
                case "3":
                    System.out.println("Введите ключ для поиска:");
                    String findKey = scanner.nextLine();
                    String foundValue = dictionary.findByKey(findKey);
                    if (foundValue != null) {
                        System.out.println("Найденное значение: " + foundValue);
                    } else {
                        System.out.println("Запись не найдена.");
                    }
                    break;
                case "4":
                    System.out.println("Содержимое словаря:");
                    Map<String, String> dictionaryContent = dictionary.getDictionary();
                    for (Map.Entry<String, String> entry : dictionaryContent.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    break;
                case "0":
                    FilePathChecker.checker();

                default:
                    System.out.println("Неверный ввод. Пожалуйста, введите число от 0 до 4.");
                    break;
            }
        }
        scanner.close();
    }
}