import java.io.*;
import java.util.HashMap;
import java.util.Map;

class util {
    public static void readFromFile(Map<String, String> dictionary, String filePath, String regex) {
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

    public static boolean addEntry(Map<String, String> dictionary, String key, String value, String regex) {
        if (key.matches(regex)) {
            dictionary.put(key, value);
            return true;
        }
        return false;
    }

    public static boolean deleteByKey(Map<String, String> dictionary, String key) {
        if (dictionary.containsKey(key)) {
            dictionary.remove(key);
            return true;
        }
        return false;
    }

    public static String findByKey(Map<String, String> dictionary, String key) {
        return dictionary.get(key);
    }

    public static void writeToFile(Map<String, String> dictionary, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean validatePath(String filePath) {
        File file = new File(filePath);
        return file.exists() && !file.isDirectory();
    }

    public static void readFromFile(DictionaryInterface dictionary, String filePath) {

    }
}