import java.util.Map;

interface DictionaryInterface {
    boolean addEntry(String key, String value);
    boolean deleteByKey(String key);
    String findByKey(String key);
    Map<String, String> getDictionary();
    void writeToFile();
    public String getFilePath();
}