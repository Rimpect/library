import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
public class util {
    protected Map<String, String> dictionary;
    protected String filePath;
    protected Pattern regex;

    public util(String filePath, String regex) {
        this.filePath = filePath;
        this.regex = Pattern.compile(regex);
        this.dictionary = new HashMap<>();
        readFromFile(filePath);
    }

    public void readFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && regex.matcher(parts[0]).matches()) {
                    dictionary.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean addEntry(String key, String value) {
        if (regex.matcher(key).matches()) {
            dictionary.put(key, value);
            writeToFile();
            return true;
        }
        return false;
    }


    public boolean deleteByKey(String key) {
        if (dictionary.containsKey(key)) {
            dictionary.remove(key);
            writeToFile();
            return true;
        }
        return false;
    }


    public String findByKey(String key) {
        return dictionary.get(key);
    }


    public Map<String, String> getDictionary() {
        return new HashMap<>(dictionary);
    }


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

