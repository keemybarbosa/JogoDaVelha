package br.com.keemy.jogodavelha;

import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc ;

        Board board;

        do {
                imprimirTopo();

                sc = new Scanner(System.in);

                board = getBoard(sc);

                board.show();

                do{
                    jogar(sc, board);

                    checarFimPartida(board);
                } while (!board.isFinish());

        } while (continuarJogo(sc));


    }

    private static void checarFimPartida(Board board) {
        GameEvent e = board.getEvent();
        if (e.getEventType() == GameEvent.GameEventType.GAME_FINISH){
            System.out.println(e.getMessage());
            board.setFinish(true);
        }
    }

    private static Board getBoard(Scanner sc) {
        System.out.println("Informe o nome do JOGADOR1:");
        String jogador1 = sc.nextLine();
        System.out.println("Informe o nome do JOGADOR2:");
        String jogador2 = sc.nextLine();

        return new Board(new Jogador(jogador1), new Jogador(jogador2));
    }

    private static void jogar(Scanner sc, Board board) {
        int posicao;
        boolean repetirJogada = false;
        do {
            System.out.printf("MOVIMENTO %d: %s ESCOLHA UM NÚMERO correspondente a sua jogada:", board.getJogadaAtual() + 1, board.getJogadorAtual().getNome());
            if (sc.hasNextInt()) {
                posicao = sc.nextInt();
                if (posicao < 1 || posicao > 9) {
                    System.out.println("Você não escreveu um numero, escreva um número no tabuleiro:");
                    repetirJogada = true;
                } else {
                    if (board.isEmpty(posicao)){
                        board.Jogar(posicao);
                    } else {
                        System.out.println("Posição ocupada! Escolha outra opção!");
                    }
                }
            } else {
                System.out.println("Não digitou algo válido!");
                sc = new Scanner(System.in); //LIMPAR O SCANNER
                repetirJogada = true;
            }
        } while (repetirJogada);
    }

    private static boolean continuarJogo(Scanner sc) {
        sc = new Scanner(System.in);
        System.out.println("Deseja encerrar o jogo? Escreva: SIM para encerrar e NÃO para continuar.");
        String entrada = sc.next().toUpperCase();
        if (entrada.equalsIgnoreCase("SIM")) {
            System.out.println("OK! Jogo encerrado até a próxima");
            return false;
        }
        return true;
    }

    public static void imprimirTopo() {
        System.out.println("__________________________________________________\n" +
                "|                OKUBARO´S GAMES                 |\n" +
                "|                 JOGO DA VELHA                  |\n" +
                "|________________________________________________|");
        System.out.println("O jogo do dia:" + DateFormat.getDateInstance().format(new Date()) + "\n*PRECISAMOS DE 2 JOGADORES PARA INICIAR O JOGO*");
    }

}