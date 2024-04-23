import java.util.Scanner;

public class Manager {
    private DictionaryService dictionary1;
    private DictionaryService dictionary2;

    public Manager(String filePath1, String filePath2) {

        dictionary1 = new DictionaryService(filePath1, "^.{1,9}$|^[a-zA-Z]{4}$");

        dictionary2 = new DictionaryService(filePath2, "^.{1,9}$|^[0-9]{5}$");
    }

    public void manage() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Просмотр словаря 1");
            System.out.println("2. Просмотр словаря 2");
            System.out.println("3. Удаление из словаря 1");
            System.out.println("4. Удаление из словаря 2");
            System.out.println("5. Поиск в словаре 1");
            System.out.println("6. Поиск в словаре 2");
            System.out.println("7. Добавить в словарь 1");
            System.out.println("8. Добавить в словарь 2");
            System.out.println("9. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    viewDictionary(dictionary1);
                    break;
                case 2:
                    viewDictionary(dictionary2);
                    break;
                case 3:
                    deleteFromDictionary(dictionary1, scanner);
                    break;
                case 4:
                    deleteFromDictionary(dictionary2, scanner);
                    break;
                case 5:
                    findInDictionary(dictionary1, scanner);
                    break;
                case 6:
                    findInDictionary(dictionary2, scanner);
                    break;
                case 7:
                    addToDictionary(dictionary1, scanner);
                    break;
                case 8:
                    addToDictionary(dictionary2, scanner);
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("Неверный ввод");
            }
        }
    }

    private void viewDictionary(DictionaryService dictionaryService) {
        dictionaryService.readFromFile();
        dictionaryService.getDictionary().forEach((k, v) -> System.out.println(k + " - " + v));
    }

    private void deleteFromDictionary(DictionaryService dictionary, Scanner scanner) {
        System.out.println("Введите ключ для удаления:");
        String key = scanner.nextLine();
        dictionary.deleteByKey(key);
    }

    private void findInDictionary(DictionaryService dictionary, Scanner scanner) {
        System.out.println("Введи ключ для поиска:");
        String key = scanner.nextLine();
        String value = dictionary.findByKey(key);
        if (value != null) {
            System.out.println(key + " - " + value);
        } else {
            System.out.println("Ключ не найден");
        }
    }

    private void addToDictionary(DictionaryService dictionary, Scanner scanner) {
        System.out.println("Введите ключ:");
        String key = scanner.nextLine();
        System.out.println("Введите значение:");
        String value = scanner.nextLine();
        dictionary.addEntry(key, value);
    }
}