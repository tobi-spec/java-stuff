package advanced.udp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpClient {
    public static void main(String[] args) throws IOException {
        String hostname = "localhost";
        int port = 6000;
        Scanner scanner = new Scanner(System.in);

        DatagramSocket socket = new DatagramSocket();

        while(true) {
            System.out.print("Enter message: ");
            String message = scanner.nextLine();

            byte[] buffer = message.getBytes();
            InetAddress address = InetAddress.getByName(hostname);

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);

            if ("bye".equalsIgnoreCase(message)) break;

            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(responsePacket);

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Server replied: " + response);
        }
        socket.close();
        scanner.close();
    }
}
