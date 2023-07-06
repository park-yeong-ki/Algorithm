package bj2230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int start = 0;
        int end = 1;
        int ans = Integer.MAX_VALUE;
        while (start <= end && end < N) {
            int diff = arr[end] - arr[start];
            if (diff >= M) {
                ans = Math.min(ans, diff);
                start++;
            } else {
                end++;
            }
        }

        System.out.println(ans);
    }
}