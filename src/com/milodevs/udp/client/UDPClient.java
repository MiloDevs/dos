package com.milodevs.udp.client;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    private static DatagramSocket dgramSocket;
    private static final int PORT = 1066;
    private static byte[] buffer = new byte[256];
    private static Scanner scanner;

    public static void main(String[] args) {
        System.err.println("Starting Client");
        try {
            dgramSocket = new DatagramSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }

        do {
            try {
                buffer = new byte[256];
                scanner = new Scanner(System.in);
                System.out.print("Enter message: ");
                String message = scanner.nextLine();
                if (message.equals("q")) {
                    System.out.println("Exiting...");
                    break;
                }
                InetAddress serverAddress = InetAddress.getByName("localhost");
                DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress,
                        PORT);
                dgramSocket.send(outPacket);
                DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
                dgramSocket.receive(inPacket);
                String response = new String(inPacket.getData(), 0, inPacket.getLength());
                System.out.println("Server says: " + response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
