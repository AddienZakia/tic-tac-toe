public class w {
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

    public static void main(String[] args) {

        int[][] r = {
                // c
                /* r */ { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        int nomor = 3;
        int column = getPosition(9, nomor);
        int row = getPosition(nomor, 9);
        System.out.println("col : " + column);
        System.out.println("row : " + row);
        System.out.println(r[row][column]);
    }
}
