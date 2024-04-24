import java.util.Map;

public abstract class DictionaryAbstract {
    public abstract void readFromFile();
    public abstract void addEntry(String key, String value);
    public abstract void deleteByKey(String key);
    public abstract String findByKey(String key);
    public abstract Map<String, String> getDictionary();
}