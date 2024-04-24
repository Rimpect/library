import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FilePathChecker {
    private String filePath1;
    private String filePath2;

    public FilePathChecker(String filePath1, String filePath2) {
        this.filePath1 = filePath1;
        this.filePath2 = filePath2;
    }

    public boolean validatePaths() {
        Path path1 = Paths.get(filePath1);
        Path path2 = Paths.get(filePath2);

        return Files.exists(path1) && Files.isRegularFile(path1) &&
                Files.exists(path2) && Files.isRegularFile(path2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к первому файлу:");
        String filePath1 = scanner.nextLine();

        System.out.println("Введите путь ко второму файлу:");
        String filePath2 = scanner.nextLine();

        FilePathChecker filePathChecker = new FilePathChecker(filePath1, filePath2);
        if (filePathChecker.validatePaths()) {
            System.out.println("Оба файла существуют по указанным путям.");
            Manager manager = new Manager(filePath1,filePath2);
            manager.manage();

        } else {
            System.out.println("Один или оба файла не существуют по указанным путям.");
        }

        scanner.close();
    }
}