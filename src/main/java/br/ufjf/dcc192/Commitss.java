package br.ufjf.dcc192;

class Commitss {
    private Pessoa pessoa;
    private String id;
    private String comentario;
    
    public Commitss(String hash, String name, String email, String msg) {
        this.id = hash;
        this.comentario = msg;
        pessoa = new Pessoa(name, email);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
