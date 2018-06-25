package br.ufjf.dcc192;

import controlBD.CommitsDAO;
import controlBD.PessoaDAO;
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
        repositorios = (ArrayList<Repositorio>) rDao.ListAll();
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
    }
    
    
}
