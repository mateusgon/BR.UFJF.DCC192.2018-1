package br.ufjf.dcc192;

class Pessoa {
    private String nome;
    private String email;
    private Integer commits;

    public Pessoa(String name, String email) {
        this.nome = name;
        this.email= email;
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

    public Integer getCommits() {
        return commits;
    }

    public void setCommits(Integer commits) {
        this.commits = commits;
    }

    @Override
    public String toString() {
        return nome + " - " +  email;
    }
    
    
    
}
