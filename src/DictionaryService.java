import java.io.*;
import java.util.*;
import java.util.TreeMap;
public class DictionaryService {
    private Map<String, String> dictionary;
    private String filePath;
    private String pattern;

    public DictionaryService(String filePath, String pattern) {
        this.filePath = filePath;
        this.pattern = pattern;
        dictionary = new TreeMap<>();
        readFromFile();
    }

    public void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2 && parts[0].matches(pattern)) {
                    dictionary.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteByKey(String key) {
        dictionary.remove(key);
        writeToFile();
    }

    public String findByKey(String key) {
        return dictionary.get(key);
    }

    public void addEntry(String key, String value) {
        if (key.matches(pattern)) {
            dictionary.put(key, value);
            writeToFile();
        }
    }

    private void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                bw.write(entry.getKey() + " - " + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<String, String> getDictionary() {
        return dictionary;
    }

}