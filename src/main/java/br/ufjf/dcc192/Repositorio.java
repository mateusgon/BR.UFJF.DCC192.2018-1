package br.ufjf.dcc192;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    
    private Integer codigoRepositorio;
    private String nome;
    private String url;
    private List<Pessoa> participantes;
    private int contador;
    private Boolean possivel;

    public Repositorio() {
    }
    
    public Repositorio(String nome, String url, List<Commits> commits) {
        this.participantes = new ArrayList<>();
        this.nome = nome;
        this.url = url;
        this.contador=0;
        this.possivel=false;
        for (Commits commit : commits) {
            Pessoa p;
            p = new Pessoa(commit.getPessoa().getNome(), commit.getPessoa().getEmail());
            if (contador == 0)
            {
                participantes.add(p);
                contador++;
            }
            for (Pessoa parts : participantes) {
                if (parts.getNome().equals(p.getNome()) || parts.getEmail().equals(p.getEmail()))
                {
                    //parts.setCommits(((parts.getCommits())+1));
                    possivel=false;
                    parts.getCommits().add(commit);
                    break;
                }
                else
                {
                    possivel=true;
                }
            }
            if (possivel)
            {
                p.getCommits().add(commit);
                participantes.add(p);    
            }
        }
    }

    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Pessoa> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Pessoa> participantes) {
        this.participantes = participantes;
    }

    @Override
    public String toString() {
        return nome + " - Possui " + getParticipantes().size() + " participantes";
    }

    public Integer getCodigoRepositorio() {
        return codigoRepositorio;
    }

    public void setCodigoRepositorio(Integer codigoRepositorio) {
        this.codigoRepositorio = codigoRepositorio;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public Boolean getPossivel() {
        return possivel;
    }

    public void setPossivel(Boolean possivel) {
        this.possivel = possivel;
    }
    
    
}
