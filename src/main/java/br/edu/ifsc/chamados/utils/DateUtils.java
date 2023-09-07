package br.edu.ifsc.chamados.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {


    public static String dateTime2StringFormatted(LocalDateTime hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return hora.format(formatter);
    }

    public static String getDataHoraFormatado() {
       return dateTime2StringFormatted(LocalDateTime.now());
    }

}
