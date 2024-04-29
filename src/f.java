/*public class f {
}
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DictionaryService extends DictionaryAbstract {
    private Map<String, String> dictionary;
    private String filePath;
    private Pattern pattern;

    public DictionaryService(String filePath, String regex) {
        this.filePath = filePath;
        this.pattern = Pattern.compile(regex);
        this.dictionary = new HashMap<>();
        readFromFile(); // Читаем данные из файла при создании объекта
    }

    @Override
    public void readFromFile() {
        dictionary.clear(); // Очищаем текущий словарь перед чтением из файла
        try {
            Scanner fileScanner = new Scanner(new File(filePath));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" - ");
                if (parts.length == 2 && isValidEntry(parts[0], parts[1])) {
                    dictionary.put(parts[0], parts[1]);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filePath);
        }
    }

    @Override
    public boolean addEntry(String key, String value) {
        if (isValidEntry(key, value)) {
            dictionary.put(key, value);
            saveToFile(); // Сохраняем изменения в файл
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        if (dictionary.containsKey(key)) {
            dictionary.remove(key);
            saveToFile(); // Сохраняем изменения в файл
            return true;
        }
        return false;
    }

    @Override
    public String findByKey(String key) {
        return dictionary.get(key);
    }

    @Override
    public Map<String, String> getDictionary() {
        return dictionary;
    }

    private boolean isValidEntry(String key, String value) {
        Matcher matcher = pattern.matcher(key);
        return matcher.matches();
    }

    private void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                writer.println(entry.getKey() + " - " + entry.getValue());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при сохранении файла: " + filePath);
        }
    }
}

 */