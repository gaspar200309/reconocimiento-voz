package com.mycompany.paquete2;

public class Main {
    public static void main(String[] args) {
        ProcesadorDeAudio procesador = new ProcesadorDeAudio();

        try {
            System.out.println("Iniciando reconocimiento de voz...");

            // Intenta reconocer el audio hasta que se detecte algo o el usuario cancele
            String resultado = null;
            while (resultado == null) {
                resultado = procesador.capturarYTranscribir();
                if (resultado == null) {
                    System.out.println("Intentando de nuevo...");
                    Thread.sleep(1000); // Espera 1 segundo antes de intentar de nuevo
                }
            }

            System.out.println("Texto final reconocido: " + resultado);

        } catch (Exception e) {
            System.err.println("Error en el programa principal: " + e.getMessage());
            e.printStackTrace();
        } finally {
            procesador.cerrar();
        }
    }
}