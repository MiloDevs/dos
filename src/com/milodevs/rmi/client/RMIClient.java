package com.milodevs.rmi.client;

import com.milodevs.rmi.Search;
import java.net.MalformedURLException;
import java.rmi.*;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Search obj = (Search) Naming.lookup("rmi://localhost:1900/hello");
            System.out.println(obj.query("hello"));
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.out.println(e);
        }
    }
}
