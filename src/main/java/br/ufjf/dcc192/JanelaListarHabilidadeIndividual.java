package br.ufjf.dcc192;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.repodriller.domain.Modification;

public class JanelaListarHabilidadeIndividual extends JFrame{
    
    public JanelaListarHabilidadeIndividual(Pessoa p, SampleDataRepositorio control) throws HeadlessException {
        super("Perfil de " + p.getNome());
        JPanel perfil = new JPanel(new GridLayout(5, 1));
        JLabel labels[] = new JLabel[6];
        labels[0] = new JLabel("Nome: " + p.getNome());
        labels[1] = new JLabel("Email: " + p.getEmail());
        labels[2] = new JLabel("Commits: " + p.getCommits().size());
        p.setBancoDeDados(0);
        p.setInterfaceG(0);
        p.setEscritaELeitura(0);
        for(Commits commit: p.getCommits())
        {
            for(Modification modification: commit.getModificacoes())
            {
                for(String palavra: control.getPalavrasSwing())
                {
                    boolean i = modification.getDiff().toLowerCase().contains(palavra.toLowerCase());
                    if (i)
                    {
                        p.setInterfaceG(p.getInterfaceG() + 1);
                    }
                }
                for(String palavra1: control.getPalavrasBD())
                {
                    boolean i = modification.getDiff().toLowerCase().contains(palavra1.toLowerCase());
                    if (i)
                    {
                        p.setBancoDeDados(p.getBancoDeDados() + 1);
                    }
                }
                for(String palavra4: control.getPalavrasLeituraEEscrita())
                {
                    boolean i = modification.getDiff().toLowerCase().contains(palavra4.toLowerCase());
                    if (i)
                    {
                        p.setEscritaELeitura(p.getEscritaELeitura() + 1);
                    }
                }
            }
        }
        labels[3] = new JLabel("Interface: " + p.getInterfaceG());
        labels[4] = new JLabel("Banco de dados: " + p.getBancoDeDados());
        labels[5] = new JLabel("Leitura e escrita: " + p.getEscritaELeitura());
        for (int i = 0; i < 6; i++)
        {
            perfil.add(labels[i]);
        }
        add(perfil, BorderLayout.CENTER);
    }
    
}
