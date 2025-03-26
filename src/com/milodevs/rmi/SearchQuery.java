package com.milodevs.rmi;
import java.rmi.*;
import java.rmi.server.*;

public class SearchQuery extends  UnicastRemoteObject implements Search  {
    public SearchQuery() throws RemoteException {
        super();
    }
    @Override
    public String query(String search) throws RemoteException {
        String result;
        if (search.equals("hello")) {
            result = "Hello World!";
        } else {
            result = "Not Found!";
        }
        return result;
    }
}