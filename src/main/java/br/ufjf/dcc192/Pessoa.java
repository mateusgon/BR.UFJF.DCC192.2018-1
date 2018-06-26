package br.ufjf.dcc192;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    
    private Integer codigoPessoa;
    private String nome;
    private String email;
    private Integer interfaceG;
    private Integer bancoDeDados;
    private Integer escritaELeitura;
    private Integer applets;
    private Integer net;
    private Integer especifica;
    private List<Commits> commits;

    public Pessoa() {
        bancoDeDados = 0;
        interfaceG = 0;
        escritaELeitura = 0;
        applets = 0;
        net = 0;
        especifica = 0;
    }

    
    
    public Pessoa(String name, String email) {
        this.nome = name;
        this.email= email;
        commits = new ArrayList<>();
        bancoDeDados = 0;
        interfaceG = 0;
        escritaELeitura = 0;
        applets = 0;
        net = 0;
        especifica = 0;
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
        return nome + " - " +  email + " - Commits: " + commits.size();
    }

    public List<Commits> getCommits() {
        return commits;
    }

    public void setCommits(List<Commits> commits) {
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

    public Integer getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(Integer codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public Integer getEscritaELeitura() {
        return escritaELeitura;
    }

    public void setEscritaELeitura(Integer escritaELeitura) {
        this.escritaELeitura = escritaELeitura;
    }

    public Integer getApplets() {
        return applets;
    }

    public void setApplets(Integer applets) {
        this.applets = applets;
    }

    public Integer getNet() {
        return net;
    }

    public void setNet(Integer net) {
        this.net = net;
    }

    public Integer getEspecifica() {
        return especifica;
    }

    public void setEspecifica(Integer especifica) {
        this.especifica = especifica;
    }
    
    
    
}
