package advanced.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {
    public static void main(String[] args) throws SocketException {
        int port = 6000;
        byte[] buffer = new byte[1024];

        try(DatagramSocket socket = new DatagramSocket(port)){
            System.out.println("UDP Server is listening on port " + port);

            while(true){
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + received);
                if("bye".equalsIgnoreCase(received)) break;

                String response = "Echo: " + received;
                byte[] responseBytes = response.getBytes();

                DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length,
                        packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
