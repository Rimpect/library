import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryService extends DictionaryAbstract {
    private Map<String, String> dictionary;
    private String filePath;
    private Pattern pattern;

    public DictionaryService(String filePath, String regex) {
        this.filePath = filePath;
        this.pattern = Pattern.compile(regex);
        this.dictionary = new HashMap<>();
    }

    @Override
    public void readFromFile() {
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
    public void addEntry(String key, String value) {
        if (isValidEntry(key, value)) {
            dictionary.put(key, value);
        }
    }

    @Override
    public void deleteByKey(String key) {
        dictionary.remove(key);
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
}