package advanced.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port)) {
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            System.out.println("Enter text (type 'bye' to quit):");
            while ((userInput = console.readLine()) != null) {
                output.println(userInput);
                String response = input.readLine();
                System.out.println(response);
                if ("bye".equalsIgnoreCase(userInput)) break;
            }
        } catch (UnknownHostException ex) {
            System.err.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex.getMessage());
        }
    }
}
