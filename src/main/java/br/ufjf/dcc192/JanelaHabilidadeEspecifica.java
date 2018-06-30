package br.ufjf.dcc192;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import org.repodriller.domain.Modification;

public class JanelaHabilidadeEspecifica extends JFrame{

    public JanelaHabilidadeEspecifica(Repositorio selecionado) {
        super("Habilidade específica");
        setMinimumSize(new Dimension(600, 400));
        setPreferredSize(new Dimension(600, 400));
        JPanel janela = new JPanel();
        janela.setMinimumSize(new Dimension(600, 300));
        janela.setPreferredSize(new Dimension(600, 300));
        add(new JScrollPane(janela), BorderLayout.CENTER);
        JButton confirmar = new JButton("Confirmar");
        JTextField texto;
        Box vertical = Box.createVerticalBox();
        Box horizontal = Box.createHorizontalBox();
        JLabel adicionar;
        adicionar = new JLabel("Palavra-chave: ");
        texto = new JTextField("Insira aqui a habilidade desejada");
        horizontal.add(adicionar);
        horizontal.add(texto);
        vertical.add(horizontal);
        vertical.add(confirmar);
        janela.add(vertical);
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(texto.getText() == null) && !("Insira aqui a habildade desejada".equals(texto.getText())))
                {   
                    List<Pessoa> pessoas = new ArrayList<>();
                    int contador = 0;
                    for (Pessoa pessoa : selecionado.getParticipantes()) {
                        pessoa.setEspecifica(0);
                        for (Commits commit : pessoa.getCommits())
                        {
                            for (Modification modification : commit.getModificacoes())
                            {
                                boolean i = modification.getDiff().toLowerCase().contains(texto.getText().toLowerCase());
                                if (i)
                                {
                                    pessoa.setEspecifica(pessoa.getEspecifica() + 1);
                                }
                            }
                        }
                        if (pessoa.getEspecifica() > 0)
                        {
                            pessoas.add(pessoa);
                            contador++;
                        }
                    }
                    if (contador == 0)
                    {
                            //JOPitonPane
                    }    
                    else
                    {
                            int maior = 0;
                            int i = 1;
                            janela.removeAll();
                            JPanel perfil = new JPanel(new GridLayout(pessoas.size()+1, 1));
                            JLabel labels[] = new JLabel[pessoas.size()+1];
                            labels[0] = new JLabel("Os que mais se destacam em ordem decrescente");
                            perfil.add(labels[0]);
                            Collections.sort(pessoas, new Comparator() {
                                @Override
                                public int compare(Object o1, Object o2) {
                                    Pessoa p1 = (Pessoa) o1;
                                    Pessoa p2 = (Pessoa) o2;
                                    return p1.getEspecifica() > p2.getEspecifica() ? -1 : (p1.getEspecifica() < p2.getEspecifica() ? +1 : 0);
                                }
                            });
                            for (Pessoa pesso : pessoas ) {
                                labels[i] = new JLabel("Nome: " + pesso.getNome() + " e Email: " + pesso.getEmail());
                                perfil.add(labels[i]);
                                i++;
                            }
                            janela.add(new JScrollPane(perfil), BorderLayout.CENTER);
                            janela.updateUI();
                    }
                } 
                else
                {
                    JOptionPane.showMessageDialog(null, "Verifique como você cadastrou os dados, algo foi informado errado.\nTente novamente!", "Algo digitado incorreto", JOptionPane.INFORMATION_MESSAGE);
                }    
            }
        });
    }
}
