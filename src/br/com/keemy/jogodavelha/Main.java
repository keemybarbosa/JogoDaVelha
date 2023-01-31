package br.com.keemy.jogodavelha;

import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Board board;

        do {
            try {

                ImprimirTopo();

                board = getBoard(sc);

                board.show();

                do{
                    jogar(sc, board);

                    GameEvent e = board.getEvent();
                    if (e.getEventType() == GameEvent.GameEventType.GAME_FINISH){
                        System.out.println(e.getMessage());
                        break;
                    }
                } while (true);

            } catch (Exception e) {
                System.out.println("Você não digitou um argumento válido\n" +
                        ":( VAMOS ENCERRAR O JOGO :(.");
                System.exit(0);
            }


        } while (ContinuarJogo());


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
            posicao = sc.nextInt();
            if(posicao < 1 || posicao > 9) {
                System.out.println("Você não escreveu um numero, escreva um número no tabuleiro:");
                repetirJogada = true;
            }
            else {
                board.Jogar(posicao);
            }
        } while (repetirJogada);
    }

    private static boolean ContinuarJogo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja encerrar o jogo? Escreva: SIM para encerrar e NÃO para continuar.");
        String entrada = sc.next().toUpperCase();
        if (entrada.equalsIgnoreCase("SIM")) {
            System.out.println("OK! Jogo encerrado até a próxima");
            return false;
        }
        return true;
    }

    public static void ImprimirTopo() {
        System.out.println("__________________________________________________\n" +
                "|                OKUBARO´S GAMES                 |\n" +
                "|                 JOGO DA VELHA                  |\n" +
                "|________________________________________________|");
        System.out.println("O jogo do dia:" + DateFormat.getDateInstance().format(new Date()) + "\n*PRECISAMOS DE 2 JOGADORES PARA INICIAR O JOGO*");
    }

}