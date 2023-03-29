package bj14916;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int idx = n/5;

        int result = -1;
        while(idx >= 0) {
            int count = 0;
            int copyN = n;

            copyN -= idx*5;
            count = idx;

            if (copyN % 2 == 0 ) {
                count += copyN/2;
                result = count;
                break;
            }

            idx--;
        }

        System.out.println(result);
    }
}