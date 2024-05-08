import java.io.*;
import java.util.HashMap;
import java.util.Map;
class EnglishDictionary implements DictionaryInterface {
    private Map<String, String> dictionary;
    private String regex;
    private String filePath;

    public EnglishDictionary(String filePath) {
        dictionary = new HashMap<>();
        regex = "^[a-zA-Z]{4}$"; // Регулярное выражение для английских слов из 4 букв
        this.filePath = filePath;
        readFromFile(filePath); // Считываем данные из файла при создании объекта
    }

    @Override
    public void readFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].matches(regex)) {
                    dictionary.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addEntry(String key, String value) {
        if (key.matches(regex)) {
            dictionary.put(key, value);
            writeToFile(); // Записываем изменения в файл
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        if (dictionary.containsKey(key)) {
            dictionary.remove(key);
            writeToFile(); // Записываем изменения в файл
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
        return new HashMap<>(dictionary); // Возвращаем копию словаря для безопасности
    }

    @Override
    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}