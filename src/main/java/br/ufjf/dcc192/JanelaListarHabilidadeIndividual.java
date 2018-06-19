package br.ufjf.dcc192;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.repodriller.domain.Modification;

public class JanelaListarHabilidadeIndividual extends JFrame{

    String procurada1 = "DAO";
    String procurada2 = "JFRAME";
    
    public JanelaListarHabilidadeIndividual(Pessoa p) throws HeadlessException {
        super("Perfil de " + p.getNome());
        JPanel perfil = new JPanel(new GridLayout(5, 1));
        JLabel labels[] = new JLabel[5];
        labels[0] = new JLabel("Nome: " + p.getNome());
        labels[1] = new JLabel("Email: " + p.getEmail());
        labels[2] = new JLabel("Commits: " + p.getCommits().size());
        p.setBancoDeDados(0);
        p.setInterfaceG(0);
        for(Commitss commit: p.getCommits())
        {
            for(Modification modification: commit.getModificacoes())
            {
                boolean i = modification.getDiff().toLowerCase().contains(procurada1.toLowerCase());
                if (i)
                {
                    p.setBancoDeDados(p.getBancoDeDados() + 1);
                }
                boolean j = modification.getDiff().toLowerCase().contains(procurada2.toLowerCase());
                if (j)
                {
                    p.setInterfaceG(p.getInterfaceG() + 1);
                }
            }
        }
        labels[3] = new JLabel("Interface: " + p.getInterfaceG());
        labels[4] = new JLabel("Banco de dados: " + p.getBancoDeDados());
        for (int i = 0; i < 5; i++)
        {
            perfil.add(labels[i]);
        }
        add(perfil, BorderLayout.CENTER);
    }
    
}
