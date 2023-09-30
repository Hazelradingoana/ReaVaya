package org.example;

import io.javalin.Javalin;
import io.javalin.core.util.CorsPlugin;
import io.javalin.http.staticfiles.Location;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
// program server

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.eclipse.jetty.server.Server;

public class JavalinServer {
    private static final String PAGES_DIR = "/public";

    private final Javalin appServer;

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
    }
}


//public class JavalinServer {
//    public static void main(String[] args) {
//        Javalin server = Javalin.create().start(7000); // Start the server on port 7000
//
//        // Define a route to handle GET requests
//        server.get("/api/data", context -> {
//            context.header("Access-Control-Allow-Origin", "*");
//            context.result("Hello from the backend!");
//        });
//    }
//}

//    Javalin webServer = Javalin.create(config -> {
//        config.defaultContentType = "application/json";
//    });
//
//this.webServer.start(7000);
//
//
//
//public void webServerFunctionality(World currentWorld) {
//        this.webServer.get("/world", context -> ApiHandler.getCurrentWorld(context, currentWorld));
//        this.webServer.get("/world/{world}", context -> ApiHandler.getSpecifiedWorld(context, currentWorld, tester));
//        this.webServer.post("/robot/{name}", context -> ApiHandler.executeCommand(context, currentWorld));
//        }