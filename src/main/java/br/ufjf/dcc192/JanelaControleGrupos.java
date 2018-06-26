package br.ufjf.dcc192;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class JanelaControleGrupos extends javax.swing.JFrame {

    private final List<Repositorio> repos;
    private final JList<Repositorio> lstRepositorios = new JList<>(new DefaultListModel<>());
    private final JButton verMembros = new JButton("Ver membros");
    private final JButton listarHabilidades = new JButton("Listar habilidades");
    private final JButton habilidadeEspecifica = new JButton("Selecionar por habilidade específica");
    private final JButton preverEquipes = new JButton("Prever Equipes");
    
    public JanelaControleGrupos(SampleDataRepositorio control) {
        super("Projetos");
        setPreferredSize(new Dimension(730, 600));
        setMinimumSize(new Dimension(500, 400));
        this.repos = control.getRepositorios();
        lstRepositorios.setModel(new RepositoriosListModel(repos));
        lstRepositorios.setMinimumSize(new Dimension(200, 200));
        lstRepositorios.setMaximumSize(new Dimension(200, 200));
        JPanel botoes = new JPanel(new GridLayout(2, 2));
        botoes.add(verMembros);
        botoes.add(listarHabilidades);
        botoes.add(habilidadeEspecifica);
        botoes.add(preverEquipes);
        add(new JScrollPane(lstRepositorios), BorderLayout.NORTH);
        add(botoes, BorderLayout.SOUTH);
        lstRepositorios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        verMembros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Repositorio selecionado = lstRepositorios.getSelectedValue();
                JanelaVerMembros jvm = new JanelaVerMembros(selecionado.getParticipantes());
                jvm.setSize(534, 400);
                jvm.setLocationRelativeTo(null);
                jvm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jvm.setVisible(true);
            }
        });
        listarHabilidades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Repositorio selecionado = lstRepositorios.getSelectedValue();
                JanelaListarHabilidade jlh = new JanelaListarHabilidade(selecionado, control);
                jlh.setSize(534, 400);
                jlh.setLocationRelativeTo(null);
                jlh.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jlh.setVisible(true);
            }
        });
        habilidadeEspecifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Repositorio selecionado = lstRepositorios.getSelectedValue();
                JanelaHabilidadeEspecifica jhe = new JanelaHabilidadeEspecifica(selecionado);
                jhe.setSize(534, 400);
                jhe.setLocationRelativeTo(null);
                jhe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jhe.setVisible(true);
            }
        });
        preverEquipes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Repositorio selecionado = lstRepositorios.getSelectedValue();
                JanelaPreverEquipes jpe = new JanelaPreverEquipes(selecionado, control);
                jpe.setSize(534, 400);
                jpe.setLocationRelativeTo(null);
                jpe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jpe.setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
