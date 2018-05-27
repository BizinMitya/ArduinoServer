import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import servlets.EventServlet;
import servlets.LogServlet;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            Server server = new Server(8080);
            ServletHandler servletHandler = new ServletHandler();
            servletHandler.addServletWithMapping(EventServlet.class, "/events");
            servletHandler.addServletWithMapping(LogServlet.class, "/logs");
            server.setHandler(servletHandler);
            server.start();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
