import java.util.Scanner;

public class IdenticalArrays {
    public static void main(String[] args) {
        Scanner 1 = new Scanner(System.in);

        System.out.println("Enter the first 3x3 array:");
        int[][] m1 = read3x3(in);

        System.out.println("Enter the second 3x3 array:");
        int[][] m2 = read3x3(in);

        if (equals(m1, m2)) {
            System.out.println("The two arrays are identical.");
        } else {
            System.out.println("The two arrays are not identical.");
        }
        in.close();
    }

    private static int[][] read3x3(Scanner in) {
        int[][] m = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m[i][j] = in.nextInt();
            }
        }
        return m;
    }

    public static boolean equals(int[][] m1, int[][] m2) {
        if (m1 == null || m2 == null) return m1 == m2;
        if (m1.length != m2.length) return false;
        for (int i = 0; i < m1.length; i++) {
            if (m1[i] == null || m2[i] == null) {
                if (m1[i] != m2[i]) return false;
                else continue;
            }
            if (m1[i].length != m2[i].length) return false;
            for (int j = 0; j < m1[i].length; j++) {
                if (m1[i][j] != m2[i][j]) return false;
            }
        }
        return true;
    }
}
