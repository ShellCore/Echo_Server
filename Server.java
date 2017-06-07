import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server
 * Created by Cesar. 07/06/2017.
 */
public class Server {

    public static void main(String[] args) {

        // Si en los argumentos de la ejecución de la aplicacion no se menciona el puerto, manda error
        if (args.length != 1) {
            System.err.println("Uso: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.err.println("Excepción capturada cuando se trató de escuchar desde el puerto" + portNumber + "o escuchando por una conexión");
            System.out.println(e.getMessage());
        }
    }
}
