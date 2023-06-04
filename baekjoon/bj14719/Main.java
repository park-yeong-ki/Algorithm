package bj14719;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int W = sc.nextInt();

        int[] arr = new int[W];
        for (int i = 0; i < W; i++) {
            arr[i] = sc.nextInt();
        }

        int start = W;
        for (int i = 0; i < W; i++) {
            if (arr[i] != 0) {
                start = i;
                break;
            }
        }

        int sum = 0;
        while (start < W - 1) {
            int end = W;
            int max = Integer.MIN_VALUE;
            for (int i = start + 1; i < W; i++) {
                if (arr[i] - arr[start] >= 0) {
                    end = i;
                    break;
                } else {
                    if (max <= arr[i] - arr[start]) {
                        max = arr[i] - arr[start];
                        end = i;
                    }
                }
            }

            for (int i = start + 1; i <= end - 1; i++) {
                sum += Math.min(arr[start], arr[end]) - arr[i];
            }

            start = end;
        }

        System.out.println(sum);
    }
}
