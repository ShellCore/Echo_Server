import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Cliente
 * Created by Cesar. 07/06/2017.
 */

public class Client {

    public static void main(String[] args) {

        // Si en los argumentos no se manda la ip y el puerto, manda un mensaje de error y termina el programa.
        if (args.length != 2) {
            System.err.println("Uso: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        // Obtención del IP y puerto
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            ) {
                String userInput;
                while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch(UnknownHostException e){
            System.err.println("No se conoce el host" + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No se puede establecer entrada o salida de datos de la conexión  " + hostName);
            System.exit(1);
        }

    }
}