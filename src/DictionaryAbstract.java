import java.util.Map;

public abstract class DictionaryAbstract {
    public abstract void readFromFile();
    public abstract boolean addEntry(String key, String value);
    public abstract boolean deleteByKey(String key);
    public abstract String findByKey(String key);
    public abstract Map<String, String> getDictionary();
}