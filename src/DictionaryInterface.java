import java.util.Map;

public interface DictionaryInterface {
    void readFromFile();
    boolean addEntry(String key, String value);
    boolean deleteByKey(String key);
    String findByKey(String key);
    Map<String, String> getDictionary();
}