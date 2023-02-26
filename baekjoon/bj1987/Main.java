package bj1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //R, C입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        //보드 입력
        board = new char[R+2][C+2];
        for (int i = 1; i <= R; i++) {
            String str = br.readLine();
            for (int j = 1; j <= C; j++) {
                board[i][j] = str.charAt(j - 1);
            }
        }

        //dfs사용
        dfs(1, 1, new boolean[26], 1);

        //출력
        System.out.println(max);
    }

    static void dfs(int x, int y, boolean[] visited, int cnt) {
        //방문 배열 표시
        visited[board[x][y] - 65] = true;

        //인접한 상하좌우에 이미 지나온 알파벳과 다른 경우 탐색
        for (int i = 0; i < 4; i++) {
            if (board[x + dx[i]][y + dy[i]] != '\u0000' && !visited[board[x + dx[i]][y + dy[i]] - 65]) {
                dfs(x + dx[i], y + dy[i], visited, cnt + 1);
            }
        }

        //가장 긴 깊이를 찾는 것이므로 방문 배열 해제
        visited[board[x][y] - 65] = false;

        //깊이의 최대값을 구한다.
        max = Math.max(max, cnt);
    }
}
