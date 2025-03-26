package com.milodevs.rmi.alt.server;

import com.milodevs.rmi.alt.MessengerService;
import com.milodevs.rmi.alt.MessengerServiceImpl;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    private static MessengerService messengerService, stub;
    private static final int PORT = 1099;

    public static void main(String[] args) {
        System.err.println("Starting RMI Server...");
        try {
            messengerService = new MessengerServiceImpl();
            stub = (MessengerService) UnicastRemoteObject.exportObject((MessengerService) messengerService, 0);
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.rebind("MessengerService", stub);
            System.out.println("RMI Server Started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
