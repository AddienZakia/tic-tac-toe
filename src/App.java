import java.util.Random;
import java.util.Scanner;

public class App {

    static void print(String[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String value = data[i][j];
                System.out.print((value != null ? value : "*") + " ");
            }

            System.out.println(" ");
        }
    }

    static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static int getPosition(int row, int column) {
        int r = 9, c = 9;

        if (row <= 3)
            r = 0;
        else if (row <= 6)
            r = 1;
        else
            r = 2;

        if (column % 3 == 1)
            c = 0;
        else if (column % 3 == 2)
            c = 1;
        else
            c = 2;

        return row == 9 ? c : r;
    }

    static String checkFieldWin(String data[][]) {
        int[][] list = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 },

                { 1, 4, 7 },
                { 2, 5, 8 },
                { 3, 6, 9 },

                { 1, 5, 9 },
                { 3, 5, 7 },

        };

        char symbol = '?';
        for (int i = 0; i < list.length; i++) {
            int col1 = getPosition(9, list[i][0]);
            int row1 = getPosition(list[i][0], 9);

            int col2 = getPosition(9, list[i][1]);
            int row2 = getPosition(list[i][1], 9);

            int col3 = getPosition(9, list[i][2]);
            int row3 = getPosition(list[i][2], 9);

            if (data[row1][col1] != null &&
                    data[row1][col1] == data[row2][col2] &&
                    data[row1][col1] == data[row3][col3]) {
                symbol = data[row1][col1].charAt(0);
            }
        }

        // check continue/win/lose
        if (symbol == 'X')
            return "User";
        else if (symbol == 'O')
            return "Bot";
        else
            return "continue";
    }

    static void botTurn(String[][] data) {
        Random rand = new Random();

        int randomize = rand.nextInt(10);
        int row = getPosition(randomize, 9);
        int col = getPosition(9, randomize);

        if (data[row][col] != null)
            botTurn(data);
        else
            data[row][col] = "O";

    }

    public static void main(String[] args) {
        String[][] data = new String[3][3];
        try (Scanner input = new Scanner(System.in)) {
            int turn = 0; // 0 user (x), 1 bot (o)
            int row = 1, column = 1;

            while (true) {
                print(data);

                System.out.print("Masukkan nomor row : ");
                row = input.nextInt();

                System.out.print("Masukkan nomor column : ");
                column = input.nextInt();

                boolean checkRowColumnValid = (column > 3 || column < 1) || (row > 3 || row < 1);
                if (checkRowColumnValid) {
                    System.out.println("\nAngka tidak valid, mohon berikan angka valid (1-3)\n");
                    continue;
                }

                boolean emptyField = data[row - 1][column - 1] != null;
                if (emptyField) {
                    System.out.println("\nField sudah terisi\n");
                } else {
                    // set field
                    if (turn % 1 == 0) {
                        data[row - 1][column - 1] = "X";
                    } else {
                        data[row - 1][column - 1] = "O";
                    }

                    turn++;

                    if (turn != 9) {
                        botTurn(data);
                        turn++;
                    }

                    String winner = checkFieldWin(data);
                    if (!winner.equals("continue")) {
                        clear();
                        print(data);

                        System.out.println("The winner is : " + winner);
                        return;
                    }

                    boolean checkDraw = true;
                    for (int i = 0; i < data.length; i++) {
                        for (int j = 0; j < data.length; j++) {
                            if (data[i][j] == null || data[i][j] == "*")
                                checkDraw = false;
                        }
                    }

                    if (checkDraw) {
                        System.out.println("The game is draw :(");
                        return;
                    }

                    clear();
                }
            }
        }

    }
}
