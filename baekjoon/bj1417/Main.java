package bj1417;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int cnt = 0;
        while (true) {
            int maxIdx = 0;
            for (int i = 1; i < N; i++) {
                if (arr[maxIdx] <= arr[i]) {
                    maxIdx = i;
                }
            }

            if (maxIdx == 0) break;

            arr[0]++;
            arr[maxIdx]--;
            cnt++;
        }

        System.out.println(cnt);
    }
}
