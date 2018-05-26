package servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import utils.FileUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Сервлет для чтения логов событий
 */
public class LogServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LogServlet.class);

    /**
     * Запрос на сервлет вида http://localhost:8080/logs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
            configuration.setClassForTemplateLoading(this.getClass(), "/templates");
            Template template = configuration.getTemplate("logs.ftl");
            Map<String, Object> events = new HashMap<>();
            events.put("events", FileUtil.getLogsAsList());
            template.process(events, response.getWriter());
            response.getWriter().flush();
        } catch (TemplateException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}