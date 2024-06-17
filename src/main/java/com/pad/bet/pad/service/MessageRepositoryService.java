package com.pad.bet.pad.service;

import javax.swing.*;
import java.io.*;

public class MessageRepositoryService {

    public String buscar(String path) {
        File fichero = new File(path);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader fil = new BufferedReader(new FileReader(fichero));
            String message = fil.readLine();
            builder.append(message);
            while (message != null) {
                message = fil.readLine();
                builder.append("\n");
                if(message != null)
                    builder.append(message);
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un Error" + e);
            JOptionPane.showMessageDialog(null, "Se ha producido un error", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        return builder.toString();
    }
}
