import utils.CityStatistics;
import utils.FileParser;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    private static final String ENTER_PATH_FILE = "\nВведите путь к файлу или введите команду: exit";
    private static final String INCORRECT_FILE_FORMAT = "Введен некорректный формат файла";
    private static final String FILE_NOT_FOUND = "\nфайл не найден";
    private static final String ERROR_READING_OCCURRED_FILE = "\nПроизошла ошибка чтения файла";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(ENTER_PATH_FILE);
            String path = scanner.nextLine();

            if (path.equals("exit")) {
                break;
            }

            try {
                FileParser.parse(path);
                CityStatistics cityStatistics = new CityStatistics();
                cityStatistics.printAndCountDuplicates();
                cityStatistics.printCountingBuildingsCity();

            } catch (IllegalArgumentException e) {
                System.out.println(INCORRECT_FILE_FORMAT);
            } catch (FileNotFoundException e) {
                System.out.println(FILE_NOT_FOUND);
            } catch (Exception e) {
                System.out.println(ERROR_READING_OCCURRED_FILE);
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
