import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DictionaryService implements DictionaryInterface {
    private Map<String, String> dictionary;
    private String filePath;
    private Pattern valuePattern;

    public DictionaryService(String filePath, String valueRegex) {
        this.filePath = filePath;
        this.valuePattern = Pattern.compile(valueRegex);
        this.dictionary = new HashMap<>();
        readFromFile();
    }

    @Override
    public void readFromFile() {
        dictionary.clear();
        try {
            Scanner fileScanner = new Scanner(new File(filePath));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
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
        if (!key.startsWith("0") && isValidValue(value)) {
            dictionary.put(key, value);
            saveToFile();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        if (dictionary.containsKey(key)) {
            dictionary.remove(key);
            saveToFile();
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

    private boolean isValidValue(String value) {
        Matcher matcher = valuePattern.matcher(value);
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