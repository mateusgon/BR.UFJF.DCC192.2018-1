package br.ufjf.dcc192;

import controlBD.CommitsDAO;
import controlBD.CommitsDAOJDBC;
import controlBD.PessoaDAO;
import controlBD.PessoaDAOJDBC;
import controlBD.RepositorioDAO;
import controlBD.RepositorioDAOJDBC;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.repodriller.domain.Modification;

public class SampleDataRepositorio {
    private ArrayList<Repositorio> repositorios;
    private RepositorioDAO rDao;
    private PessoaDAO pDao;
    private CommitsDAO cDao;

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
    }

    public ArrayList<Repositorio> getRepositorios() {
        return repositorios;
    }

    public RepositorioDAO getDao() {
        return rDao;
    }

    public void insereBanco(Repositorio r) throws Exception {
        rDao.criar(r.getNome(), r.getUrl());
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
                    conexao.write(modificacoe.getDiff()+ "//");
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

    public int getIndiceRepositorios() {
        int posicao = -1;
            if (repositorios.size() > 0)
            {
                for (Repositorio repositorio : repositorios) {
                    if (posicao < repositorio.getCodigoRepositorio())
                    {
                        posicao = repositorio.getCodigoRepositorio();
                    }
                }            
                posicao++;
                return posicao;
            }
        return 0;
    }
    
    
}
