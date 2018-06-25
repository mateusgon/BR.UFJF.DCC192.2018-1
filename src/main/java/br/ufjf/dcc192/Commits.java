package br.ufjf.dcc192;

import java.util.List;
import org.repodriller.domain.Modification;

class Commits {
    private Pessoa pessoa;
    private String id;
    private String comentario;
    private List<Modification> modificacoes;
    
    public Commits(String hash, String name, String email, String msg, List<Modification> modificacoes) {
        this.id = hash;
        this.comentario = msg;
        this.modificacoes = modificacoes;
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

    public List<Modification> getModificacoes() {
        return modificacoes;
    }

    public void setModificacoes(List<Modification> modificacoes) {
        this.modificacoes = modificacoes;
    }

    
    
}
