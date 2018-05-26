import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import servlets.EventServlet;
import servlets.LogServlet;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server(8080);
            ServletHandler servletHandler = new ServletHandler();
            servletHandler.addServletWithMapping(EventServlet.class, "/events");
            servletHandler.addServletWithMapping(LogServlet.class, "/logs");
            server.setHandler(servletHandler);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
