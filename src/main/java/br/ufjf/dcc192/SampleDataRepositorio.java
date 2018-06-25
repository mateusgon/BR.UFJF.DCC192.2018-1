package br.ufjf.dcc192;

import controlBD.CommitsDAO;
import controlBD.CommitsDAOJDBC;
import controlBD.PessoaDAO;
import controlBD.PessoaDAOJDBC;
import controlBD.RepositorioDAO;
import controlBD.RepositorioDAOJDBC;
import java.io.IOException;
import java.util.ArrayList;

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
                p.setCommits(cDao.listSelecionado(p.getCodigoPessoa(), repositorio.getCodigoRepositorio()));
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
        if (this.repositorios.size() > 0)
        {
            Integer codigoRepositorio = repositorios.get((repositorios.size()-1)).getCodigoRepositorio();
            codigoRepositorio++;
            r.setCodigoRepositorio(codigoRepositorio);
            rDao.criar(r.getNome(), r.getUrl());
        }
        else
        {
            rDao.criar(r.getNome(), r.getUrl());
            Integer codigoRepositorio = rDao.ListAll().get(rDao.ListAll().size()-1).getCodigoRepositorio();
            r.setCodigoRepositorio(codigoRepositorio);   
        }
        for (Pessoa pessoas : r.getParticipantes()) {        
            int id = pDao.criar(pessoas.getNome(), pessoas.getEmail(), r.getCodigoRepositorio());
            for (Commits commits : pessoas.getCommits()) {
                cDao.criar(commits.getId(), commits.getComentario(), r.getCodigoRepositorio(), id, commits.getModificacoes());
            }
        }
        
    }
    
    
}
