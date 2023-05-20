package bj11501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 0; test_case < T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] stock = new int[N];
            for (int i = 0; i < N; i++) {
                stock[i] = Integer.parseInt(st.nextToken());
            }

            int max = stock[N - 1];
            long profit = 0;
            for (int i = N-2; i >= 0; i--) {
                if (stock[i] < max) profit += max-stock[i];
                else max = stock[i];
            }
            sb.append(profit+"\n");
        }
        System.out.print(sb);
    }
}
