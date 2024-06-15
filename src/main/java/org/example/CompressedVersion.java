package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CompressedVersion {
    private static int numberOfShips[] = new int[]{5, 3, 2, 2}, lengthOfShips[] = new int[]{1, 2, 3, 4}, points = 0, points2 = 0, total = 0;
    private static final int LENGTH_BOARD = 10, POINTS = TotalPoints();
    private static Scanner s = new Scanner(System.in);
    private static Random r = new Random();
    private static final String[] colors = new String[]{"\u001B[0m", "\u001B[1;35m", "\u001B[1;31m", "\u001B[1;33m", "\u001B[1;32m", "\u001B[1;36m", "\u001B[1;92m", "\u001B[1;34m", "\u001B[1;1m", "\u001B[1;37m"}; // [0] - Reset, [1] - Magenta Bold, [2] - Vermelho Bold, [3] - Amarelo Bold, [4] - Verde Bold, [5] - Ciano Bold, [6] - Verde Claro Bold, [7] - Azul Bold, [8] - Fúcsia Bold, [9] - Branco Bold.
    private static final String UNCHARTED = colors[9] + "?" + colors[0], SEA = colors[5] + "~" + colors[0],  divider = colors[9] +"+---+---+---+---+---+---+---+---+---+---+"+ colors[0], pipe = colors[9] +"|"+ colors[0], spaceLetters = "     ";
    private static final String LETTERS[] = new String[]{colors[1] + "S" + colors[0], colors[3] + "F" + colors[0], colors[6] + "C" + colors[0], colors[7] + "D" + colors[0]}, MISS = "!", ALF[] = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static String subtitles[] = new String[]{"null", "Modo de jogo: ", "null", "[ " + UNCHARTED + " ] - Área obscura", "[ " + SEA + " ] - Mar", "[ " + LETTERS[0] + " ] - Submarino", "[ " + LETTERS[1] + " ] - Fragata", "[ " + LETTERS[2] + " ] - Corveta", "[ " + LETTERS[3] + " ] - Destroyer", "null"};
    private static String board[][] = new String[LENGTH_BOARD][LENGTH_BOARD], board2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
    private static String fakeBoard[][] = new String[LENGTH_BOARD][LENGTH_BOARD], fakeBoard2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
    private static String nameBots[] = new String[]{"Máquina", "Robô", "Pirata", "Marujo"}, ships[] = new String[]{"submarine", "frigate", "corvette", "destroyer"}, p1 = "", p2 = "", gameMode = "", option = "";
    private static boolean turn = true, oneTime = true;
    private static int TotalPoints() {
        for (int i = 0; i < lengthOfShips.length; i++) {
            total += lengthOfShips[i] * numberOfShips[i];
        }return total;
    }
    public static void main(String[] args) {
        StartBoard();
    }
    public static void ChooseMode() {
        while (true) {
            System.out.printf("[ R ] - Player x Machine\n[ M ] - Player x Player\nOption: ");
            option = s.next().toUpperCase();
            switch (option) {
                case "R"-> {
                    gameMode = colors[4] + "Player x Machine" + colors[0];
                    ChooseNamePlayers();
                    PlayGame();
                }
                case "M"-> {
                    gameMode = "Player x Player";
                    ChooseNamePlayers();
                    PlayGame();
                }
                default-> System.out.printf(TextOptionIncorrect());
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
        points = 0; points2 = 0; turn = true; p1 = ""; p2 = ""; gameMode = ""; option = "";

        for (int i = 0; i < LENGTH_BOARD; i++) {
            for (int j = 0; j < LENGTH_BOARD; j++) {
                board[i][j] = UNCHARTED;     board2[i][j] = UNCHARTED;
                fakeBoard[i][j] = UNCHARTED; fakeBoard2[i][j] = UNCHARTED;
            }
        }
        AllocateShips(board); AllocateShips(board2);
        ChooseMode();
    }
    public static String GetScoreBoard(){
        return p1 + " - [ " + colors[3] + points + colors[0] + " ] vs [ " + colors[3] + points2 + colors[0] + " ] - " + p2;
    }
    public static String GetSubtitles(int i) {
        String subtitles[] = new String[]{"null", "Game mode: " +gameMode, "null", "[ " + UNCHARTED + " ] - Unknown area", "[ " + SEA + " ] - Sea", "[ " + LETTERS[0] + " ] - Submarine"+"("+ lengthOfShips[0] +") slot", "[ " + LETTERS[1] + " ] - Frigate"+"("+ lengthOfShips[1] +") slots", "[ " + LETTERS[2] + " ] - Corvette"+"("+ lengthOfShips[2] +") slots", "[ " + LETTERS[3] + " ] - Destroyer"+"("+ lengthOfShips[3] +") slots", "null"};
        return subtitles[i];
    }
    public static void ViewBoard(String b[][]) {
        System.out.println(colors[8] + spaceLetters +"A   B   C   D   E   F   G   H   I   J" + colors[0] + "\n   "+ divider);
        for (int i = 0; i < LENGTH_BOARD; i++) {
            System.out.printf(colors[8] + i + colors[0] + "  ");
            for (int j = 0; j < LENGTH_BOARD; j++) System.out.printf(pipe +" %s ", b[i][j]);
            if (!subtitles[i].equals("null")) {
                System.out.printf(pipe + colors[8] + " %s   " + colors[0], GetSubtitles(i));
            } else if (i == 2) {
                System.out.print(pipe +" "+ GetScoreBoard());

            }else if (subtitles[i].equals("null")) System.out.printf(pipe);
            System.out.printf("\n   "+ divider +"\n");
        }
    }
    public static void AllocateShips(String b[][]) {
        for (int i = 0; i < LETTERS.length; i++) {
            for (int j = 0; j < numberOfShips[i]; j++) {
                boolean shipPlaced = false;
                while (!shipPlaced) {
                    int x = r.nextInt(LENGTH_BOARD);
                    int y = r.nextInt(LENGTH_BOARD);
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
            if (ValidatePosition(x, y, row, column, targetBoard)) {
                return false;
            }
        }return true;
    }
    public static boolean ValidatePosition(int x, int y, int row, int column,String targetBoard[][]) {
        return ((row < 0 || row >= LENGTH_BOARD) || (column < 0 || column >= LENGTH_BOARD) || (!targetBoard[row][column].equals(UNCHARTED)));
    }
    public static int XCoordinateInput() {
        int x = -1;
        while (x == -1) {
            System.out.printf("%s enter the coordinate X: ", turn ? p1 : p2);
            String xIndex = s.next();
            x = IsNumberValid(xIndex);
            if (x == -1) {
                System.out.printf(TextPositionInvalid(xIndex));
            }
        }return x;
    }
    public static int YCoordinateInput() {
        int y = -1;
        while (y == -1) {
            System.out.printf("%s enter the coordinate Y: ", turn ? p1 : p2);
            String yIndex = s.next().toUpperCase();
            y = LetterToNumber(yIndex);
            if (y == -1) {
                System.out.printf(TextPositionInvalid(yIndex));
            }
        }return y;
    }
    public static int LetterToNumber(String y) {
        for (int i = 0; i < ALF.length; i++) {
            if (y.equals(ALF[i])) return i;
        }return -1;
    }
    public static void PlayGame() {
        while (points < POINTS && points2 < POINTS) {
            System.out.printf(colors[3] + "%s's turn\n" + colors[0], turn ? p1 : p2);
            ViewBoard(turn ? fakeBoard : fakeBoard2);
            int x, y;
            if (option.equals("M") || (turn && option.equals("R"))) {
                x = XCoordinateInput();
                y = YCoordinateInput();
            } else {
                x = r.nextInt(LENGTH_BOARD);
                y = r.nextInt(LENGTH_BOARD);
                System.out.printf("%s shoot in the coordinate X: %d and Y: %s\n", p2, x, ALF[y]);
            }
            String target = turn ? board[x][y] : board2[x][y], fakeTarget = turn ? fakeBoard[x][y] : fakeBoard2[x][y];;
            if (TargetAlreadyReached(fakeTarget)) {
                System.out.printf("%s you already shot here, choose another coordinate!!\n", turn ? p1 : p2);

            } else if (Shoot(target, fakeTarget).equals(SEA)) {
                System.out.printf("%s hit the %s!!\n", turn ? p1 : p2, SEA);
                InsertSeaIntoBoard(turn ? fakeBoard : fakeBoard2, x, y);
                turn = PassingTurn();
            } else {
                Scoring(turn ? fakeBoard : fakeBoard2, x, y, target);
                System.out.printf("%s hit the %s!!\n", turn ? p1 : p2, GetNameShip(target));
            }
        }System.out.printf("%s won the game!!", points >= POINTS ? p1 : p2);
        ResetBattleShip();
    }
    public static String TextOptionIncorrect() {
        return "Option incorrect!!\n";
    }
    public static void InsertSeaIntoBoard(String fb[][], int x, int y) {
        fb[x][y] = SEA;
    }
    public static String Shoot(String target, String fakeTarget) {
        return target.equals(UNCHARTED) ? SEA : (TargetAlreadyReached(fakeTarget) ? MISS : target);
    }
    public static boolean TargetAlreadyReached(String fakeTarget) {
        return ((fakeTarget.equals(SEA) || fakeTarget.equals(LETTERS[0]) || fakeTarget.equals(LETTERS[1]) || fakeTarget.equals(LETTERS[2]) || fakeTarget.equals(LETTERS[3])));
    }
    public static boolean PassingTurn() {
        return turn ? false : true;
    }
    public static int Scoring(String fb[][], int x, int y, String target) {
        fb[x][y] = target;
        return turn ? points++ : points2++;
    }
    public static String TextPositionInvalid(String xy) {
        return oneTime ? "Position " +xy+ " is invalid!!\n" : "";
    }
    public static int IsNumberValid(String xIndex) {
        try {
            int value = Integer.parseInt(xIndex);
            if (value >= 0 && value < LENGTH_BOARD) {
                return value;
            }return -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public static String GetNameShip(String target) {
        int i = Arrays.asList(LETTERS).indexOf(target);
        return i != -1 ? ships[i] : UNCHARTED;
    }
    public static void ResetBattleShip() {
        while ((!option.equals("Y")) || (!option.equals("N"))) {
            System.out.printf("\nDo you want to play again? [ Y ] - Yes [ N ] - No\nOption: ");
            option = s.next().toUpperCase();
            switch (option) {
                case "Y"-> StartBoard();
                case "N"-> System.exit(0);
                default-> System.out.printf(TextOptionIncorrect());
            }
        }
    }
}//Created by Tarrique D. Santos.