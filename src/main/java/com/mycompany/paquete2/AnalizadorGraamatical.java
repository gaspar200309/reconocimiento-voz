package com.mycompany.paquete2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorGraamatical {
    private static final String GRAMATICA_REGEX = "(el|la)\\s(gato|perro)\\s(corre|salta)";

    public boolean analizarTexto(String texto) {
        Pattern pattern = Pattern.compile(GRAMATICA_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(texto);

        if (matcher.matches()) {
            System.out.println("Texto válido según la gramática.");
            return true;
        } else {
            System.out.println("Texto no cumple con la gramática.");
            return false;
        }
    }
}
