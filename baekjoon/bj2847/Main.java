package bj2847;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int total = 0;
        for (int i = N-1; i >= 1; i--) {
            if (arr[i] <= arr[i - 1]) {
                int cnt = arr[i - 1] - arr[i] + 1;
                arr[i - 1] -= cnt;
                total += cnt;
            }
        }
        System.out.println(total);
    }
}
