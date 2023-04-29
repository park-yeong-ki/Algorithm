package bj14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] arr;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0);
        System.out.println(max);
    }

    static void dfs(int idx, int profit) {
        if (idx >= N) {
            max = Math.max(max, profit);
            return;
        }

        if (idx + arr[idx][0] <= N) dfs(idx + arr[idx][0], profit + arr[idx][1]);
        dfs(idx+1, profit);
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}