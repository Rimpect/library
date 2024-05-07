import java.util.Map;
import java.util.Scanner;

public class Manager {
    private DictionaryService dictionary;

    public Manager(String filePath) {

        dictionary = new DictionaryService(filePath, "^[a-zA-Z]{4}$");
        //dictionary2 = new DictionaryService(filePath2, "^[0-9]{5}$");
    }

    public void manage() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Просмотр словаря");
            System.out.println("2. Удаление из словаря");
            System.out.println("3. Поиск в словаре");
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
                    viewDictionary(dictionary);
                    break;
                case 2:
                    deleteFromDictionary(dictionary, scanner);
                    break;
                case 3:
                    findInDictionary(dictionary, scanner);
                    break;
                case 4:
                    addToDictionary(dictionary, scanner);
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }

    private void viewDictionary(DictionaryService dictionary) {
        dictionary.readFromFile();
        for (Map.Entry<String, String> entry : dictionary.getDictionary().entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    private void deleteFromDictionary(DictionaryService dictionary, Scanner scanner) {
        System.out.println("Введите ключ для удаления:");
        String key = scanner.nextLine();
        if (dictionary.deleteByKey(key)) {
            System.out.println("Запись успешно удалена.");
        } else {
            System.out.println("Запись не найдена для удаления.");
        }
    }

    private void findInDictionary(DictionaryService dictionary, Scanner scanner) {
        System.out.println("Введите ключ для поиска:");
        String key = scanner.nextLine();
        String value = dictionary.findByKey(key);
        if (value != null) {
            System.out.println(key + " - " + value);
        } else {
            System.out.println("Ключ не найден.");
        }
    }

    private void addToDictionary(DictionaryService dictionary, Scanner scanner) {
        System.out.println("Введите ключ:");
        String key = scanner.nextLine();
        System.out.println("Введите значение:");
        String value = scanner.nextLine();
        if (dictionary.addEntry(key, value)) {
            System.out.println("Запись успешно добавлена.");
        } else {
            System.out.println("Не удалось добавить запись.");
        }
    }
}