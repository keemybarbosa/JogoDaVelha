package br.com.keemy.jogodavelha;

import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc;

        Board board;

        do {
            try {

                Inicialize();

                sc = new Scanner(System.in);
                System.out.println("Informe o nome do JOGADOR1:");
                String jogador1 = sc.nextLine();
                System.out.println("Informe o nome do JOGADOR2:");
                String jogador2 = sc.nextLine();

                board = new Board(new Jogador(jogador1), new Jogador(jogador2));

                board.show();

                int posicao = 0;

                while ((board.getJogadaAtual() <= 9) && !board.verificaGanhador()) {

                    if (board.getJogadaAtual() > 8) {
                        System.out.println("AHHHH O JOGO DEU VELHA! EMPATE!!!!");
                        break;
                    }

                    System.out.printf("MOVIMENTO %d: %s ESCOLHA UM NÚMERO correspondente a sua jogada:", board.getJogadaAtual()+1, board.getJogadorAtual().getNome());
                    posicao = sc.nextInt();
                    board.Jogar (posicao);

                    if (board.verificaGanhador()) {
                        System.out.println("Jogo encerrado");
                        break;
                    }

                    while (posicao < 1 || posicao > 9) {
                        System.out.println("Você não escreveu um numero, escreva um número no tabuleiro:");
                        posicao = sc.nextInt();
                        board.Jogar(posicao);
                    }
                }

            } catch (Exception e) {
                System.out.println("Você não digitou um argumento válido\n:( VAMOS ENCERRAR O JOGO :(.");
                System.exit(0);
            }


        } while (ContinuarJogo());


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

    public static void Inicialize() {
        System.out.println("__________________________________________________\n" +
                "|                OKUBARO´S GAMES                 |\n" +
                "|                 JOGO DA VELHA                  |\n" +
                "|________________________________________________|");
        System.out.println("O jogo do dia:" + DateFormat.getDateInstance().format(new Date()) + "\n*PRECISAMOS DE 2 JOGADORES PARA INICIAR O JOGO*");
    }

}