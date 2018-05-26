package br.ufjf.dcc192;

import java.io.IOException;
import java.util.ArrayList;

public class SampleDataRepositorio {
    private ArrayList<Repositorio> repositorios;
    private RepositorioDAO dao;

    public SampleDataRepositorio(RepositorioDAO rep) throws IOException {
        repositorios = new ArrayList<>();
        this.dao = rep;
        if (dao.vazio())
        {
            
        }
        else
        {
            
        }
    }
    
    
}
