package br.ufjf.dcc192;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class JanelaVerMembros extends javax.swing.JFrame{

    private final List<Pessoa> parts;
    private final JList<Pessoa> lstParticipantes = new JList<>(new DefaultListModel<>());
    
    public JanelaVerMembros(List<Pessoa> participantes) {
        super("Membros");
        setMinimumSize(new Dimension(534, 400));
        setPreferredSize(new Dimension(534, 400));
        this.parts = participantes;
        lstParticipantes.setModel(new ParticipantesListModel(parts));
        lstParticipantes.setMinimumSize(new Dimension(200, 200));
        lstParticipantes.setMaximumSize(new Dimension(200, 200));
        add(new JScrollPane(lstParticipantes), BorderLayout.CENTER);
    }
    
}
