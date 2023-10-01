package org.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.util.List;
import java.util.Map;

import static org.example.DatabaseConnection.connection;

public class JavalinServer {
    private static final String PAGES_DIR = "/public";

    private final Javalin appServer;

    private DatabaseConnection dbc = new DatabaseConnection();



    public JavalinServer() {

        appServer = Javalin.create(config -> config.addStaticFiles(PAGES_DIR, Location.CLASSPATH));
    }

    private void routeForGetRequests() {
        // Define a route to handle GET requests
        appServer.get("/api/data", context -> {
            context.header("Access-Control-Allow-Origin", "*");
            context.result("Hello from the backend!");
        });
    }

    private void routeForPostRequests() {
//        "POST".equals(exchange.getRequestMethod())
        appServer.post("/api/register", context -> {
            // Parse form data from the request
            Map<String, List<String>> formData = context.formParamMap();

            // Forward the form data to your existing RegistrationHandler
            RegistrationHandler registrationHandler = new RegistrationHandler();
            registrationHandler.handle(connection, formData);

            context.status(200).result("Registration data received.");
        });
    }


    public void start(int port) {
        this.appServer.start(port);
    }

    public void stop() {
        this.appServer.stop();
    }

    public int port() {
        return appServer.port();
    }

    public static void main(String[] args) {
        JavalinServer server = new JavalinServer();
        server.start(5050);
        server.routeForGetRequests();
        server.routeForPostRequests();
    }
}
