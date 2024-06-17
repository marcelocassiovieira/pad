package com.pad.bet.pad.service;

import java.io.FileNotFoundException;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        MessageRepositoryService service = new MessageRepositoryService();
        service.buscar("src/main/resources/messages/bienvenido.txt");
    }
}
