package bj2471;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        long start = 0;
        long end = n;
        long ans = Long.MAX_VALUE;
        while (start <= end) {
            long mid = (start + end) / 2;

            if (Math.pow(mid, 2) < n) {
                start = mid + 1;
            } else if (Math.pow(mid, 2) > n) {
                end = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                ans = mid;
                break;
            }
        }

        System.out.println(ans);
    }
}
