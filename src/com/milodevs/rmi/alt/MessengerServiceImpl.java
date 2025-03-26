package com.milodevs.rmi.alt;

public class MessengerServiceImpl implements MessengerService {

    @Override
    public String sendMessage(String clientMessage) {
        return clientMessage.isEmpty() ? "No message recieved" : "Messenger says your message is " + clientMessage;
    }
}