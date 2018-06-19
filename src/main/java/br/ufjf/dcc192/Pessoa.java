package br.ufjf.dcc192;

import java.util.ArrayList;
import java.util.List;

class Pessoa {
    private String nome;
    private String email;
    private Integer interfaceG;
    private Integer bancoDeDados;
    private List<Commitss> commits;

    public Pessoa(String name, String email) {
        this.nome = name;
        this.email= email;
        commits = new ArrayList<>();
        bancoDeDados = 0;
        interfaceG = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nome + " - " +  email + " - Commits" + commits.size();
    }

    public List<Commitss> getCommits() {
        return commits;
    }

    public void setCommits(List<Commitss> commits) {
        this.commits = commits;
    }

    public Integer getInterfaceG() {
        return interfaceG;
    }

    public void setInterfaceG(Integer interfaceG) {
        this.interfaceG = interfaceG;
    }

    public Integer getBancoDeDados() {
        return bancoDeDados;
    }

    public void setBancoDeDados(Integer bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }
    
    
    
}
