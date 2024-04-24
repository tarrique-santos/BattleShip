package org.example;

import java.util.Random;
import java.util.Scanner;

public class Alpha {
    private static int LENGTH_BOARD = 10;
    private static Scanner s;
    private static Random r;

    public Alpha() {
    }

    public static void main(String[] args) {
        String board[][] = new String[LENGTH_BOARD][LENGTH_BOARD], board2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
        String fakeBoard[][] = new String[LENGTH_BOARD][LENGTH_BOARD], fakeBoard2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
        String legendas[] = new String[]{"null", "null", "[ ~ ] - Mar\n", "[ X ] - Alvo atingido\n", "[ S ] - Submarino\n", "[ F ] - Fragata\n", "[ C ] - Corveta\n", "[ D ] - Destroyer\n", "null", "null"};
        boolean vez = true, playing = true, changeMode = true, positionShips = true;
        String submarino = "S", fragata = "F", corveta = "C",destroyer = "D"; // String ships[] = new String[]{"submarino", "fragata", "corveta", "destroyer"};
        String letters[] = new String[]{"S", "F", "C", "D"};
        String ships[] = new String[]{"submarino", "fragata", "corveta", "destroyer"};
        int numberOfShips[] = new int[]{5, 3, 2, 2}, lengthOfShips[] = new int[]{1, 2, 3, 4};
        int points = 0, points2 = 0;
        StartBoard(board, board2, fakeBoard, fakeBoard2);
        PositionShips(ships, letters, numberOfShips, lengthOfShips, positionShips, points, points2, board, board2, fakeBoard, fakeBoard2, vez, legendas);

        while(changeMode) {
            System.out.printf("[ R ] - Player x Machine\n[ M ] - Player x Player\nOption: ");
            switch (s.next().toUpperCase()) {
                case "R":
                    changeMode = false;
                    PlayAgainMachine(playing, board, board2, fakeBoard, fakeBoard2, vez, legendas);
                    break;
                case "M":
                    changeMode = false;
                    break;
                default:
                    System.out.println("Option incorrect!!");
            }
        }
    }
    public static void PlayAgainMachine(boolean playing, String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2, boolean vez, String[] legendas) {
        System.out.printf("Enter the name of the 1st user: ");
        String p1 = s.next();
        System.out.printf("Enter the name of the 2nd user: ");
        String p2 = s.next();

        while(playing) {
            if (vez) {
                System.out.printf("Vez de %s!\n", p1);
            } else {
                System.out.printf("Vez de %s!\n", p2);
            }
        }
    }
    public static void StartBoard(String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2) {
        for(int i = 0; i < LENGTH_BOARD; ++i) {
            for(int j = 0; j < LENGTH_BOARD; ++j) {
                board[i][j] = "~";
                board2[i][j] = "~";
                fakeBoard[i][j] = "~";
                fakeBoard2[i][j] = "~";
            }
        }
    }
    public static boolean CanAllocate(int x, int y, boolean position, int length, String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2, boolean vez, String[] legendas) {
        int i = 0;
        if (position && length + x < 9 - length) {
            System.out.println("caiu no if da funcao");

            while(i < length) {
                if (board[x + i][y] == "~") {
                    ++i;
                    System.out.println("Caiu no while");
                }
            }
            if (i != length) {
                return false;
            } else {
                return true;
            }
        } else if (!position && length + y < 9) {
            System.out.println("caiu no else if da funcao");

            for(i = 0; i < length; ++i) {
                if (board[x][y + i] == "~") {
                    ++i;
                }
            }
            if (i != length) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public static void PositionShips(String[] ships, String[] letters, int[] numberOfShips, int[] lengthOfShips, boolean positionShips, int points, int points2, String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2, boolean vez, String[] lengendas) {
        int totalShips[] = new int[]{12, 12};
        int index = 0;
        int iShips = 0;
        while(positionShips) {
            int x;
            int y;
            int length;
            boolean position;
            do {
                while(positionShips) {
                    if (totalShips[iShips] != 0 && index < lengthOfShips[index]) {
                        x = r.nextInt(0, 10);
                        y = r.nextInt(0, 10);
                        position = r.nextBoolean();
                        length = lengthOfShips[index];
                    }

                    if (totalShips[0] == 0 && totalShips[1] == 0) {
                        positionShips = false;
                    } else {
                        ++iShips;
                    }
                }

                return;
            } while(!CanAllocate(x, y, position, length, board, board2, fakeBoard, fakeBoard2, vez, lengendas));

//            System.out.println(CanAllocate(x, y, position, length, board, board2, fakeBoard, fakeBoard2, vez, lengendas));
//
//            for(int j = 0; j < lengthOfShips[index]; ++j) {
//                board[x][j] = letters[index];
//            }
//
//            int var10002 = totalShips[iShips]--;
//            ++index;
        }
    }

    public static void ViewBord(String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2, boolean vez, String[] legendas) {
        System.out.printf("    A   B   C   D   E   F   G   H   I   J\n");

        for(int i = 0; i < LENGTH_BOARD; ++i) {
            System.out.printf("%d ", i);

            for(int j = 0; j < LENGTH_BOARD; ++j) {
                if (vez) {
                    System.out.printf("| %s ", board[i][j]);
                } else {
                    System.out.printf("| %s ", board2[i][j]);
                }
            }

            if (!legendas[i].equals("null")) {
                System.out.printf("| %s  +---+---+---+---+---+---+---+---+---+---+\n", legendas[i]);
            } else {
                System.out.printf("|\n  +---+---+---+---+---+---+---+---+---+---+\n");
            }
        }

    }

    static {
        s = new Scanner(System.in);
        r = new Random();
    }
}
