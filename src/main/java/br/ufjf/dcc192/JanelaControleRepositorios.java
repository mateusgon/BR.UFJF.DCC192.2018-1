package br.ufjf.dcc192;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import org.repodriller.RepositoryMining;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRemoteRepository;

public class JanelaControleRepositorios extends javax.swing.JFrame {

    public JanelaControleRepositorios(SampleDataRepositorio control) {
        super("Administração de repositório");
        setMinimumSize(new Dimension(600, 400));
        setPreferredSize(new Dimension(600, 400));
        JPanel janela = new JPanel();
        janela.setMinimumSize(new Dimension(600, 300));
        janela.setPreferredSize(new Dimension(600, 300));
        JComboBox<String> layouts = new JComboBox<>(new String[]{"Adicionar", "Alterar", "Remover"});
        add(layouts, BorderLayout.NORTH);
        add(new JScrollPane(janela), BorderLayout.CENTER);
        layouts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               switch(layouts.getSelectedIndex())
                {
                    case 0:
                        janela.removeAll();
                        janela.updateUI();
                        configurarAdicionar();
                        break;
                        
                    case 1:
                        janela.removeAll();
                        janela.updateUI();
                        configurarAlterar();
                        break;
                    case 2:
                        janela.removeAll();
                        janela.updateUI();
                        configurarExcluir();
                        break;
                }
            }

            private void configurarAdicionar()
            {   
                JButton confirmar = new JButton("Confirmar");
                JTextField [] texto = new JTextField[2];
                Box vertical = Box.createVerticalBox();
                Box horizontal = Box.createHorizontalBox();
                Box horizontal2 = Box.createHorizontalBox();
                JLabel [] adicionar = new JLabel[3];
                adicionar[0] = new JLabel("Nome: ");
                texto[0] = new JTextField("Insira aqui o nome do repositório");
                adicionar[1] = new JLabel("URL: ");
                texto[1] = new JTextField("Digite aqui a URL do repositório");                
                horizontal.add(adicionar[0]);
                horizontal.add(texto[0]);
                vertical.add(horizontal);
                horizontal2.add(adicionar[1]);
                horizontal2.add(texto[1]);
                vertical.add(horizontal2);
                vertical.add(confirmar);
                janela.add(vertical);
                confirmar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!(texto[0].getText() == null) && !("Insira aqui o nome do repositório".equals(texto[0].getText())) && !(texto[1].getText() == null))
                        {    
                            try 
                            {   
                                DevelopersVisitors dev;
                                String urlFinal = texto[1].getText();
                                new RepositoryMining().in(GitRemoteRepository.hostedOn(urlFinal).buildAsSCMRepository()).through(Commits.all()).process(dev = new DevelopersVisitors(), new CSVFile("repositorios/"+texto[0].getText()+".csv")).mine();
                                Repositorio r = new Repositorio(texto[0].getText(), texto[1].getText(), Inicial.commits);
                                control.getRepositorios().add(r);
                                try {
                                    control.insereBanco(r);
                                } catch (Exception ex) {
                                    Logger.getLogger(JanelaControleRepositorios.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Inicial.commits = new ArrayList<>();
                                JOptionPane.showMessageDialog(null, "Repositório lido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);                     
                            }   
                            catch (NumberFormatException ex)
                            {
                                JOptionPane.showMessageDialog(null, "Você não digitou um valor ou o valor está no formato incorreto.\nLembre-se, o valor deve ser, por exemplo, 0.00.", "Número no formato incorreto", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Verifique como você cadastrou os dados, algo foi informado errado.\nTente novamente!", "Algo digitado incorreto", JOptionPane.INFORMATION_MESSAGE);
                        }    
                    }
                });
            }
            private void configurarAlterar() {
                   /* JButton alterar = new JButton("Alterar");
                    JList<Repositorio> lstRepositorio = new JList<>(new DefaultListModel<>());
                    lstItem.setModel(new ItemListModel();
                    lstItem.setMinimumSize(new Dimension(500, 500));
                    lstItem.setPreferredSize(new Dimension(500, 500));
                    janelaItem.add(new JScrollPane(lstItem), BorderLayout.CENTER);
                    janelaItem.add(alterar);
                    lstItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    alterar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    Item selecionado = lstItem.getSelectedValue();
                        if (selecionado != null)
                        {
                            if (abrirJanela)
                            {
                                abrirJanela = false;
                                int i = pizzaria.getSdi().getItem().indexOf(selecionado);
                                JanelaControleItem modific = new JanelaControleItem(pizzaria.getSdi().getItem().get(i));
                                modific.setSize(600, 150);
                                modific.setLocationRelativeTo(null);
                                modific.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                modific.setVisible(true);
                                modific.addWindowListener(new WindowAdapter() {
                                    @Override
                                    public void windowClosing(WindowEvent evt) {
                                            modific.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                            Item a = modific.getModificado();
                                            pizzaria.getSdi().getItem().get(i).setNome(a.getNome());
                                            pizzaria.getSdi().getItem().get(i).setTipoItem(a.getTipoItem());
                                            pizzaria.getSdi().getItem().get(i).setValor(a.getValor());
                                        try {
                                            pizzaria.atualizarItem();
                                        } catch (IOException ex) {
                                            Logger.getLogger(JanelaControleItem.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                            lstItem.clearSelection();
                                            lstItem.updateUI();
                                            abrirJanela = true;
                                    }
                                });
                                lstItem.clearSelection();
                                lstItem.updateUI();
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Você deve fechar a janela aberta primeiro.", "Feche a janela aberta.", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } 
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Você deveria ter selecionado um Item.", "Selecione um Item.", JOptionPane.INFORMATION_MESSAGE);
                        }
                 }
                 });*/
            }    

            private void configurarExcluir() {
                    /*JButton remover = new JButton("Remover");
                    JList<Item> lstItem = new JList<>(new DefaultListModel<>());
                    lstItem.setModel(new ItemListModel(item.getItem()));
                    lstItem.setMinimumSize(new Dimension(500, 500));
                    lstItem.setPreferredSize(new Dimension(500, 500));
                    janelaItem.add(new JScrollPane(lstItem), BorderLayout.CENTER);
                    janelaItem.add(remover);
                    lstItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    remover.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    Item selecionado = lstItem.getSelectedValue();
                       if (selecionado != null)
                       {
                           pizzaria.getSdi().getItem().remove(selecionado);
                        try {
                            pizzaria.atualizarItem();
                        } catch (IOException ex) {
                            Logger.getLogger(JanelaControleItem.class.getName()).log(Level.SEVERE, null, ex);
                        }
                           lstItem.clearSelection();
                           lstItem.updateUI();
                       }
                       else
                       {
                           JOptionPane.showMessageDialog(null, "Você deveria ter selecionado um Item.", "Selecione um Item.", JOptionPane.INFORMATION_MESSAGE);
                       }
                    }
                });*/
         }
        });
        layouts.setSelectedIndex(0);
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
