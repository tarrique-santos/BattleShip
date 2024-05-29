package org.example;

import java.util.Random;
import java.util.Scanner;

public class TestVersion {
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
    // private static final String LETTERS[] = new String[]{colors[1] + "S" + colors[0], colors[3] + "F" + colors[0], colors[6] + "C" + colors[0], colors[7] + "D" + colors[0]}, UNCHARTED = colors[9] + "?" + colors[0], SEA = colors[5] + "~" + colors[0], MISS = "!", ALF[] = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}, divider = colors[9] +"+---+---+---+---+---+---+---+---+---+---+"+ colors[0], pipe = colors[9] +"|"+ colors[0];
    private static final String LETTERS[] = new String[]{"S","F", "C", "D"}, UNCHARTED = "?", SEA = "~", MISS = "!", ALF[] = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}, divider = colors[9] +"+---+---+---+---+---+---+---+---+---+---+"+ colors[0], pipe = colors[9] +"|"+ colors[0];
    private static String legendas[] = new String[]{"null", "Modo de jogo: ", "null", "[ " + UNCHARTED + " ] - Área obscura", "[ " + SEA + " ] - Mar", "[ " + LETTERS[0] + " ] - Submarino", "[ " + LETTERS[1] + " ] - Fragata", "[ " + LETTERS[2] + " ] - Corveta", "[ " + LETTERS[3] + " ] - Destroyer", "null"}, space = "";
    private static String board[][] = new String[LENGTH_BOARD][LENGTH_BOARD], board2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
    private static String fakeBoard[][] = new String[LENGTH_BOARD][LENGTH_BOARD], fakeBoard2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
    private static String nameBots[] = new String[]{"Máquina", "Robô", "Pirata", "Marujo"}, ships[] = new String[]{"um Submarino", "uma Fragata", "uma Corveta", "um Destroyer"}, p1 = "", p2 = "", gameMode = "";
    private static boolean turn = true, oneTime = true;
    private static int TotalPoints() {
        for (int i = 0; i < lengthOfShips.length; i++) {
            total += lengthOfShips[i] * numberOfShips[i];
        }return total;
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
                    gameMode = "Player x Machine";
                    legendas[1] += gameMode;
                    ChooseNamePlayers();
                    PlayGame(playing, turn);
                    break;
                case "M":
                    gameMode = "Player x Player";
                    legendas[1] += gameMode;
                    ChooseNamePlayers();
                    PlayGame(playing, turn);
                    break;
                default: System.out.println("Option incorrect!!");
            }
        }
    }
    private static String LenghtSpaces(String length) {
        String spaces = "";
        for (int i = 0; i < length.length(); i++) {
            spaces += " ";
        }return spaces;
    }
    public static void ChooseNamePlayers() {
        System.out.printf("Enter the name of the 1st user: ");
        p1 = s.next();
        if (gameMode.contains("Machine")) p2 = nameBots[r.nextInt(nameBots.length)];
        else {
            System.out.printf("Enter the name of the 2nd user: ");
            p2 = s.next();
        }space = ScoreBoard().length() >= legendas[1].length() ? LenghtSpaces(ScoreBoard()) : LenghtSpaces(legendas[1]);
    }
    public static void StartBoard() {
        for (int i = 0; i < LENGTH_BOARD; ++i) {
            for (int j = 0; j < LENGTH_BOARD; ++j) {
                board[i][j] = UNCHARTED;     board2[i][j] = UNCHARTED;
                fakeBoard[i][j] = UNCHARTED; fakeBoard2[i][j] = UNCHARTED;
            }
        }
    }
    public static String SpaceDifference(String legenda) { // Math.max(space.length() - legenda.length(), 0);
        int diff = space.length() - legenda.length() >= 0 ? space.length() - legenda.length() : 0;
        String spaces = "";
        for (int j = 0; j < diff; j++) {
            spaces += " ";
        }return spaces;
    }
    public static String ScoreBoard(){
        // String t = p1 + " - [ " + colors[3] + points + colors[0] + " ] vs [ " + colors[3] + points2 + colors[0] + " ] - " + p2;
        return p1 + " - [ " + points +" ] vs [ " + points2  + " ] - " + p2;
    }
    public static void ViewBoard() {
        System.out.printf(colors[0] + "     A   B   C   D   E   F   G   H   I   J   "+ SpaceDifference("") +"   A   B   C   D   E   F   G   H   I   J" + colors[0] + "\n");
        System.out.printf("   " + divider +"  "+ SpaceDifference("") + divider + "\n");
        for (int i = 0; i < LENGTH_BOARD; i++) {
            System.out.printf("%d  %s", i, pipe);
            for (int j = 0; j < LENGTH_BOARD; j++) {
                System.out.printf(" %s %s", fakeBoard[i][j], pipe);
            }
            if (i == 2) {
                System.out.printf(" %s%s ",ScoreBoard(), SpaceDifference(ScoreBoard()));
            } else if (!legendas[i].equals("null")) {
                System.out.printf(" %s%s ", legendas[i], SpaceDifference(legendas[i]));
            } else {
                System.out.print("  "+ SpaceDifference(""));
            }System.out.print(pipe);
            for (int j = 0; j < LENGTH_BOARD; j++) {
                System.out.printf(" %s %s", fakeBoard2[i][j], pipe);
            }System.out.printf(" %d\n   %s%s \n", i, divider, SpaceDifference("") +"  "+ divider);
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
    public static boolean IsNumberValid(String xIndex, int x[]) {
        try {
            int value = Integer.parseInt(xIndex);
            if (value >= 0 && value < LENGTH_BOARD) {
                x[0] = value;
                System.out.printf("X: %d\n", value);
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.printf("xIndex: %s\n", xIndex);
            return false;
        }return false;
    }
    public static boolean AreValidCoordinates(String y, String xIndex, int x[]) {
        return (LetterToNumber(y) != -1) && (IsNumberValid(xIndex, x)) && (x[0] != -1);
    }
    public static void PlayGame(boolean playing, boolean turn) {
        while ((points <= POINTS) || (points2 <= POINTS)) {
            int x[] = new int[]{ 0 }, yIndex = 0; String y = "", xIndex = "", target = "", fakeTarget = "";
            System.out.printf("%s's turn\n", turn ? p1 : p2);
            ViewBoard();
            if (turn) {
                System.out.printf("%s enter the coordinate X: ", turn ? p1 : p2);
                xIndex = s.next().toUpperCase();

                System.out.printf("%s enter the coordinate Y: ", turn ? p1 : p2);
                y = s.next().toUpperCase();

                if (AreValidCoordinates(y, xIndex, x)) {
                    target = board[x[0]][LetterToNumber(y)];
                    fakeTarget = fakeBoard[x[0]][LetterToNumber(y)];
                } else if ((LetterToNumber(y) == -1) || (x[0] == -1)) {
                    System.out.printf(TextPositionInvalid()); oneTime = false;
                }
            } else if (gameMode.contains("Machine")) {
                x[0] = r.nextInt(0, LENGTH_BOARD);
                yIndex = r.nextInt(0, LENGTH_BOARD);
                y = ALF[yIndex];

                System.out.printf("%s shoot in the coordinate X: %d and Y: %s\n", p2, x[0], y);
                target = board2[x[0]][yIndex];
                fakeTarget = fakeBoard2[x[0]][yIndex];
            } else {
                System.out.printf("%s enter the coordinate X: ", p2);
                xIndex = s.next().toUpperCase();
                System.out.printf("%s enter the coordinate Y: ", p2);
                y = s.next().toUpperCase();
            }
            if (AreValidCoordinates(y, xIndex, x)) {
                System.out.printf("Valido!!\n");
                target = turn ? board[x[0]][LetterToNumber(y)] : board2[x[0]][LetterToNumber(y)];
                fakeTarget = turn ? fakeBoard[x[0]][LetterToNumber(y)] : fakeBoard2[x[0]][LetterToNumber(y)];
            } else {
                System.out.printf(TextPositionInvalid()+ "Não validou\n %d %s %s %d \n",LetterToNumber(y), IsNumberValid(xIndex, x), y, x[0]); oneTime = false;
            }
            if (Shoot(target, fakeTarget).equals(MISS)) {
                System.out.printf("%s hit the %s(%s)!!\n", turn ? p1 : p2, Shoot(target, fakeTarget).equals(SEA) ? "sea" : GetNameShip(target) , target);
                System.out.printf("%s choose another coordinate!!\n", turn ? p1 : p2);
            } else if (Shoot(target, fakeTarget).equals(SEA)) {
                System.out.printf("%s hit the %s!!\n", turn ? p1 : p2, SEA);
                turn = PassingTheTurn(turn, turn ? fakeBoard : fakeBoard2, x[0], y);
            } else {
                if(AreValidCoordinates(y, xIndex, x)) {
                    Scoring(turn, turn ? fakeBoard : fakeBoard2, x[0], y, Shoot(target, fakeTarget));
                    System.out.printf("%s hit the %s!!\n", turn ? p1 : p2, GetNameShip(target));
                } else {
                    System.out.printf(TextPositionInvalid()); oneTime = false;
                }
            } oneTime = true;
        }System.out.printf("%s won the game!!", points >= POINTS ? p1 : p2);
    }
    public static String TextPositionInvalid() {
        return oneTime ? "Position invalid!!\n" : "";
    }
    public static int Scoring(boolean turn, String fb[][], int x, String y, String target) {
        fb[x][LetterToNumber(y)] = target;
        return turn ? points++ : points2++;
    }
    public static boolean PassingTheTurn(boolean turn, String fb[][], int x, String y) {
        fb[x][LetterToNumber(y)] = SEA;
        return turn ? false : true;
    }
    public static int LetterToNumber(String y) {
        for (int i = 0; i < ALF.length; i++)
            if (y.equals(ALF[i])) {
                return i;
            }
        return -1;
    }
    public static String Shoot(String target, String fakeTarget) {
        if (target.equals(UNCHARTED)) return SEA;
        else if (fakeTarget.equals(SEA) || (fakeTarget.equals(LETTERS[0])) || (fakeTarget.equals(LETTERS[1])) || (fakeTarget.equals(LETTERS[2])) || (fakeTarget.equals(LETTERS[3]))) return MISS;
        else return target;
    }
    public static String GetNameShip(String target) {
        for (int i = 0; i < LETTERS.length; i++) {
            if (target.equals(LETTERS[i])) {
                return ships[i];
            }
        }return null;
    }
}