package br.ufjf.dcc192;

import javax.swing.JFrame;

public class Inicial {
    public static void main(String[] args) {
        JanelaPrincipal inicio = new JanelaPrincipal();
        inicio.setSize(600, 400);
        inicio.setLocationRelativeTo(null);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio.setVisible(true);
    }
}
