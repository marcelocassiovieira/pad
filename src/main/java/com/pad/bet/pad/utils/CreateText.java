package com.pad.bet.pad.utils;


import com.pad.bet.pad.domain.Cliente;

import java.util.Objects;

public class CreateText {


    public static String getCbuCompleto(Cliente cliente) {
        if (Objects.nonNull(cliente)) {
            String text1 = "Hola! ⚠️ *NO ENVIAR DINERO SIN CONSULTAR CBU SEGUNDOS ANTES* ⚠️\n" +
                    "\n" +
                    " ⚜️ *MÍNIMO DE CARGA: $1.000*\n" +
                    "\n" +
                    "*DATOS DE LA CUENTA*\n";

            String nombreCompleto = "Nombre Completo: *" + cliente.getTitular() + "*\n";
            String cbu = "CBU: *" + cliente.getCbu() + "*\n";
            String Alias = "Alias: *" + cliente.getAlias() + "*\n\n";

            String text2 = "ENVIAR:\n" +
                    "\uD83D\uDD3ACOMPROBANTE\n" +
                    "\uD83D\uDD3AUSUARIO de la plataforma\n" +
                    "\uD83D\uDD3ANOMBRE COMPLETO del TITULAR de la cuenta bancaria\n" +
                    "\n" +
                    "❗Para cargas *MENORES* a $3.000 se debe abonar un *RECARGO* de $30.❗\n" +
                    "Ejemplo: $1.000 = $1.030\n" +
                    "\n" +
                    "*Si no envían los $30 se le descuenta de su carga neta*\n" +
                    "Ejemplo: $1.000 = $970\n" +
                    "\n" +
                    "\uD83D\uDCF2 *INGRESÁ AQUÍ PARA JUGAR:* https://universegame.best/";

            return text1 + nombreCompleto + cbu + Alias + text2;
        }
        return "";
    }

    public static String getCbu(Cliente cliente) {
        if (Objects.nonNull(cliente)) {
            return cliente.getCbu();
        }
        return "";
    }

    public static String getRegistroUsuario(Cliente cliente) {
        if (Objects.nonNull(cliente)) {
            String text1 = "\uD83D\uDD38*USUARIO REGISTRADO*\uD83D\uDD38\n\n";
            String text2 = "*TU USUARIO:*" + cliente.getUsuario() + "\n";
            String text3 = "*CONTRASEÑA:* abc123\n\n";
            String text4 = "Para comenzar a jugar ingresá aquí: https://universegame.best/\n" +
                    "    \n" +
                    "Encontranos en instagram como *@suertecaba_arg*\n" +
                    "\n" +
                    "*CONSULTAR CBU PARA CARGAR TUS FICHAS* \uD83C\uDFB0";
            return text1 + text2 + text3 + text4;
        }
        return "";
    }

}
