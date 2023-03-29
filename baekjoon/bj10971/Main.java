package bj10971;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] abjArray;
    static int N;
    static int start;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        abjArray = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                abjArray[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            start = i;
            dfs(i, new boolean[N], 0, 1);
        }

        System.out.println(min);
    }

    static void dfs(int city, boolean[] visited, int cost, int depth) {
        if (cost >= min) {
            return;
        }

        if (depth == N) {
            if (abjArray[city][start] != 0) {
                cost += abjArray[city][start];
                min = Math.min(min, cost);
            }
            return;
        }

        visited[city] = true;

        for (int i = 0; i < abjArray.length; i++) {
            if (!visited[i] && abjArray[city][i] != 0) {
                dfs(i, visited, cost + abjArray[city][i], depth + 1);
            }
        }

        visited[city] = false;
    }
}
