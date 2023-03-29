package bj14620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr;
    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { 1, 0, -1, 0 };
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];
        setFlower(0, 0);
        System.out.println(min);
    }

    static void setFlower(int cnt, int sum) {
        if (cnt == 3) {
            min = Math.min(min, sum);
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            outer:
            for (int j = 1; j < N - 1; j++) {
                if (visited[i][j]) continue;
                for (int j2 = 0; j2 < 4; j2++) {
                    if(visited[i + dr[j2]][j + dc[j2]]) continue outer;
                }

                visited[i][j] = true;
                sum += arr[i][j];
                for (int j2 = 0; j2 < 4; j2++) {
                    visited[i + dr[j2]][j + dc[j2]] = true;
                    sum += arr[i + dr[j2]][j + dc[j2]];
                }
                setFlower(cnt + 1, sum);
                visited[i][j] = false;
                sum -= arr[i][j];
                for (int j2 = 0; j2 < 4; j2++) {
                    visited[i + dr[j2]][j + dc[j2]] = false;
                    sum -= arr[i + dr[j2]][j + dc[j2]];
                }
            }
        }
    }
}