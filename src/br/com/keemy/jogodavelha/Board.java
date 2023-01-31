package br.com.keemy.jogodavelha;

public class Board {

    char[][] tabuleiro = {{'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}};
    Jogador[] jogador = new Jogador[2];
    int jogadaAtual;
    boolean finish;

    public Board(Jogador jogador1, Jogador jogador2) {
        this.jogadaAtual = 0;
        this.jogador[0] = jogador1;
        this.jogador[0].setSimbolo('X');
        this.jogador[1] = jogador2;
        this.jogador[1].setSimbolo('0');

        this.finish = false;
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

    public char getValue(int posicao){
        int linha = Double.valueOf(Math.floor((posicao-1)/3)).intValue();
        int coluna = Double.valueOf(Math.floor((posicao-1)%3)).intValue();
        return this.getTabuleiro() [linha][coluna];
    }

    public boolean isEmpty(int posicao){
        return (this.getValue(posicao) != this.jogador[0].getSimbolo()) &&
                (this.getValue(posicao) != this.jogador[1].getSimbolo());
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
        //
        //Verifica as colunas
        for (int i = 0; i < jogador.length; i++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                int count = 0;
                for (int linha = 0; linha < 3; linha++) {
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

        //DIAGONAL PRINCIPAL
        for (int i = 0; i < jogador.length; i++){
            //diagonal principal
            int count = 0;
            for (int indice = 0; indice < 3; indice++) {
                if (this.getTabuleiro()[indice][indice] == jogador[i].getSimbolo()) {
                    count++;
                }
            }
            if(count==3) {
                System.out.println(jogador[i].getNome() + " VENCEU A PARTIDA!");
                return true;
            }
        }

        //DIAGONAL SECUNDÁRIA
        for (int i = 0; i < jogador.length; i++){
            //diagonal principal
            int count = 0;
            for (int indice = 0; indice < 3; indice++) {
                if (this.getTabuleiro()[2-indice][indice] == jogador[i].getSimbolo()) {
                    count++;
                }
            }
            if(count==3) {
                System.out.println(jogador[i].getNome() + " VENCEU A PARTIDA!");
                return true;
            }
        }

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

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public GameEvent getEvent() {
        GameEvent e = new GameEvent();
        if (this.verificaGanhador()) {
            e.setMessage("Jogo encerrado");
            e.setEventType(GameEvent.GameEventType.GAME_FINISH);
        } else if (this.getJogadaAtual() == 9){
            e.setMessage("AHHHH O JOGO DEU VELHA! EMPATE!!!!");
            e.setEventType(GameEvent.GameEventType.GAME_FINISH);
        }
        return e;

    }
}
