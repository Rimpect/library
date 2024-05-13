import java.io.*;
import java.util.HashMap;
import java.util.Map;
class NumberDictionary implements DictionaryInterface {
    private Map<String, String> dictionary;
    private String regex;
    private String filePath;

    public NumberDictionary(String filePath) {
        dictionary = new HashMap<>();
        regex = "^[0-9]{5}$";
        this.filePath = filePath;
        util.readFromFile(dictionary, filePath, regex); // Считываем данные из файла при создании объекта
    }
    @Override
    public String getFilePath() {
        return filePath;
    }
    @Override
    public boolean addEntry(String key, String value) {
        boolean result = util.addEntry(dictionary, key, value, regex);
        if (result) {
            util.writeToFile(dictionary, filePath); // Записываем изменения в файл
        }
        return result;
    }

    @Override
    public boolean deleteByKey(String key) {
        boolean result = util.deleteByKey(dictionary, key);
        if (result) {
            util.writeToFile(dictionary, filePath); // Записываем изменения в файл
        }
        return result;
    }

    @Override
    public String findByKey(String key) {
        return util.findByKey(dictionary, key);
    }

    @Override
    public Map<String, String> getDictionary() {
        return new HashMap<>(dictionary); // Возвращаем копию словаря для безопасности
    }

    @Override
    public void writeToFile() {
        util.writeToFile(dictionary, filePath);
    }
}