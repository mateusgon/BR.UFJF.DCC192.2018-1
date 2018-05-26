package br.ufjf.dcc192;

class Commitss {
    Pessoa pessoa;
    String id;
    String comentario;
    
    public Commitss(String hash, String name, String email, String msg) {
        this.id = hash;
        this.comentario = msg;
        pessoa = new Pessoa(name, email);
    }
    
}
