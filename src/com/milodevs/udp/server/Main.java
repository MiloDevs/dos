package com.milodevs.udp.client;

import java.io.IOException;
import java.net.*;

public class Main {
    private static DatagramSocket dgramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static final int PORT = 1066;
    private static byte[] buffer = new byte[256];

    public static void main(String[] args) {
        System.err.println("Starting server on PORT " + PORT);
        try {
            dgramSocket = new DatagramSocket(PORT);
        } catch (IOException e) {
            System.err.println("Unable to start server on PORT " + PORT);
            System.exit(1);
        }

        System.err.println("Server started on PORT " + PORT);

        int numMessages = 0;
        do {
            try {
                buffer = new byte[256];
                inPacket = new DatagramPacket(buffer, buffer.length);
                dgramSocket.receive(inPacket);
                InetAddress clientAddress = inPacket.getAddress();
                int clientPort = inPacket.getPort();
                String messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
                System.out.println("Message recieved from " + clientAddress + ":" + clientPort + ": " + messageIn);
                numMessages++;
                String messageOut = "Your message is " + messageIn + " msg num " + numMessages;
                outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress,
                        clientPort);
                dgramSocket.send(outPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
