package com.mycompany.paquete2;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.io.File;
import java.io.IOException;

public class ProcesadorDeAudio {
    private LiveSpeechRecognizer recognizer;
    private boolean isInitialized = false;

    public ProcesadorDeAudio() {
        initializeRecognizer();
    }

    private void initializeRecognizer() {
        try {
            Configuration config = new Configuration();

            // Verifica si estamos usando rutas de recursos o rutas del sistema de archivos
            String modelPath = "resource:/edu/cmu/sphinx/models/en-us/en-us";
            String dictPath = "resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict";
            String lmPath = "resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin";

            // Alternativa: usar rutas absolutas si los archivos están en el sistema
            /*
            String baseDir = System.getProperty("user.dir") + "/models/";
            String modelPath = baseDir + "en-us";
            String dictPath = baseDir + "cmudict-en-us.dict";
            String lmPath = baseDir + "en-us.lm.bin";
            */

            // Configura las rutas
            config.setAcousticModelPath(modelPath);
            config.setDictionaryPath(dictPath);
            config.setLanguageModelPath(lmPath);

            // Configuración adicional
            config.setSampleRate(16000);

            // Inicializa el reconocedor
            System.out.println("Inicializando reconocedor de voz...");
            recognizer = new LiveSpeechRecognizer(config);
            isInitialized = true;
            System.out.println("Reconocedor inicializado correctamente");

        } catch (IOException e) {
            System.err.println("Error al cargar los archivos de modelo: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error al inicializar el reconocedor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String capturarYTranscribir() {
        if (!isInitialized) {
            System.err.println("El reconocedor no está inicializado correctamente");
            return null;
        }

        try {


            System.out.println("Iniciando captura de audio...");
            recognizer.startRecognition(true);

            System.out.println("Hable ahora...");
            SpeechResult result = recognizer.getResult();

            if (result != null) {
                String texto = result.getHypothesis();
                System.out.println("Texto reconocido: " + texto);
                return texto;
            } else {
                System.out.println("No se pudo reconocer el audio.");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error durante la captura de audio: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                recognizer.stopRecognition();
            } catch (Exception e) {
                System.err.println("Error al detener el reconocimiento: " + e.getMessage());
            }
        }
    }

    public void cerrar() {
        if (recognizer != null) {
            try {
                recognizer.stopRecognition();
            } catch (Exception e) {
                System.err.println("Error al cerrar el reconocedor: " + e.getMessage());
            }
        }
    }
}