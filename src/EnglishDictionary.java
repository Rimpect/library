import java.util.HashMap;
import java.util.Map;

class EnglishDictionary implements DictionaryInterface {
    private Map<String, String> dictionary;
    private String regex;
    private String filePath;

    public EnglishDictionary(String filePath) {
        dictionary = new HashMap<>();
        regex = "^[a-zA-Z]{4}$";
        this.filePath = filePath;
        util.readFromFile(dictionary, filePath, regex);
    }

    @Override
    public boolean addEntry(String key, String value) {
        boolean result = util.addEntry(dictionary, key, value, regex);
        if (result) {
            util.writeToFile(dictionary, filePath);
        }
        return result;
    }
    @Override
    public String getFilePath() {
        return filePath;
    }
    @Override
    public boolean deleteByKey(String key) {
        boolean result = util.deleteByKey(dictionary, key);
        if (result) {
            util.writeToFile(dictionary, filePath);
        }
        return result;
    }

    @Override
    public String findByKey(String key) {
        return util.findByKey(dictionary, key);
    }

    @Override
    public Map<String, String> getDictionary() {
        return new HashMap<>(dictionary);
    }

    @Override
    public void writeToFile() {
        util.writeToFile(dictionary, filePath);
    }
}