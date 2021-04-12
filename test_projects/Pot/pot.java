import java.util.Scanner;
import java.lang.Math;

public class Pot {
    
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        int X = 0;

        int n = myObj.nextInt();

        int[] p = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = myObj.nextInt();
        }

        for (int i = 0; i < n; i++) {
            String[] p_str = p[i];

            int p_str_len = p_str.length();

            X += Math.pow(1, 1);

            System.out.println(p_str[2]);
        }
    }
}