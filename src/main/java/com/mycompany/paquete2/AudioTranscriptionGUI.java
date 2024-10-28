package com.mycompany.paquete2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AudioTranscriptionGUI extends JFrame {
    private JButton startButton;
    private JTextArea transcriptionArea;
    private ProcesadorDeAudio procesador;

    public AudioTranscriptionGUI() {
        setTitle("Audio Transcription GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        startButton = new JButton("Start Recording");
        transcriptionArea = new JTextArea();
        transcriptionArea.setEditable(false);
        add(startButton, BorderLayout.NORTH);
        add(new JScrollPane(transcriptionArea), BorderLayout.CENTER);

        procesador = new ProcesadorDeAudio();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> startTranscription()).start();
            }
        });
    }

    private void startTranscription() {
        String transcription = procesador.capturarYTranscribir();
        if (transcription != null) {
            transcriptionArea.append(transcription + "\n");
        } else {
            transcriptionArea.append("Audio not recognized. Try again.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AudioTranscriptionGUI gui = new AudioTranscriptionGUI();
            gui.setVisible(true);
        });
    }
}
