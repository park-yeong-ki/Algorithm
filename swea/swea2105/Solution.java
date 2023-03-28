package swea2105;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
    static int[] dr = { 1, 1, -1, -1 };
    static int[] dc = { 1, -1, -1, 1 };
    static int N;
    static int max = -1;
    static int[][] arr;
    static int startR;
    static int startC;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    startR = i;
                    startC = j;
                    dfs(i, j, 0, new boolean[101], 0, 0);
                }
            }

            bw.write("#" + test_case + " " + max + "\n");
            max = -1;
        }
        bw.close();

    }

    static void dfs(int r, int c, int d, boolean[] visited, int depth, int rot) {
        int right = (d + 1) % 4;

        if (rot > 4) {
            return;
        }

        if (depth != 0 && r + dr[d] == startR && c + dc[d] == startC) {
            max = Math.max(max, depth + 1);
            return;
        }

        if (depth != 0 && r + dr[right] == startR && c + dc[right] == startC) {
            rot++;
            if(rot == 4) {
                max = Math.max(max, depth + 1);
            }
            return;
        }

        visited[arr[r][c]] = true;
        if (r + dr[d] >= 0 && r + dr[d] < N && c + dc[d] >= 0 && c + dc[d] < N && !visited[arr[r + dr[d]][c + dc[d]]])
            dfs(r + dr[d], c + dc[d], d, visited, depth + 1, rot);

        if (r + dr[right] >= 0 && r + dr[right] < N && c + dc[right] >= 0 && c + dc[right] < N
                && !visited[arr[r + dr[right]][c + dc[right]]])
            dfs(r + dr[right], c + dc[right], right, visited, depth + 1, rot+1);
        visited[arr[r][c]] = false;
    }
}