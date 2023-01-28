package br.com.keemy.jogodavelha;

public class Board {

    char[][] tabuleiro = {{'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}};
    Jogador[] jogador = new Jogador[2];
    int jogadaAtual;

    public Board(Jogador jogador1, Jogador jogador2) {
        this.jogadaAtual = 0;
        this.jogador[0] = jogador1;
        this.jogador[0].setSimbolo('X');
        this.jogador[1] = jogador2;
        this.jogador[1].setSimbolo('0');
    }

    public char[][] getTabuleiro() {
        return tabuleiro;
    }

    public void show() {

        for (char[] row : this.getTabuleiro()) {
            for (char collumn : row) {
                System.out.print(collumn + "   ");
            }
            System.out.println();
        }
    }

    public void Jogar(int posicao) {

        int linha = Double.valueOf(Math.floor((posicao-1)/3)).intValue();
        int coluna = Double.valueOf(Math.floor((posicao-1)%3)).intValue();

        this.getTabuleiro() [linha][coluna] = this.getJogadorAtual().getSimbolo();

        this.setJogadaAtual(this.getJogadaAtual()+1);

        this.show();

    }

    public boolean verificaGanhador() {

        //Verifica as linhas
        for (int i = 0; i < jogador.length; i++) {
            for (int linha = 0; linha < 3; linha++) {
                int count = 0;
                for (int coluna = 0; coluna < 3; coluna++) {
                    if (this.getTabuleiro()[linha][coluna] == jogador[i].getSimbolo()) {
                        count++;
                    }
                }
                if(count==3) {
                    System.out.println(jogador[i].getNome() + " VENCEU A PARTIDA!");
                    return true;
                }
            }
        }

        //TODO: VERIFICAR COLUNAS E DIAGONAIS

        return false;
    }

    public int getJogadaAtual() {
        return jogadaAtual;
    }

    public void setJogadaAtual(int jogadaAtual) {
        this.jogadaAtual = jogadaAtual;
    }

    public Jogador getJogadorAtual() {
        return this.jogador[this.getJogadaAtual() % 2];
    }
}
