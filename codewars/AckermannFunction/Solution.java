package AckermannFunction;

public class Solution {
    public static int Ackermann(int m, int n) {
        if (m < 0 || n < 0) {
            throw new IllegalArgumentException();
        }
        if (m == 0) {
            return n + 1;
        }
        if (n == 0) {
            return Ackermann(m - 1, 1);
        }
        return Ackermann(m - 1, Ackermann(m, n - 1));
    }
}
