package org.example; // Package of the class.

import java.util.Random; // Import the Random class.
import java.util.Scanner; // Import the Scanner class.

public class DocumentedVersion { // Class of the program.
    /**
     * The length of the board.
     * numberOfShips[0] - Submarine(1 slot).
     * numberOfShips[1] - Frigate(2 slots).
     * numberOfShips[2] - Corvette(3 slots).
     * numberOfShips[3] - Destroyer(4 slots).
     */
    private static int numberOfShips[] = new int[]{5, 3, 2, 2};
    public static int lengthOfShips[] = new int[]{1, 2, 3, 4};
    /**
     * The score of the players.
     * score - Player 1.
     * score2 - Player 2.
     * total - sum of boat slots.
     */
    public static int score = 0, score2 = 0, total = 0;
    /**
     * The length of the board.
     * SCORE - Total scores.
     */
    private static final int LENGTH_BOARD = 10, SCORE = TotalScores();
    /**
     * The Scanner and Random classes.
     */
    private static Scanner s = new Scanner(System.in);
    private static Random r = new Random();
    /**
     * The colors of the board.
     */
    private static final String[] colors = new String[]{
            "\u001B[0m",            // [0] - Reset
            "\u001B[1;35m",         // [1] - Bold Magenta
            "\u001B[1;31m",         // [2] - Bold Red
            "\u001B[1;33m",         // [3] - Bold Yellow
            "\u001B[1;32m",         // [4] - Bold Green
            "\u001B[1;36m",         // [5] - Bold Cyan
            "\u001B[1;92m",         // [6] - Bold Light Green
            "\u001B[1;34m",         // [7] - Bold Blue
            "\u001B[1;1m",          // [8] - Bold Fuchsia
            "\u001B[1;37m"          // [9] - Bold White
    };
    /**
     * The caracteres of the board.
     * UNCHARTED - Uncharted area.
     * SEA - Sea.
     * divider - Divider of the board.
     * pipe - Pipe of the board.
     */
    private static final String UNCHARTED = colors[9] + "?" + colors[0], SEA = colors[5] + "~" + colors[0];
    private static final String divider = colors[9] +"+---+---+---+---+---+---+---+---+---+---+"+ colors[0], pipe = colors[9] +"|"+ colors[0];
    /**
     * The letters of the board.
     * LETTERS - Letters of the board.
     * LETTERS[0] - S (Submarine), LETTERS[1] - F (Frigate), LETTERS[2] - C (Corvette), LETTERS[3] - D (Destroyer).
     * MISS - Missed shot.
     * ALF - Alphabet of the board.
     */
    private static final String LETTERS[] = new String[]{colors[1] + "S" + colors[0], colors[3] + "F" + colors[0], colors[6] + "C" + colors[0], colors[7] + "D" + colors[0]};
    private static final String MISS = "!", ALF[] = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    /**
     * The subtitles of the board.
     * subtitles - Subtitles of the board.
     */
    private static String subtitles[] = new String[]{"null", "Game mode: ", "null", "[ " + UNCHARTED + " ] - Unknown area", "[ " + SEA + " ] - Sea", "[ " + LETTERS[0] + " ] - Submarine", "[ " + LETTERS[1] + " ] - Frigate", "[ " + LETTERS[2] + " ] - Corvette", "[ " + LETTERS[3] + " ] - Destroyer", "null"};
    /**
     * The board of the game.
     * board - Board of the player 1.
     * board2 - Board of the player 2.
     * fakeBoard - Fake board of the player 1.
     * fakeBoard2 - Fake board of the player 2.
     */
    private static String board[][] = new String[LENGTH_BOARD][LENGTH_BOARD], board2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
    private static String fakeBoard[][] = new String[LENGTH_BOARD][LENGTH_BOARD], fakeBoard2[][] = new String[LENGTH_BOARD][LENGTH_BOARD];
    /**
     * The names of the bots and ships.
     * nameBots - Names of the bots.
     * ships - Names of the ships.
     * p1 - Name of the player 1.
     * p2 - Name of the player 2.
     * gameMode - Game mode.
     * option - Option of the game.
     * turn - Turn of the players.
     * oneTime - To control the display of invalid coordinates.
     */
    private static String nameBots[] = new String[]{"Machine", "Robot", "Pirate", "Sailor"}, ships[] = new String[]{"submarine", "frigate", "corvette", "destroyer"}, p1 = "", p2 = "", gameMode = "", option = "";
    private static boolean turn = true, oneTime = true;
    /**
     * The method to calculate the total scores.
     * @return - The total scores.
     */
    private static int TotalScores() {
        for (int i = 0; i < lengthOfShips.length; i++) {
            total += lengthOfShips[i] * numberOfShips[i];
        }return total;
    }
    /**
     * The main method of the program.
     * @param args - Arguments of the main method.
     */
    public static void main(String[] args) {
        StartBoard();
        AllocateShips(board); AllocateShips(board2);
        ChooseMode();
    }
    /**
     * The method to choose the game mode.
     * Player x Machine - Player x Machine mode.
     * Player x Player - Player x Player mode.
     */
    public static void ChooseMode() {
        while (true) {
            System.out.printf("[ R ] - Player x Machine\n[ M ] - Player x Player\nOption: ");
            option = s.next().toUpperCase();
            switch (option) {
                case "R":
                    gameMode = colors[4] + "Player x Machine" + colors[0];
                    subtitles[1] += gameMode;
                    ChooseNamePlayers();
                    PlayGame();
                    break;
                case "M":
                    gameMode = "Player x Player";
                    subtitles[1] += gameMode;
                    ChooseNamePlayers();
                    PlayGame();
                    break;
                default: System.out.println("Option incorrect!!");
            }
        }
    }
    /**
     * The method to choose the names of the players.
     * p1 - Name of the player 1.
     * p2 - Name of the player 2.
     */
    public static void ChooseNamePlayers() {
        System.out.printf("Enter the name of the 1st user: ");
        p1 = s.next();
        if (gameMode.contains("Machine")) p2 = nameBots[r.nextInt(nameBots.length)];
        else {
            System.out.printf("Enter the name of the 2nd user: ");
            p2 = s.next();
        }
    }
    /**
     * The method to start the board.
     * UNCHARTED - Uncharted area.
     */
    public static void StartBoard() {
        for (int i = 0; i < LENGTH_BOARD; i++) {
            for (int j = 0; j < LENGTH_BOARD; j++) {
                board[i][j] = UNCHARTED;     board2[i][j] = UNCHARTED;
                fakeBoard[i][j] = UNCHARTED; fakeBoard2[i][j] = UNCHARTED;
            }
        }
    }
    /**
     * The method to view the scoreboard.
     * @return - The scoreboard.
     */
    public static String ScoreBoard(){
        return p1 + " - [ " + colors[3] + score + colors[0] + " ] vs [ " + colors[3] + score2 + colors[0] + " ] - " + p2;
    }
    /**
     * The method to view the board.
     * b - Board of the turn player.
     */
    public static void ViewBoard(String b[][]) {
        System.out.println(colors[8] + "     A   B   C   D   E   F   G   H   I   J" + colors[0] + "\n   "+ divider);
        for (int i = 0; i < LENGTH_BOARD; i++) {
            System.out.printf(colors[8] + i + colors[0] + "  ");
            for (int j = 0; j < LENGTH_BOARD; j++) System.out.printf(pipe +" %s ", b[i][j]);
            if (!subtitles[i].equals("null")) {
                System.out.printf(pipe + colors[8] + " %s   " + colors[0], subtitles[i]);
            } else if (i == 2) {
                System.out.print(pipe +" "+ ScoreBoard());

            }else if (subtitles[i].equals("null")) System.out.printf(pipe);
            System.out.printf("\n   "+ divider +"\n");
        }
    }
    /**
     * The method to allocate the ships.
     * b - Board of the player.
     */
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
        }numberOfShips = new int[]{5, 3, 2, 2}; // Reset the number of ships.
    }
    /**
     * The method to check if the ship can be allocated.
     * x - Coordinate X.
     * y - Coordinate Y.
     * position - Position of the ship.
     * length - Length of the ship.
     * b - Board of the player.
     * @return - If the ship can be allocated.
     */
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

    /**
     * The method to input the coordinate X.
     * @return - The coordinate X.
     */
    public static int XCoordinateInput() {
        int x = -1;
        while (x == -1) {
            System.out.printf("%s enter the coordinate X: ", turn ? p1 : p2);
            String xIndex = s.next();
            x = IsNumberValid(xIndex);
            if (x == -1) {
                System.out.printf(TextPositionInvalid());
            }
        }return x;
    }
    /**
     * The method to input the coordinate Y.
     * @return - The coordinate Y.
     */
    public static int YCoordinateInput() {
        int y = -1;
        while (y == -1) {
            System.out.printf("%s enter the coordinate Y: ", turn ? p1 : p2);
            String yIndex = s.next().toUpperCase();
            y = LetterToNumber(yIndex);
            if (y == -1) {
                System.out.printf(TextPositionInvalid());
            }
        }return y;
    }
    /**
     * The method to convert the letter to number.
     * y - Letter of the board.
     * @return - The number of the letter.
     */
    public static int LetterToNumber(String y) {
        for (int i = 0; i < ALF.length; i++) {
            if (y.equals(ALF[i])) return i;
        }return -1;
    }
    /**
     * The method to play the game.
     * score - Score of the player 1.
     * score2 - Score of the player 2.
     */
    public static void PlayGame() {
        /**
         * The loop to play the game.
         * while - While the score of the players is less than the total score.
         */
        while (score <= SCORE && score2 <= SCORE) {
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
            String target = turn ? board[x][y] : board2[x][y];
            String fakeTarget = turn ? fakeBoard[x][y] : fakeBoard2[x][y];

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
        }System.out.printf("%s won the game!!", score == SCORE ? p1 : p2);
    }
    /**
     * The method to insert the sea into the board.
     * fb - Fake board of the player.
     * x - Coordinate X.
     * y - Coordinate Y.
     */
    public static void InsertSeaIntoBoard(String fb[][], int x, int y) {
        fb[x][y] = SEA;
    }
    /**
     * The method to shoot the target.
     * target - Target of the player.
     * fakeTarget - Fake target of the player.
     * @return - The target.
     */
    public static String Shoot(String target, String fakeTarget) {
        if (target.equals(UNCHARTED)) {
            return SEA;
        } else if (TargetAlreadyReached(fakeTarget)) {
            return MISS;
        }return target;
    }
    /**
     * The method to check if the target is already reached.
     * fakeTarget - Fake target of the player.
     * @return - If the target is already reached.
     */
    public static boolean TargetAlreadyReached(String fakeTarget) {
        return (fakeTarget.equals(SEA) || fakeTarget.equals(LETTERS[0]) || fakeTarget.equals(LETTERS[1]) || fakeTarget.equals(LETTERS[2]) || fakeTarget.equals(LETTERS[3]));
    }
    /**
     * The method to pass the turn.
     * @return - If the turn is passed.
     */
    public static boolean PassingTurn() {
        return turn ? false : true;
    }
    /**
     * The method to score the target.
     * fb - Fake board of the player.
     * x - Coordinate X.
     * y - Coordinate Y.
     * target - Target of the player.
     * @return - The score of the player.
     */
    public static int Scoring(String fb[][], int x, int y, String target) {
        fb[x][y] = target;
        return turn ? score++ : score2++;
    }
    /**
     * The method to display the text of the invalid position.
     * @return - The text of the invalid position.
     */
    public static String TextPositionInvalid() {
        return oneTime ? "Position invalid!!\n" : "";
    }
    /**
     * The method to check if the number is valid.
     * xIndex - Index of the board.
     * @return - If the number is valid.
     */
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
    /**
     * The method to get the name of the ship.
     * target - Target of the player.
     * @return - The name of the ship.
     */
    public static String GetNameShip(String target) {
        for (int i = 0; i < LETTERS.length; i++) {
            if (target.equals(LETTERS[i])) {
                return ships[i];
            }
        }return UNCHARTED;
    }
}//Created by Tarrique D. Santos.