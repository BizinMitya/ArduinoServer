package utils;

import model.Event;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class FileUtil {
    private static final Logger LOGGER = Logger.getLogger(FileUtil.class);
    private static final String LOG_DIR_PATH = System.getProperty("user.home") + File.separator + ".ArduinoServerLogs";
    private static final String LOG_FILE_PATH = System.getProperty("user.home") + File.separator + ".ArduinoServerLogs/ArduinoServerLogFile.txt";

    private static void createLogDir() {
        File logDir = new File(LOG_DIR_PATH);
        if (!logDir.exists()) {
            if (!logDir.mkdir()) {
                LOGGER.error("Ошибка создания директории в домашнем каталоге!");
            }
        }
    }

    private static File createLogFile() throws IOException {
        File logFile = new File(LOG_FILE_PATH);
        if (!logFile.exists()) {
            if (!logFile.createNewFile()) {
                LOGGER.error("Ошибка создания файла логов!");
            }
        }
        return logFile;
    }

    public static File getLogFile() throws IOException {
        createLogDir();
        return createLogFile();
    }

    public static List<Event> getLogsAsList() {
        List<Event> events = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(LOG_FILE_PATH))) {
            scanner.useDelimiter(System.lineSeparator());
            while (scanner.hasNext()) {
                String[] data = scanner.next().split(",");
                events.add(new Event(data[0], data[1]));
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return events;
    }

}
