package com.milodevs.tcp.server;

import java.io.*;
import java.net.*;

public class TCPServer {
    private static ServerSocket serverSock;
    private static final int PORT = 5154;

    public static void main(String[] args) {
        System.out.println("Opening server on port:" + PORT);

        try {
            serverSock = new ServerSocket(PORT);
            System.out.println("Server opened on port:" + PORT);
            do {
                runServer();
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSock != null) {
                    serverSock.close();
                    System.out.println("Server closed.");
                }
            } catch (IOException e) {
                System.out.println("Error closing server socket.");
            }
        }

    }

    private static void runServer() {
        Socket link = null;
        try {
            link = serverSock.accept();
            System.out.println("Connection opened.");
            BufferedReader input = new BufferedReader(new InputStreamReader(link.getInputStream()));
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            String in = input.readLine();
            int numMsg = 0;
            while (!in.equals("exit")) {
                System.out.println("Message received " + in);
                numMsg++;
                output.println("Message " + numMsg + ": " + in);
                in = input.readLine();
            }
            System.out.println("Closing connection.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (link != null) {
                    link.close();
                    System.out.println("Connection closed.");
                }
            } catch (IOException e) {
                System.out.println("Error closing client socket.");
            }
        }
    }
}