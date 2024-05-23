package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int numberOfShips[] = new int[]{5, 3, 2, 2}, lengthOfShips[] = new int[]{1, 2, 3, 4}, points = 0, points2 = 0, total = 0;
    private static final int LENGTH_BOARD = 10, POINTS = TotalPoints();
    private static Scanner s = new Scanner(System.in);
    private static Random r = new Random();
    private static final String[] colors = new String[]{
            "\u001B[0m",            // [0] - Reset
            "\u001B[1;35m",         // [1] - Magenta Bold
            "\u001B[1;31m",         // [2] - Vermelho Bold
            "\u001B[1;33m",         // [3] - Amarelo Bold
            "\u001B[1;32m",         // [4] - Verde Bold
            "\u001B[1;36m",         // [5] - Ciano Bold
            "\u001B[1;92m",         // [6] - Verde Claro Bold
            "\u001B[1;34m",         // [7] - Azul Bold
            "\u001B[1;1m",         // [8] - Fúcsia Bold
            "\u001B[1;37m"          // [9] - Branco Bold
    };
    private static final String LETTERS[] = new String[]{colors[1] + "S" + colors[0], colors[3] + "F" + colors[0], colors[6] + "C" + colors[0], colors[7] + "D" + colors[0]}, UNCHARTED = colors[9] + "?" + colors[0], SEA = colors[5] + "~" + colors[0], MISS = "!", ALF[] = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}, divider = colors[9] +"+---+---+---+---+---+---+---+---+---+---+"+ colors[0], pipe = colors[9] +"|"+ colors[0];
    private static String legendas[] = new String[]{"null", "Modo de jogo: ", "null", "[ " + UNCHARTED + " ] - Área obscura\n", "[ " + SEA + " ] - Mar\n", "[ " + LETTERS[0] + " ] - Submarino\n", "[ " + LETTERS[1] + " ] - Fragata\n", "[ " + LETTERS[2] + " ] - Corveta\n", "[ " + LETTERS[3] + " ] - Destroyer\n", "null"};
    private static String board[][] = new String[LENGTH_BOARD][LENGTH_BOARD], board2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
    private static String fakeBoard[][] = new String[LENGTH_BOARD][LENGTH_BOARD], fakeBoard2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
    private static String nameBots[] = new String[]{"Máquina", "Robô", "Pirata", "Marujo"}, ships[] = new String[]{"um Submarino", "uma Fragata", "uma Corveta", "um Destroyer"}, p1 = "", p2 = "", gameMode = "";
    private static boolean turn = true;

    private static int TotalPoints() {
        for (int i = 0; i < lengthOfShips.length; i++) {
            total += lengthOfShips[i] * numberOfShips[i];
        }
        return total;
    }
    public static void main(String[] args) {
        boolean playing = true;
        StartBoard();
        AllocateShips(board); AllocateShips(board2);
        ChooseMode(playing, turn);
    }
    public static void ChooseMode(boolean playing, boolean turn) {
        while (true) {
            System.out.printf("[ R ] - Player x Machine\n[ M ] - Player x Player\nOption: ");
            switch (s.next().toUpperCase()) {
                case "R":
                    gameMode = colors[4] + "Player x Machine" + colors[0];
                    legendas[1] += gameMode + "\n";
                    ChooseNamePlayers();
                    PlayGame(playing, turn);
                    break;
                case "M":
                    gameMode = "Player x Player";
                    legendas[1] += gameMode + "\n";
                    ChooseNamePlayers();
                    PlayGame(playing, turn);
                    break;
                default:
                    System.out.println("Option incorrect!!");
            }
        }
    }
    public static void ChooseNamePlayers() {
        System.out.printf("Enter the name of the 1st user: ");
        p1 = s.next();
        if (gameMode.contains("Machine")) p2 = nameBots[r.nextInt(nameBots.length)];
        else {
            System.out.printf("Enter the name of the 2nd user: ");
            p2 = s.next();
        }
    }
    public static void StartBoard() {
        for (int i = 0; i < LENGTH_BOARD; ++i) {
            for (int j = 0; j < LENGTH_BOARD; ++j) {
                board[i][j] = UNCHARTED;
                board2[i][j] = UNCHARTED;
                fakeBoard[i][j] = UNCHARTED;
                fakeBoard2[i][j] = UNCHARTED;
            }
        }
    }
    public static String ScoreBoard(){
        return p1 + " - [ " + colors[3] + points + colors[0] + " ] vs [ " + colors[3] + points2 + colors[0] + " ] - " + p2;
    }
    public static void ViewBoard(String b[][]) {
        System.out.printf(colors[3] + "%s's turn\n" + colors[0], turn ? p1 : p2);
        System.out.println(colors[8] + "     A   B   C   D   E   F   G   H   I   J" + colors[0] + "\n   "+ divider);
        for (int i = 0; i < LENGTH_BOARD; i++) {
            System.out.printf(colors[8] + i + colors[0] + "  ");
            for (int j = 0; j < LENGTH_BOARD; j++)
                System.out.printf(pipe +" %s ", b[i][j]);
            if (!legendas[i].equals("null") || legendas[i].contains("Modo de jogo")) {
                System.out.printf(pipe + colors[8] + " %s   \n" + colors[0], legendas[i] +"   "+ divider);
            } else if (i == 2) {
                System.out.print(pipe +" "+ ScoreBoard() +"\n   "+ divider +"\n");
            } else if (legendas[i].equals("null")) {
                System.out.printf(pipe +"\n   "+ divider +"\n");
            }
        }
    }
    public static void AllocateShips(String b[][]) {
        for (int i = 0; i < LETTERS.length; i++) {
            for (int j = 0; j < numberOfShips[i]; j++) {
                boolean shipPlaced = false;
                while (!shipPlaced) {
                    int x = r.nextInt(0, LENGTH_BOARD);
                    int y = r.nextInt(0, LENGTH_BOARD);
                    boolean position = r.nextBoolean();
                    int length = lengthOfShips[i];
                    if (CanAllocate(x, y, position, length, b)) {
                        for (int k = 0; k < length; k++) {
                            int row = position ? x + k : x;
                            int column = position ? y : y + k;
                            b[row][column] = LETTERS[i];
                        }shipPlaced = true;
                    }
                }
            }
        }numberOfShips = new int[]{5, 3, 2, 2};
    }
    public static boolean CanAllocate(int x, int y, boolean position, int length, String b[][]) {
        String[][] targetBoard = b;
        for (int i = 0; i < length; i++) {
            int row = position ? x + i : x;
            int column = position ? y : y + i;
            if ((row >= LENGTH_BOARD) || (column >= LENGTH_BOARD) || (!targetBoard[row][column].equals(UNCHARTED))) {
                return false;
            }
        }return true;
    }
    public static void PlayGame(boolean playing, boolean turn) {
        while ((points != POINTS) || (points2 != POINTS)) {
            int x = 0, yIndex = 0;
            String y = "", target = "", fakeTarget = "";
            ViewBoard(turn ? fakeBoard : fakeBoard2);
            if (turn) {
                System.out.printf("%s enter the coordinate X: ", turn ? p1 : p2);
                x = s.nextInt();

                System.out.printf("%s enter the coordinate Y: ", turn ? p1 : p2);
                y = s.next().toUpperCase();

                if (LetterToNumber(y) != -1) {
                    target = board[x][LetterToNumber(y)];
                    fakeTarget = fakeBoard[x][LetterToNumber(y)];
                } else if (LetterToNumber(y) == -1) {
                    System.out.printf("Position invalid!!\n");
                }
            } else if (gameMode.contains("Machine")) {
                x = r.nextInt(0, LENGTH_BOARD);
                yIndex = r.nextInt(0, LENGTH_BOARD);
                y = ALF[yIndex];
                System.out.printf("%s shoot in the coordinate X: %d and Y: %s\n", p2, x, y);
                target = board2[x][yIndex];
                fakeTarget = fakeBoard2[x][yIndex];

            } else {
                System.out.printf("%s enter the coordinate X: ", turn ? p1 : p2);
                x = s.nextInt();
                System.out.printf("%s enter the coordinate Y: ", turn ? p1 : p2);
                y = s.next().toUpperCase();
            }
            if (LetterToNumber(y) != -1) {
                target = turn ? board[x][LetterToNumber(y)] : board2[x][LetterToNumber(y)];
                fakeTarget = turn ? fakeBoard[x][LetterToNumber(y)] : fakeBoard2[x][LetterToNumber(y)];

            } else {
                System.out.printf("Position invalid!!\n");
            }
            String shotResult = Shoot(target, fakeTarget);
            if (shotResult.equals(MISS)) {
                System.out.printf("%s hit the sea(%s)!!\n", turn ? p1 : p2, shotResult);
                System.out.printf("%s choose another coordinate!!\n", turn ? p1 : p2);
            } else if (shotResult.equals(SEA)) {
                System.out.printf("%s hit the %s!!\n", turn ? p1 : p2, SEA);
                if (turn) {
                    fakeBoard[x][LetterToNumber(y)] = SEA;
                    turn = !turn;
                } else {
                    fakeBoard2[x][LetterToNumber(y)] = SEA;
                    turn = true;
                }
            } else {
                if (turn) {
                    fakeBoard[x][LetterToNumber(y)] = target;
                    points++;
                } else {
                    fakeBoard2[x][LetterToNumber(y)] = target;
                    points2++;
                }System.out.printf("%s hit the %s!!\n", turn ? p1 : p2, GetNameShip(target));
            }
        }System.out.printf("%s won the game!!", points == POINTS ? p1 : p2);
    }
    public static int LetterToNumber(String y) {
        for (int i = 0; i < ALF.length; i++)
            if (y.equals(ALF[i])) return i;
        return -1;
    }
    public static String Shoot(String target, String fakeTarget) {
        if (target.equals(UNCHARTED)) {
            return SEA;
        } else if (fakeTarget.equals(SEA)) {
            return MISS;
        } else {
            return target;
        }
    }
    public static String GetNameShip(String target) {
        for (int i = 0; i < LETTERS.length; i++) {
            if (target.equals(LETTERS[i])) {
                return ships[i];
            }
        }return null;
    }
}