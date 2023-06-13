package bj1072;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int X = sc.nextInt();
        int Y = sc.nextInt();

        int Z = (int) ((long) Y * 100 / X);

        int start = 1;
        int end = (int) 1e9;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int num = (int) ((long) (Y + mid) * 100 / (X + mid));

            if (num > Z) {
                end = mid - 1;
                ans = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(ans);
    }
}