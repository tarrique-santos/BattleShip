package org.example;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int LENGTH_BOARD = 10 , POINTS = 25; // pontos totais equivalem ao número de slots de barcos espalhados no tabuleiro.
    private static Scanner s = new Scanner(System.in);
    private static Random r = new Random();
    private static String letters[] = new String[]{"S", "F", "C", "D"}, UNCHARTED = "?", SEA = "~";
    private static String legendas[] = new String[]{"null", "Modo de jogo: " , "null" , "[ "+UNCHARTED+" ] - Área obscura\n" ,"[ "+SEA+" ] - Mar\n", "[ "+letters[0]+" ] - Submarino\n", "[ "+letters[1]+" ] - Fragata\n", "[ "+letters[2]+" ] - Corveta\n", "[ "+letters[3]+" ] - Destroyer\n", "null"};
    private static String board[][] = new String[LENGTH_BOARD][LENGTH_BOARD], board2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
    private static String fakeBoard[][] = new String[LENGTH_BOARD][LENGTH_BOARD], fakeBoard2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
    private static  String nameBots[] = new String[]{"Máquina", "Robô", "Pirata", "Marujo"}, p1 = "", p2 = ""; // ships[] = new String[]{"submarino", "fragata", "corveta", "destroyer"},

    public static void main(String[] args) {
        boolean vez = true, playing = true, changeMode = true;//String submarino = "S", fragata = "F", corveta = "C",destroyer = "D";// String ships[] = new String[]{"submarino", "fragata", "corveta", "destroyer"};
        int numberOfShips[] = new int[]{5, 3, 2, 2}, lengthOfShips[] = new int[]{1, 2, 3, 4};
        int points = 0, points2 = 0;
        StartBoard();
        AllocateShips(vez, numberOfShips, lengthOfShips);
        while(changeMode) {
            System.out.printf("[ R ] - Joagador x Máquina\n[ M ] - Jogador x Jogador\nOpção: ");
            String modo = s.next().toUpperCase();
            switch (modo) {
                case "R":
                    changeMode = false;
                    int n = r.nextInt(0, 3);
                    p2 = nameBots[n];
                    legendas[1] += "Jogador vs Máquina\n";
                    PlayAgainMachine(playing, vez, points, points2);
                    break;
                case "M":
                    changeMode = false;
                    legendas[1] += "Multijogador\n";
                    PlayMultiplyer(vez, playing, points, points2);
                    break;
                default: System.out.println("Opção incorreta!!");
            }
        }
    }
    public static void StartBoard() {
        for(int i = 0; i < LENGTH_BOARD; ++i) {
            for(int j = 0; j < LENGTH_BOARD; ++j) {
                board[i][j] = UNCHARTED; board2[i][j] = UNCHARTED;
                fakeBoard[i][j] = UNCHARTED; fakeBoard2[i][j] = UNCHARTED;
            }
        }
    }
    public static void ViewBord(boolean vez, int points, int points2) {
        System.out.printf("     A   B   C   D   E   F   G   H   I   J\n   +---+---+---+---+---+---+---+---+---+---+\n");
        for(int i = 0; i < LENGTH_BOARD; ++i) {
            System.out.printf("%d  ", i);
            for(int j = 0; j < LENGTH_BOARD; ++j) {
                if (vez) {
                    System.out.printf("| %s ", fakeBoard[i][j]);
                } else {
                    System.out.printf("| %s ", fakeBoard2[i][j]);
                }
            }
            if (!legendas[i].equals("null") || legendas[i].contains("Modo de jogo")) {
                System.out.printf("| %s   +---+---+---+---+---+---+---+---+---+---+\n", legendas[i]);
            } else if (i == 2) {
                System.out.printf("| %s - [ %d ] vs [ %d ] - %s\n   +---+---+---+---+---+---+---+---+---+---+\n", p1, points, points2, p2);
            } else if (legendas[i].equals("null")) {
                System.out.printf("|\n   +---+---+---+---+---+---+---+---+---+---+\n");
            }
        }
    }
    public static void PlayAgainMachine(boolean playing, boolean vez, int points, int points2) {
        System.out.printf("Informe o nome do 1º jogador: ");
        p1 = s.next();
        System.out.printf("Seu oponente é o(a) %s!\n", p2);
        while (playing) {
            if(points == POINTS) {
                System.out.printf("Parabéns %s, você venceu!\n", p1);
                playing = false;
            }else if(points2 == 25){// os pontos dependerá do número de slots de barcos espalahados no tabuleiro.
                System.out.printf("Parabéns %s, você venceu!\n", p2);
                playing = false;
            }
            else if (vez) {
                System.out.printf("Vez de %s!\n", p1);
                ViewBord(vez, points, points2);
                System.out.print("Informe a coordenada x: ");
                int x = 0;
                try{
                    x = s.nextInt();
                }catch (InputMismatchException e) {
                    System.out.println("Posição inválida! Informe um número válido!");
                }
                System.out.print("Informe a coordenada y: ");
                String y = s.next().toUpperCase();
                LetterToNumber(y);
                if((x > 9 || x < 0) || (LetterToNumber(y) == -1)){
                    System.out.println("Posição inválida!");
                }else if(ShootTheShip(vez, x, y)){
                    System.out.printf("Continue jogando!\n");
                    points++;
                }else{
                    vez = !vez;
                }
            }else {
                System.out.printf("Vez de %s!\n", p2);
                ViewBord(vez, points, points2);
                int x = r.nextInt(0,LENGTH_BOARD);
                String alf = "ABCDEFGHIJ"; int index = r.nextInt(0,LENGTH_BOARD);
                String y = String.valueOf(alf.charAt(index));
                if(ShootTheShip(vez, x, y)){
                    System.out.printf("Continue jogando!\n");
                    points2++;
                }else{
                    vez = true;
                }
            }
        }
    }
    public static void PlayMultiplyer(boolean vez, boolean playing, int points, int points2) {
        System.out.printf("Informe o nome do 1º jogador: ");
        p1 = s.next();
        System.out.printf("Informe o nome do 2º jogador: ");
        p2 = s.next();
        while(playing) {
            if(points == POINTS) {
                System.out.printf("Parabéns %s, você venceu!\n", p1);
                playing = false;
            }else if(points2 == POINTS){// os pontos dependerá do número de slots de barcos espalahados no tabuleiro.
                System.out.printf("Parabéns %s, você venceu!\n", p2);
                playing = false;
            }
            else if (vez) {
                System.out.printf("Vez de %s!\n", p1);
                ViewBord(vez, points, points2);
                System.out.print("Informe a coordenada x: ");
                int x = 0;
                try{
                    x = s.nextInt();
                }catch (InputMismatchException e) {
                    System.out.println("Posição inválida! Informe um número válido!");
                }
                System.out.print("Informe a coordenada y: ");
                String y = s.next().toUpperCase();
                LetterToNumber(y);
                if((x > 9 || x < 0) || (LetterToNumber(y) == -1)){
                    System.out.println("Posição inválida!");
                }else if(ShootTheShip(vez, x, y) && (points < POINTS)){
                    System.out.printf("Continue jogando!\n");
                    points++;
                }else{
                    vez = !vez;
                }
            }else {
                System.out.printf("Vez de %s!\n", p2);
                ViewBord(vez, points, points2);
                System.out.print("Informe a coordenada x: ");
                int x = 0;
                try{
                    x = s.nextInt();
                }catch (InputMismatchException e) {
                    System.out.println("Posição inválida! Informe um número válido!");
                }
                System.out.print("Informe a coordenada y: ");
                String y = String.valueOf(s.next().charAt(0)).toUpperCase();
                if((x > 9 || x < 0) || (LetterToNumber(y) == -1)){
                    System.out.println("Posição inválida!");
                }else if(ShootTheShip(vez, x, y) && (points2 < POINTS)){
                    System.out.printf("Continue jogando!\n");
                    points2++;
                }else{
                    vez = true;
                }
            }
        }
    }
    public static boolean ShootTheShip(boolean vez, int x, String y) {
        if(vez){
            if((fakeBoard[x][LetterToNumber(y)] == "~") || (fakeBoard[x][LetterToNumber(y)] == "S") || (fakeBoard[x][LetterToNumber(y)] == "F") || (fakeBoard[x][LetterToNumber(y)] == "C") || (fakeBoard[x][LetterToNumber(y)] == "D")){
                System.out.printf("Alvo já atingido!\n");return false;
            }
            else if(board[x][LetterToNumber(y)].equals("?")){
                System.out.printf("%s errou o tiro!\n", p1);
                fakeBoard[x][LetterToNumber(y)] = "~";return false;
            } else if (board[x][LetterToNumber(y)].equals("S")){
                fakeBoard[x][LetterToNumber(y)] = "S";
                System.out.printf("%s acertou um submarino!\n",p1);
            } else if(board[x][LetterToNumber(y)].equals("F")){
                fakeBoard[x][LetterToNumber(y)] = "F";
                System.out.printf("%s acertou uma fragata!\n",p1);
            } else if(board[x][LetterToNumber(y)].equals("C")){
                fakeBoard[x][LetterToNumber(y)] = "C";
                System.out.printf("%s acertou uma corveta!\n",p1);
            } else if(board[x][LetterToNumber(y)].equals("D")){
                fakeBoard[x][LetterToNumber(y)] = "D";
                System.out.printf("%s acertou um destroyer!\n", p1);
            }
        }else{
            if((fakeBoard2[x][LetterToNumber(y)] == "~") || (fakeBoard2[x][LetterToNumber(y)] == "S") || (fakeBoard2[x][LetterToNumber(y)] == "F") || (fakeBoard2[x][LetterToNumber(y)] == "C") || (fakeBoard2[x][LetterToNumber(y)] == "D")){
                System.out.printf("Alvo já atingido!\n");return false;// dependendo da situação, pode ser que o jogador perca a vez, por isso o return false
            }
            else if(board2[x][LetterToNumber(y)].equals("?")){
                System.out.printf("%s errou o tiro!\n", p2);
                fakeBoard2[x][LetterToNumber(y)] = "~";return false;
            } else if (board2[x][LetterToNumber(y)].equals("S")){
                fakeBoard2[x][LetterToNumber(y)] = "S";
                System.out.printf("%s acertou um submarino!\n",p2);
            } else if(board2[x][LetterToNumber(y)].equals("F")){
                fakeBoard2[x][LetterToNumber(y)] = "F";
                System.out.printf("%s acertou uma fragata!\n",p2);
            } else if(board2[x][LetterToNumber(y)].equals("C")){
                fakeBoard2[x][LetterToNumber(y)] = "C";
                System.out.printf("%s acertou uma corveta!\n",p2);
            } else if(board2[x][LetterToNumber(y)].equals("D")){
                fakeBoard2[x][LetterToNumber(y)] = "D";
                System.out.printf("%s acertou um destroyer!\n", p2);
            }
        }return true;
    }
    public static int LetterToNumber(String y){
        String alf = "ABCDEFGHIJ";
        for (int i = 0; i < alf.length(); i++) {
            if(alf.charAt(i) == y.charAt(0)){
                return i;
            }
        }return -1;
    }
    public static void AllocateShips(boolean vez, int[] numberOfShips, int[] lengthOfShips) {
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < numberOfShips[i]; j++) {
                boolean shipPlaced = false;
                while (!shipPlaced) {
                    int x = r.nextInt(0,LENGTH_BOARD);
                    int y = r.nextInt(0,LENGTH_BOARD);
                    boolean position = r.nextBoolean();
                    int length = lengthOfShips[i];
                    if (CanAllocate(x, y, position, length, vez)) {
                        if (position) {
                            for (int k = x; k < length + x; k++) {
                                board[k][y] = letters[i];
                            }
                        } else {
                            for (int k = y; k < length + y; k++) {
                                board[x][k] = letters[i];
                            }
                        }shipPlaced = true;
                    }
                }
            }
        }
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < numberOfShips[i]; j++) {
                boolean shipPlaced = false;
                while (!shipPlaced) {
                    int x = r.nextInt(0,LENGTH_BOARD);
                    int y = r.nextInt(0,LENGTH_BOARD);
                    boolean position = r.nextBoolean();
                    int length = lengthOfShips[i];
                    if (CanAllocate(x, y, position, length, !vez)) {
                        if (position) {
                            for (int k = x; k < length + x; k++) {
                                board2[k][y] = letters[i];
                            }
                        } else {
                            for (int k = y; k < length + y; k++) {
                                board2[x][k] = letters[i];
                            }
                        }shipPlaced = true;
                    }
                }
            }
        }
    }
    public static boolean CanAllocate(int x, int y, boolean position, int length, boolean vez) {
        if (position && length + x <= LENGTH_BOARD) {
            for (int j = x; j < length + x; j++) {
                if ((board[j][y] != "?" && vez)||(board2[j][y] != "?" && !vez)) {
                    return false;
                }
            }return true;
        } else if (!position && length + y <= LENGTH_BOARD) {
            for (int j = y; j < length + y; j++) {
                if ((board[x][j] != "?" && vez)||(board2[x][j] != "?" && !vez)) {
                    return false;
                }
            }
        } else {
            return false;
        }return true;
    }
}