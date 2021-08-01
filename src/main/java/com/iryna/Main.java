package com.iryna;

import com.iryna.controller.Controller;
import com.iryna.controller.CreateController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main {
    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(Controller.class, "/products");
        context.addServlet(CreateController.class, "/create");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
