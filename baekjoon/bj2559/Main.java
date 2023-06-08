package bj2559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int start = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            if (i >= K - 1) {
                if (i >= K) sum -= arr[start++];
                max = Math.max(max, sum);
            }
        }

        System.out.println(max);
    }
}
