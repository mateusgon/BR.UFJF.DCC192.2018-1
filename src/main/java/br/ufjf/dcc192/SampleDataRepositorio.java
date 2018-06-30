package br.ufjf.dcc192;

import controlBD.CommitsDAO;
import controlBD.CommitsDAOJDBC;
import controlBD.PessoaDAO;
import controlBD.PessoaDAOJDBC;
import controlBD.RepositorioDAO;
import controlBD.RepositorioDAOJDBC;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.repodriller.domain.Modification;

public class SampleDataRepositorio {
    private ArrayList<Repositorio> repositorios;
    private RepositorioDAO rDao;
    private PessoaDAO pDao;
    private CommitsDAO cDao;
    private ArrayList<String> palavrasSwing;
    private ArrayList<String> palavrasBD;
    private ArrayList<String> palavrasLeituraEEscrita;
    private static Scanner input;
    private static Scanner input3;
    private static Scanner input4;
    private static Scanner input5;
    
    public SampleDataRepositorio() throws IOException, Exception {
        repositorios = new ArrayList<>();
        rDao = new RepositorioDAOJDBC();
        pDao = new PessoaDAOJDBC();
        cDao = new CommitsDAOJDBC();
        repositorios = (ArrayList<Repositorio>) rDao.ListAll();
        for (Repositorio repositorio : repositorios) {
            repositorio.setParticipantes(pDao.ListSelecionado(repositorio.getCodigoRepositorio()));
            for (Pessoa p : repositorio.getParticipantes())
            {
                p.setCommits(cDao.listSelecionado(p.getCodigoPessoa(), repositorio.getCodigoRepositorio(), repositorio.getNome()));
            }
        }
        lePalavrasChave();
    }

    public ArrayList<Repositorio> getRepositorios() {
        return repositorios;
    }

    public RepositorioDAO getDao() {
        return rDao;
    }

    public void insereBanco(Repositorio r) throws Exception {
        rDao.criar(r.getNome(), r.getUrl());
        int id2;
        id2 = getIndiceRepositorios(r.getNome(), r.getUrl());
        r.setCodigoRepositorio(id2);
        for (Pessoa pessoas : r.getParticipantes()) {        
            int id = pDao.criar(pessoas.getNome(), pessoas.getEmail(), r.getCodigoRepositorio());
            pessoas.setCodigoPessoa(id);
            for (Commits commits : pessoas.getCommits()) {
                cDao.criar(commits.getId(), commits.getComentario(), r.getCodigoRepositorio(), id, commits.getModificacoes());
            }
        }
        for (Pessoa pessoas : r.getParticipantes()) {        
            FileWriter fw = new FileWriter(r.getNome()+r.getCodigoRepositorio()+"commits"+pessoas.getCodigoPessoa()+".txt", false);
            BufferedWriter conexao = new BufferedWriter(fw);
            for (Commits commits : pessoas.getCommits()) {
                for (Modification modificacoe : commits.getModificacoes()) {
                try {   
                    conexao.write(modificacoe.getDiff()+ "/Fim/");
                    conexao.newLine();
                }
                catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            conexao.close();
        }  
    }

    public int getIndiceRepositorios(String nome, String url) throws Exception {
        return rDao.ListSelecionado(nome, url);
    }

    public void remover(Repositorio selecionado) throws Exception {
        for (Pessoa pessoa : selecionado.getParticipantes()) {
            for (Commits co : pessoa.getCommits()) {
                cDao.excluir(pessoa.getCodigoPessoa(), selecionado.getCodigoRepositorio(), selecionado.getNome());
            }
            pDao.excluir(pessoa.getCodigoPessoa());
        }
        rDao.excluir(selecionado.getCodigoRepositorio());
        this.repositorios.remove(selecionado);
    }
    
    public void lePalavrasChave() throws FileNotFoundException
    {
        palavrasSwing = new ArrayList<>();
        palavrasBD = new ArrayList<>();
        palavrasLeituraEEscrita = new ArrayList<>();
        input = new Scanner (new FileReader("listaSwing.txt")).useDelimiter("//");
        input.useLocale(Locale.ENGLISH);
            try
                {
                    while (input.hasNext())
                    {
                        String texto =  input.next();
                        palavrasSwing.add(texto);
                    }
                }
                catch (NoSuchElementException elementException)
                {
                  System.out.println("Todas as leituras de item foram feitas.");
                }
                catch (IllegalStateException stateException)
                {
                   System.err.println("Error reading from file. Terminating.");
                } 
        input.close();
        input3 =  new Scanner(new FileReader("listaBancoDeDados.txt")).useDelimiter("//");
        input3.useLocale(Locale.ENGLISH);
                try
                {
                    while (input3.hasNext())
                    {
                        String texto =  input3.next();
                        palavrasBD.add(texto);
                    }
                }
                catch (NoSuchElementException elementException)
                {
                  System.out.println("Todas as leituras de item foram feitas.");
                }
                catch (IllegalStateException stateException)
                {
                   System.err.println("Error reading from file. Terminating.");
                } 
        input3.close();
        input5 = new Scanner(new FileReader("listaLeituraEEscrita.txt")).useDelimiter("//");
        input5.useLocale(Locale.ENGLISH);
                try
                {
                    while (input5.hasNext())
                    {
                        String texto =  input5.next();
                        palavrasLeituraEEscrita.add(texto);
                    }
                }
                catch (NoSuchElementException elementException)
                {
                  System.out.println("Todas as leituras de item foram feitas.");
                }
                catch (IllegalStateException stateException)
                {
                   System.err.println("Error reading from file. Terminating.");
                } 
        input5.close();
    }

    public ArrayList<String> getPalavrasSwing() {
        return palavrasSwing;
    }

    public void setPalavrasSwing(ArrayList<String> palavrasSwing) {
        this.palavrasSwing = palavrasSwing;
    }

    public ArrayList<String> getPalavrasBD() {
        return palavrasBD;
    }

    public void setPalavrasBD(ArrayList<String> palavrasBD) {
        this.palavrasBD = palavrasBD;
    }

    public ArrayList<String> getPalavrasLeituraEEscrita() {
        return palavrasLeituraEEscrita;
    }

    public void setPalavrasLeituraEEscrita(ArrayList<String> palavrasLeituraEEscrita) {
        this.palavrasLeituraEEscrita = palavrasLeituraEEscrita;
    }
        
    
    
}
