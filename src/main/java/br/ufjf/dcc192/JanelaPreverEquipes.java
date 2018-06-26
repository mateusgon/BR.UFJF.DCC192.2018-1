package br.ufjf.dcc192;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.repodriller.domain.Modification;

public class JanelaPreverEquipes extends JFrame{

    private ArrayList<Pessoa> interfaceG;
    private ArrayList<Pessoa> banco;
    private ArrayList<Pessoa> net;
    private ArrayList<Pessoa> escritaELeitura;
    
    
    public JanelaPreverEquipes(Repositorio selecionado, SampleDataRepositorio control) {
        super("Habilidade específica");
        interfaceG = new ArrayList<>();
        banco = new ArrayList<>();
        net = new ArrayList<>();
        escritaELeitura = new ArrayList<>();
        setMinimumSize(new Dimension(600, 400));
        setPreferredSize(new Dimension(600, 400));
        JPanel janela = new JPanel();
        janela.setMinimumSize(new Dimension(600, 300));
        janela.setPreferredSize(new Dimension(600, 300));
        add(janela, BorderLayout.CENTER);
        for (Pessoa p : selecionado.getParticipantes())
        {
            p.setBancoDeDados(0);
            p.setInterfaceG(0);
            p.setEscritaELeitura(0);
            p.setNet(0);
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
                    for(String palavra3: control.getPalavrasWeb())
                    {
                        boolean i = modification.getDiff().toLowerCase().contains(palavra3.toLowerCase());
                        if (i)
                        {
                            p.setNet(p.getNet() + 1);
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
        }
        int vet[] = new int[5];
        int i = 0;
        for (i = 0; i < 4; i++)
        {
            vet[i] = 0;
        }
        for (Pessoa p : selecionado.getParticipantes())
        {
            vet[0] = vet[0] + p.getInterfaceG();
            vet[1] = vet[1] + p.getBancoDeDados();
            vet[2] = vet[2] + p.getNet();
            vet[3] = vet[4] + p.getEscritaELeitura();
        }
        for (Pessoa p : selecionado.getParticipantes())
        {
            if (vet[0] > 0 && p.getPorcentagem() < porcentagem(vet[0], p.getInterfaceG()))
            {
                p.setPorcentagem(porcentagem(vet[0], p.getInterfaceG()));
                p.setIdPorcentagem(0);
            }
            
            if (vet[1] > 0 && p.getPorcentagem() < porcentagem(vet[1], p.getBancoDeDados()))
            {
                p.setPorcentagem(porcentagem(vet[1], p.getBancoDeDados()));
                p.setIdPorcentagem(1);
            }
            
            if (vet[2] > 0 && p.getPorcentagem() < porcentagem(vet[2], p.getNet()))
            {
                p.setPorcentagem(porcentagem(vet[2], p.getNet()));
                p.setIdPorcentagem(2);
            }
            
            if (vet[4] > 0 && p.getPorcentagem() < porcentagem(vet[3], p.getEscritaELeitura()))
            {
                p.setPorcentagem(porcentagem(vet[3], p.getEscritaELeitura()));
                p.setIdPorcentagem(3);
            }
            
            int id = p.getIdPorcentagem();
            switch (id)
            {
                case 0:
                    interfaceG.add(p);
                    break;
                case 1:
                    banco.add(p);
                    break;
                case 2:
                    net.add(p);
                    break;
                case 3:
                    escritaELeitura.add(p);
                    break;
            }
        }
        Box vertical = Box.createVerticalBox();
        JLabel texto1 = new JLabel("Participantes para Interface");
        vertical.add(texto1);
        if (interfaceG.size() > 0)
        {
            int cont = 0;
            JLabel textos[] = new JLabel[interfaceG.size()];
            for (Pessoa p : interfaceG) {
                textos[cont] = new JLabel("Nome: " + p.getNome() + " e E-mail: " + p.getEmail());
                vertical.add(textos[cont]);
            }
        }
        else
        {
            JLabel texto = new JLabel("Não existem usuários indicados interface desktop");
            vertical.add(texto);
        }
        JLabel texto2 = new JLabel("Participantes para Banco de Dados");
        vertical.add(texto2);
        if (banco.size() > 0)
        {
            int cont = 0;
            JLabel textos[] = new JLabel[banco.size()];
            for (Pessoa p : banco) {
                textos[cont] = new JLabel("Nome: " + p.getNome() + " e E-mail: " + p.getEmail());
                vertical.add(textos[cont]);
            }
        }
        else
        {
            JLabel texto = new JLabel("Não existem usuários indicados para banco de dados");
            vertical.add(texto);
        }
        JLabel texto3 = new JLabel("Participantes para Web");
        vertical.add(texto3);
        if (net.size() > 0)
        {
            int cont = 0;
            JLabel textos[] = new JLabel[net.size()];
            for (Pessoa p : net) {
                textos[cont] = new JLabel("Nome: " + p.getNome() + " e E-mail: " + p.getEmail());
                vertical.add(textos[cont]);
            }
        }
        else
        {
            JLabel texto = new JLabel("Não existem usuários indicados para web");
            vertical.add(texto);
        }
        JLabel texto4 = new JLabel("Participantes para Escrita e leitura");
        vertical.add(texto4);
        if (escritaELeitura.size() > 0)
        {
            int cont = 0;
            JLabel textos[] = new JLabel[escritaELeitura.size()];
            for (Pessoa p : escritaELeitura) {
                textos[cont] = new JLabel("Nome: " + p.getNome() + " e E-mail: " + p.getEmail());
                vertical.add(textos[cont]);
            }
        }
        else
        {
            JLabel texto = new JLabel("Não existem usuários indicados para escrita e leitura");
            vertical.add(texto);
        }
        janela.add(new JScrollPane(vertical));
    }
    
    public int porcentagem (int total, int valor)
    {
        int x = (valor * total)/100;
        return x;
    }
}
