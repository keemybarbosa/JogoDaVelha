package br.com.keemy.jogodavelha;

public class Jogador {
    private String nome;
    private Character simbolo;

    public Jogador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Character getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(Character simbolo) {
        this.simbolo = simbolo;
    }
}
