package bj2512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int total = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            int max = total / (N - i);
            if (max >= arr[i]) {
                total -= arr[i];
            } else {
                ans = max;
                break;
            }
        }

        if (ans == 0) System.out.println(arr[N - 1]);
        else System.out.println(ans);
    }
}
