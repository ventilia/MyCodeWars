public class Matrix {

    public static int determinant(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return matrix[0][0];
        }
        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        int det = 0;
        int sign = 1;
        for (int i = 0; i < n; i++) {
            int[][] minor = new int[n - 1][n - 1];
            for (int row = 1; row < n; row++) {
                int colMinor = 0;
                for (int col = 0; col < n; col++) {
                    if (col != i) {
                        minor[row - 1][colMinor] = matrix[row][col];
                        colMinor++;
                    }
                }
            }
            det += sign * matrix[0][i] * determinant(minor);
            sign = -sign;
        }
        return det;
    }
}