package br.ufjf.dcc192;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JanelaListarHabilidade extends JFrame{
    
    private final List<Pessoa> parts;
    private final JList<Pessoa> lstParticipantes = new JList<>(new DefaultListModel<>());
    private JButton botaoIndividual = new JButton("Habilidade individual");
    
    public JanelaListarHabilidade(Repositorio selecionado)
    {
        super("Lista de Habilidades Individuais");
        parts = selecionado.getParticipantes();
        setMinimumSize(new Dimension(534, 400));
        setPreferredSize(new Dimension(534, 400));
        lstParticipantes.setModel(new ParticipantesListModel(parts));
        lstParticipantes.setMinimumSize(new Dimension(200, 200));
        lstParticipantes.setMaximumSize(new Dimension(200, 200));
        JPanel botao = new JPanel();
        botao.add(botaoIndividual);
        add(new JScrollPane(lstParticipantes), BorderLayout.NORTH);
        add(botao, BorderLayout.SOUTH);
        botaoIndividual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pessoa part = lstParticipantes.getSelectedValue();
                JanelaListarHabilidadeIndividual jlhi = new JanelaListarHabilidadeIndividual(part);
                jlhi.setSize(534, 400);
                jlhi.setLocationRelativeTo(null);
                jlhi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jlhi.setVisible(true);
            }
        });
    }
    
}
