package servlets;

import org.apache.log4j.Logger;
import utils.DateUtil;
import utils.FileUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Сервлет для сохранения событий
 */
public class EventServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(EventServlet.class);

    /**
     * Запрос на сервлет вида http://localhost:8080/events?event=[событие]
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String event = request.getParameter("event");
        try {
            File logFile = FileUtil.getLogFile();
            try (PrintWriter printWriter = new PrintWriter(
                    new OutputStreamWriter(new FileOutputStream(logFile, true), "UTF-8"))) {
                printWriter.write(DateUtil.getNowDate() + "," + event + System.lineSeparator());
                LOGGER.info("Пришло событие: " + event);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
