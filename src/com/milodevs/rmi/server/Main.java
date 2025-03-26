package com.milodevs.rmi.server;
import com.milodevs.rmi.Search;
import com.milodevs.rmi.SearchQuery;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;

public class Main {
    public static void main(String[] args){
        try {
            Search obj = new SearchQuery();
            LocateRegistry.createRegistry(1900);
            Naming.rebind("rmi://localhost:1900"+"/hello", obj);
        } catch (MalformedURLException | RemoteException e) {
            System.out.println(e);
        }
    }
}