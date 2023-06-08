package bj1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            if (min != Integer.MAX_VALUE) sum -= arr[start++];
            if (sum >= S) {
                while (sum - arr[start] >= S) {
                    sum -= arr[start++];
                }

                min = Math.min(min, i - start + 1);
            }
        }

        if (min == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(min);
    }
}
