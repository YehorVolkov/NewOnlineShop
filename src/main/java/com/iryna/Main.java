package com.iryna;

import com.iryna.controller.Servlet;
import com.iryna.controller.CreateServlet;
import com.iryna.db.DbService;
import com.iryna.loader.SettingsLoader;
import com.iryna.service.AddProductService;
import com.iryna.service.Service;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {

        SettingsLoader settingsLoader = new SettingsLoader("config.properties");
        DbService dbService = new DbService(settingsLoader);

        Service service = new Service();
        AddProductService addProductService = new AddProductService();

        service.setDbService(dbService);
        addProductService.setDbService(dbService);

        CreateServlet createServlet = new CreateServlet(addProductService);
        Servlet servlet = new Servlet(service);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(servlet), "/products");
        context.addServlet(new ServletHolder(createServlet), "/create");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }
}
