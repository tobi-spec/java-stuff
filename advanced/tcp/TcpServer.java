package advanced.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        int port = 5000;
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("TCP Server is listening on port " + port);

            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String text;
                while((text = input.readLine()) != null) {
                    System.out.println("Received: " + text);
                    output.println("Echo: " + text);
                    if ("bye".equalsIgnoreCase(text)) break;
                }

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
