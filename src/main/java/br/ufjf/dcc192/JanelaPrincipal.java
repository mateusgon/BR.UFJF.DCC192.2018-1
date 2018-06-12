package br.ufjf.dcc192;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JanelaPrincipal extends javax.swing.JFrame {

    SampleDataRepositorio control;
    Boolean abrirJanela = true;
    
    public JanelaPrincipal() throws IOException {
        super("Gerenciador de grupos");
        control = new SampleDataRepositorio();
        setMinimumSize(new Dimension(600, 400));
        setPreferredSize(new Dimension(600, 400));
        jButton1 = new JButton();
        jButton2 = new JButton();
        jPanel1 = new JPanel();
        jButton1.setText("Selecionar repositório");
        jButton2.setText("Ver grupos");
        jButton1.setMinimumSize(new Dimension(300, 200));
        jButton2.setMinimumSize(new Dimension(300, 200));
        jPanel1.setMinimumSize(new Dimension(600, 300));
        jButton1.setPreferredSize(new Dimension(300, 200));
        jButton2.setPreferredSize(new Dimension(300, 200));
        jPanel1.setPreferredSize(new Dimension(600, 400));
        add(jButton1, BorderLayout.NORTH);
        add(jButton2, BorderLayout.SOUTH);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(abrirJanela)
                {
                    abrirJanela = false;
                    JanelaControleRepositorios inicio = new JanelaControleRepositorios(control);
                    inicio.setSize(650, 350);
                    inicio.setLocationRelativeTo(null);
                    inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    inicio.setVisible(true); 
                    inicio.addWindowListener(new WindowAdapter() {
                    @Override
                               public void windowClosing(WindowEvent evt) {
                                   inicio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                   //res.setSdi(inicio.getItem());
                                   abrirJanela = true;
                               }
                    });
                }
                else
                {
                       JOptionPane.showMessageDialog(null, "Você deve fechar a janela aberta primeiro.", "Feche a janela aberta.", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(abrirJanela)
                {
                    abrirJanela = false;
                    JanelaControleGrupos inicio = new JanelaControleGrupos(control);
                    inicio.setSize(650, 350);
                    inicio.setLocationRelativeTo(null);
                    inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    inicio.setVisible(true); 
                    inicio.addWindowListener(new WindowAdapter() {
                    @Override
                               public void windowClosing(WindowEvent evt) {
                                   inicio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                   //res.setSdi(inicio.getItem());
                                   abrirJanela = true;
                               }
                    });
                }
                else
                {
                       JOptionPane.showMessageDialog(null, "Você deve fechar a janela aberta primeiro.", "Feche a janela aberta.", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(600, 400));

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
