package com.milodevs.tcp.client;
import java.io.*;
import java.net.*;

public class TCPClient {
    private static InetAddress host;
    private  static final int PORT = 5154;
    public static void main(String[] args) {
       try {
           host = InetAddress.getLocalHost();
           runClient();
       } catch (UnknownHostException e) {
            System.out.println("Host not found");
       }
    }

    private  static void runClient() {
        Socket link = null;
        try {
            link = new Socket(host, PORT);
            BufferedReader input = new BufferedReader(new InputStreamReader(link.getInputStream()));
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            BufferedReader userInp = new BufferedReader(new InputStreamReader(System.in));
            String msg, resp;
            do { 
                System.out.print("Enter message: ");
                msg = userInp.readLine();
                output.println(msg);
                resp = input.readLine();
                System.out.println("\nSERVER> " + resp);
            } while (!msg.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                System.out.println("Closing connection.");
                link.close();
            } catch (IOException e) {
                System.out.println("Unable to disconnect.");
                System.exit(1);
            }
        }
    }
}