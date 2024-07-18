package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.github.cdimascio.dotenv.Dotenv;

public class HttpServerApagarPC {
    // Cargar las variables de entorno desde el archivo .env
    Dotenv dotenv = (Dotenv) Dotenv.configure().directory("./.env").load();

    // Obtener el valor de SERVER_IP y PORT desde las variables de entorno
    private String serverIp = dotenv.get("SERVER_IP");
    private int port = Integer.parseInt(dotenv.get("PORT"));
    private HttpServer server = null;
    public HttpServerApagarPC() throws Exception
    {
        iniciar();
    }

    public HttpServer getServer()
    {
        return server;
    }

    public void iniciar() throws Exception {

        server = HttpServer.create(new InetSocketAddress(port), 0);

        // Manejador para la ruta raíz "/"
        server.createContext("/", new MyHandler());

        // Manejador para la ruta "/shutdown"
        server.createContext("/shutdown", new ShutdownHandler());

        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Servidor abierto");
        //tester();
    }

    public void apagarServidor()
    {
        if(server != null)
        {
            server.stop(0);
            server = null;
            System.out.println("Servidor cerrado");
        }
    }
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "Hello, this is the response from the server!";
            sendResponse(t, response);
        }

    }
    static class ShutdownHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // Ejecutar el comando para apagar la computadora
            String response;
            if (shutdownComputer()) {
                response = "Shutting down the computer...";
            } else {
                response = "Failed to shutdown the computer.";
            }
            sendResponse(t, response);
        }

    }
    // Método para enviar la respuesta al cliente

    private static void sendResponse(HttpExchange t, String response) throws IOException {
        t.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    // Método para apagar la computadora

    private static boolean shutdownComputer() {
        String shutdownCommand;
        String operatingSystem = System.getProperty("os.name").toLowerCase();

        if (operatingSystem.contains("win")) {
            shutdownCommand = "shutdown /s /t 0";
        } else {
            shutdownCommand = "shutdown -h now";
        }

        try {
            Process process = Runtime.getRuntime().exec(shutdownCommand);
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    //tester
    public void tester()
    {
        String serverUrl = "http://"+serverIp+":"+port+"/shutdown";

        try {
            // Realiza una solicitud GET al servidor para apagar la computadora
            String response = sendGetRequest(serverUrl);
            System.out.println("Response from server:");
            System.out.println(response);
        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }

    // Método para enviar una solicitud GET al servidor y obtener la respuesta
    private static String sendGetRequest(String serverUrl) throws IOException {
        URL url = new URL(serverUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        return response.toString();
    }
}