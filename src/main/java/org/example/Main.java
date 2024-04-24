package org.example;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static  int POINTS = 25;
    private static int LENGTH_BOARD = 10;
    private static Scanner s = new Scanner(System.in);
    private static Random r = new Random();

    public static void main(String[] args) {
        String board[][] = new String[LENGTH_BOARD][LENGTH_BOARD], board2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
        String fakeBoard[][] = new String[LENGTH_BOARD][LENGTH_BOARD], fakeBoard2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
        String legendas[] = new String[]{"null", "Game mode: " , "null" , "[ ~ ] - Sea\n", "[ S ] - Submarine\n", "[ F ] - Frigate\n", "[ C ] - Corvette\n", "[ D ] - Destroyer\n", "null", "null"};
        boolean vez = true, playing = true, changeMode = true, positionShips = true;//String submarino = "S", fragata = "F", corveta = "C",destroyer = "D";// String ships[] = new String[]{"submarino", "fragata", "corveta", "destroyer"};
        String letters[] = new String[]{"S", "F", "C", "D"};
        String ships[] = new String[]{"submarine", "frigate", "corvette", "destroyer"};
        String nameBots[] = new String[]{"Machine", "Robot", "Capitain", "Sailor"}, p1 = "", p2 = "";
        int numberOfShips[] = new int[]{5, 3, 2, 2}, lengthOfShips[] = new int[]{1, 2, 3, 4};
        int points = 0, points2 = 0;

        StartBoard(board, board2, fakeBoard, fakeBoard2);
        AllocateShips(board, board2, fakeBoard, fakeBoard2, vez, letters, ships, numberOfShips, lengthOfShips, legendas);
        while(changeMode) {
            System.out.printf("[ R ] - Player x Machine\n[ M ] - Player x Player\nOption: ");
            String modo = s.next().toUpperCase();
            switch (modo) {
                case "R":
                    changeMode = false;
                    int n = r.nextInt(0, 4);
                    p2 = nameBots[n];
                    legendas[1] += "Player vs Machine\n";
                    PlayAgainMachine(playing, board, board2, fakeBoard, fakeBoard2, vez, legendas, p2, points, points2);
                    break;
                case "M":
                    changeMode = false;
                    legendas[1] += "Multiplayer\n";
                    PlayMultiplyer(p1, p2, vez, playing, board, board2, fakeBoard, fakeBoard2, legendas, points, points2);
                    break;
                default:
                    System.out.println("Option incorrect!!");
            }
        }
    }
    public static void StartBoard(String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2) {
        for(int i = 0; i < LENGTH_BOARD; ++i) {
            for(int j = 0; j < LENGTH_BOARD; ++j) {
                board[i][j] = "?"; board2[i][j] = "?";
                fakeBoard[i][j] = "?"; fakeBoard2[i][j] = "?";
            }
        }
    }
    public static void ViewBord(String[][] fakeBoard, String[][] fakeBoard2, boolean vez, String[] legendas, String p1, String p2, int points, int points2) {
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
            if (!legendas[i].equals("null") || legendas[i].contains("Game mode")) {
                System.out.printf("| %s   +---+---+---+---+---+---+---+---+---+---+\n", legendas[i]);
            } else if (i == 2) {
                System.out.printf("| %s - [ %d ] vs [ %d ] - %s\n   +---+---+---+---+---+---+---+---+---+---+\n", p1, points, points2, p2);
            } else if (legendas[i].equals("null")) {
                System.out.printf("|\n   +---+---+---+---+---+---+---+---+---+---+\n");
            }
        }
    }
    public static void PlayAgainMachine(boolean playing, String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2, boolean vez, String[] legendas, String p2, int points, int points2) {
        System.out.printf("Enter the name of the 1st user: ");
        String p1 = s.next();
        System.out.printf("Seu oponente é o(a) %s!\n", p2);
        while (playing) {
            if(points == POINTS) {
                System.out.printf("Congratulations %s, you win!\n", p1);
                playing = false;
            }else if(points2 == 25){
                System.out.printf("Congratulations %s, you win!\n", p2);
                playing = false;
            }
            else if (vez) {
                System.out.printf("%s's turn!\n", p1);
                ViewBord(fakeBoard, fakeBoard2, vez, legendas, p1, p2, points, points2);
                System.out.print("Enter the x coordinate: ");
                int x = 0;
                try{
                    x = s.nextInt();
                }catch (InputMismatchException e) {
                    System.out.println("Invalid position! Enter a valid number!");
                }
                System.out.print("Enter the y coordinate: ");
                String y = s.next().toUpperCase();
                LetterToNumber(y);
                if((x > 9 || x < 0) || (LetterToNumber(y) == -1)){
                    System.out.println("Invalid position!");
                }else if(ShootTheShip(vez, x, y, board, board2, fakeBoard, fakeBoard2, p1, p2)){
                    System.out.printf("Keep playing!\n");
                    points++;
                }else{
                    vez = !vez;
                }
            }else {
                System.out.printf("%s's turn!\n", p2);
                ViewBord(fakeBoard, fakeBoard2, vez, legendas, p1, p2, points, points2);
                int x = r.nextInt(0,LENGTH_BOARD);
                String alf = "ABCDEFGHIJ"; int index = r.nextInt(0,LENGTH_BOARD);
                String y = String.valueOf(alf.charAt(index));
                if(ShootTheShip(vez, x, y, board, board2, fakeBoard, fakeBoard2, p1, p2)){
                    points2++;
                }else{
                    vez = true;
                }
            }
        }
    }
    public static void PlayMultiplyer(String p1, String p2, boolean vez, boolean playing, String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2, String[] legendas, int points, int points2) {
        System.out.printf("Enter the name of the 1st user: ");
        p1 = s.next();
        System.out.printf("Enter the name of the 2nd user: ");
        p2 = s.next();
        while(playing) {
            if(points == POINTS) {
                System.out.printf("Congratulations %s, you win!\n", p1);
                playing = false;
            }else if(points2 == POINTS){// os pontos dependerá do número de slots de barcos espalahados no tabuleiro.
                System.out.printf("Congratulations %s, you win!\n", p2);
                playing = false;
            }
            else if (vez) {
                System.out.printf("%s's turn!\n", p1);
                ViewBord(fakeBoard, fakeBoard2, vez, legendas, p1, p2, points, points2);
                System.out.print("Enter the x coordinate: ");
                int x = 0;
                try{
                    x = s.nextInt();
                }catch (InputMismatchException e) {
                    System.out.println("Invalid position! Enter a valid number!");
                }
                System.out.print("Enter the x coordinate: ");
                String y = s.next().toUpperCase();
                LetterToNumber(y);
                if((x > 9 || x < 0) || (LetterToNumber(y) == -1)){
                    System.out.println("Invalid position!");
                }else if(ShootTheShip(vez, x, y, board, board2, fakeBoard, fakeBoard2, p1, p2)){
                    System.out.printf("Keep playing!\n");
                    points++;
                }else{
                    vez = !vez;
                }
            }else {
                System.out.printf("%s's turn!\n", p2);
                ViewBord(fakeBoard, fakeBoard2, vez, legendas, p1, p2, points, points2);
                System.out.print("Enter the x coordinate: ");
                int x = 0;
                try{
                    x = s.nextInt();
                }catch (InputMismatchException e) {
                    System.out.println("Invalid position! Enter a valid number!");
                }
                System.out.print("Enter the y coordinate: ");
                String y = String.valueOf(s.next().charAt(0)).toUpperCase();
                if((x > 9 || x < 0) || (LetterToNumber(y) == -1)){
                    System.out.println("Invalid position!");
                }else if(ShootTheShip(vez, x, y, board, board2, fakeBoard, fakeBoard2, p1, p2)){
                    System.out.printf("Keep playing!\n");
                    points2++;
                }else{
                    vez = true;
                }
            }
        }
    }
    public static boolean ShootTheShip(boolean vez, int x, String y, String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2, String p1, String p2) {
        if(vez){
            if((fakeBoard[x][LetterToNumber(y)] == "~") || (fakeBoard[x][LetterToNumber(y)] == "S") || (fakeBoard[x][LetterToNumber(y)] == "F") || (fakeBoard[x][LetterToNumber(y)] == "C") || (fakeBoard[x][LetterToNumber(y)] == "D")){
                System.out.printf("Target already reached!\n");return false;
            }
            else if(board[x][LetterToNumber(y)].equals("?")){
                System.out.printf("%s missed the shot!\n", p1);
                fakeBoard[x][LetterToNumber(y)] = "~";return false;
            } else if (board[x][LetterToNumber(y)].equals("S")){
                fakeBoard[x][LetterToNumber(y)] = "S";
                System.out.printf("%s hit a submarine!\n",p1);
            } else if(board[x][LetterToNumber(y)].equals("F")){
                fakeBoard[x][LetterToNumber(y)] = "F";
                System.out.printf("%s hit a frigate!\n",p1);
            } else if(board[x][LetterToNumber(y)].equals("C")){
                fakeBoard[x][LetterToNumber(y)] = "C";
                System.out.printf("%s hit a corvette!\n",p1);
            } else if(board[x][LetterToNumber(y)].equals("D")){
                fakeBoard[x][LetterToNumber(y)] = "D";
                System.out.printf("%s hit a corvette!\n", p1);
            }
        }else{
            if((fakeBoard2[x][LetterToNumber(y)] == "~") || (fakeBoard2[x][LetterToNumber(y)] == "S") || (fakeBoard2[x][LetterToNumber(y)] == "F") || (fakeBoard2[x][LetterToNumber(y)] == "C") || (fakeBoard2[x][LetterToNumber(y)] == "D")){
                System.out.printf("Target already reached!\n");
                return false;// Depending on the situation, the player may lose their turn, which is why the return false
            }
            else if(board2[x][LetterToNumber(y)].equals("?")){
                System.out.printf("%s missed the shot!\n", p2);return false;
            } else if (board2[x][LetterToNumber(y)].equals("S")){
                fakeBoard2[x][LetterToNumber(y)] = "S";
                System.out.printf("%s hit a submarine!\n",p2);
            } else if(board2[x][LetterToNumber(y)].equals("F")){
                fakeBoard2[x][LetterToNumber(y)] = "F";
                System.out.printf("%s hit a frigate!\n",p2);
            } else if(board2[x][LetterToNumber(y)].equals("C")){
                fakeBoard2[x][LetterToNumber(y)] = "C";
                System.out.printf("%s hit a corvette!\n",p2);
            } else if(board2[x][LetterToNumber(y)].equals("D")){
                fakeBoard2[x][LetterToNumber(y)] = "D";
                System.out.printf("%s hit a corvette!\n", p2);
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
    public static void AllocateShips(String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2, boolean vez, String[] letters, String[] ships, int[] numberOfShips, int[] lengthOfShips, String[] legendas) {
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < numberOfShips[i]; j++) {
                boolean shipPlaced = false;
                while (!shipPlaced) {
                int x = r.nextInt(0,LENGTH_BOARD);
                    int y = r.nextInt(0,LENGTH_BOARD);
                    boolean position = r.nextBoolean();
                    int length = lengthOfShips[i];
                    if (CanAllocate(x, y, position, length, board, board2, fakeBoard, fakeBoard2, vez)) {
                        if (position) {
                            for (int k = x; k < length + x; k++) {
                                board[k][y] = letters[i];
                            }
                        } else {
                            for (int k = y; k < length + y; k++) {
                                board[x][k] = letters[i];
                            }
                        }
                        shipPlaced = true;
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

                    if (CanAllocate(x, y, position, length, board2, board, fakeBoard2, fakeBoard, !vez)) {
                        if (position) {
                            for (int k = x; k < length + x; k++) {
                                board2[k][y] = letters[i];
                            }
                        } else {
                            for (int k = y; k < length + y; k++) {
                                board2[x][k] = letters[i];
                            }
                        }
                        shipPlaced = true;
                    }
                }
            }
        }
    }
    public static boolean CanAllocate(int x, int y, boolean position, int length, String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2, boolean vez) {
        if (position && length + x <= LENGTH_BOARD) {
            for (int j = x; j < length + x; ++j) {
                if (board[j][y] != "?") {
                    return false;
                }
            }return true;
        } else if (!position && length + y <= LENGTH_BOARD) {
            for (int j = y; j < length + y; ++j) {
                if (board[x][j] != "?") {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
