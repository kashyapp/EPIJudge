package epi.kashyap;

import java.util.Arrays;

public class MatrixColumn {
    public static void main(String[] args) {
        System.out.println(search(new int[][]{
                {0, 0, 1, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {1, 1, 1, 1},
        }));
    }

    private static int search(int[][] mat) {
        int min = mat[0].length;
        for (int[] row : mat)
            min = Math.min(min, pivot(row));
        return min;
    }

    private static int pivot(int[] row) {
        System.out.println(Arrays.toString(row));
        int start = 0;
        int end = row.length;
        while (end - start > 1) {
            int mid = start + (end - start) / 2;
            if (row[mid] == 0)
                start = mid;
            else
                end = mid;
        }
        if (row[start] == 1)
            return start;
        return end;
    }
}
