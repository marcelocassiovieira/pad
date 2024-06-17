package com.pad.bet.pad.service;

import com.pad.bet.pad.utils.MessageType;

public class MessageService {


    MessageRepositoryService messageRepositoryService = new MessageRepositoryService();


    public String getMessage(MessageType messageType){
        String path = messageType.getMessage();
        String message = messageRepositoryService.buscar(path);
        return message;
    }
}
