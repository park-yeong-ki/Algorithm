package bj2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int start = 0;
        int end = N - 1;
        int min = Integer.MAX_VALUE;
        int ans1 = arr[start];
        int ans2 = arr[end];
        while (start < end) {
            int num = arr[start] + arr[end];
            if (min > Math.abs(num)) {
                min = Math.abs(num);
                ans1 = arr[start];
                ans2 = arr[end];
            }

            if (num < 0) {
                start++;
            } else if (num > 0) {
                end--;
            } else {
                start++;
                end--;
            }
        }

        System.out.println(ans1 + " " + ans2);
    }
}
