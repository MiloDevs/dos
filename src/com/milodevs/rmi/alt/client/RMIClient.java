package com.milodevs.rmi.alt.client;

import com.milodevs.rmi.alt.MessengerService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMIClient {
    public static Registry registry;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Connecting to remote server...");
        try {
            registry = LocateRegistry.getRegistry();
        } catch (Exception e) {
            System.err.println("Could not connect to remote server..Closing");
            System.exit(1);
        }
        do {
            try {
                MessengerService server = (MessengerService) registry.lookup("MessengerService");
                System.out.println("Enter a message to send to the server: ");
                String message = scanner.nextLine();
                String response = server.sendMessage(message);
                System.out.println("Response: " + response);
            } catch (Exception e) {
                System.err.println("Could not send message.. Closing");
                System.exit(1);
            }
        } while (true);
    }
}
