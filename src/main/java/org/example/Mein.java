//package org.example;
//
//import java.util.Random;
//import java.util.Scanner;
//
//public class Mein {
//    private static int LENGTH_BOARD = 10;
//    private static Scanner s = new Scanner(System.in);
//    private static Random r = new Random();
//    public static void main(String[] args) {
//        String board[][] = new String[LENGTH_BOARD][LENGTH_BOARD], board2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
//        String fakeBoard[][] = new String[LENGTH_BOARD][LENGTH_BOARD], fakeBoard2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
//        String legendas[] = new String[]{"null", "null", "[ ~ ] - Mar\n", "[ X ] - Alvo atingido\n", "[ S ] - Submarino\n", "[ F ] - Fragata\n", "[ C ] - Corveta\n", "[ D ] - Destroyer\n", "null", "null"};
//        boolean vez = true, playing = true, changeMode = true, positionShips = true;
//        String submarino = "S", fragata = "F", corveta = "C",destroyer = "D"; // String ships[] = new String[]{"submarino", "fragata", "corveta", "destroyer"};
//        String letters[] = new String[]{"S", "F", "C", "D"};
//        String ships[] = new String[]{"submarino", "fragata", "corveta", "destroyer"};
//        int numberOfShips[] = new int[]{5, 3, 2, 2}, lengthOfShips[] = new int[]{1, 2, 3, 4};
//        int points = 0, points2 = 0;
//
//        StartBoard(board, board2, fakeBoard, fakeBoard2);
//        while(changeMode) {
//            System.out.printf("[ R ] - Player x Machine\n[ M ] - Player x Player\nOption: ");
//            switch (s.next().toUpperCase()) {
//                case "R":
//                    changeMode = false;
//                    //PlayAgainMachine(playing, board, board2, fakeBoard, fakeBoard2, vez, legendas);
//                    break;
//                case "M":
//                    changeMode = false;
//                    break;
//                default:
//                    System.out.println("Option incorrect!!");
//            }
//        }
//
//        /*public static void PlayAgainMachine(boolean playing, String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2, boolean vez, String[] legendas) {
//            System.out.printf("Enter the name of the 1st user: ");
//            String p1 = s.next();
//            System.out.printf("Enter the name of the 2nd user: ");
//            String p2 = s.next();
//
//            while(playing) {
//                if (vez) {
//                    System.out.printf("Vez de %s!\n", p1);
//                } else {
//                    System.out.printf("Vez de %s!\n", p2);
//                }
//            }
//        }*/
//       public static void ViewBord(String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2, boolean vez, String[] legendas) {
//            System.out.printf("    A   B   C   D   E   F   G   H   I   J\n");
//
//           for(int i = 0; i < LENGTH_BOARD; ++i) {
//                System.out.printf("%d ", i);
//
//                for(int j = 0; j < LENGTH_BOARD; ++j) {
//                    if (vez) {
//                        System.out.printf("| %s ", board[i][j]);
//                    } else {
//                        System.out.printf("| %s ", board2[i][j]);
//                    }
//                }
//
//                if (!legendas[i].equals("null")) {
//                    System.out.printf("| %s  +---+---+---+---+---+---+---+---+---+---+\n", legendas[i]);
//                } else {
//                    System.out.printf("|\n  +---+---+---+---+---+---+---+---+---+---+\n");
//                }
//            }
//
//        }
//    }
//    public static void StartBoard(String[][] board, String[][] board2, String[][] fakeBoard, String[][] fakeBoard2) {
//        for(int i = 0; i < LENGTH_BOARD; ++i) {
//            for(int j = 0; j < LENGTH_BOARD; ++j) {
//                board[i][j] = "~";
//                board2[i][j] = "~";
//                fakeBoard[i][j] = "~";
//                fakeBoard2[i][j] = "~";
//            }
//        }
//    }
//}
