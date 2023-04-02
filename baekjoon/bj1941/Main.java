package bj1941;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static char[][] arr;
    static int[] student;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new char[5][5];
        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 5; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        student = new int[7];
        comb(0, 0, 0);

        System.out.println(result);
    }

    static void comb(int start, int cnt, int s) {
        if (cnt - s >= 4) {
            return;
        }

        if (cnt == 7) {
            if (bfs(0) == 7) {
                result++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            student[cnt] = i;
            if (arr[i/5][i%5] == 'S') comb(i + 1, cnt + 1, s + 1);
            else comb(i + 1, cnt + 1, s);
        }
    }

    static int bfs(int start){
        int cnt = 0;
        Queue<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[7];

        queue.offer(start);
        visited[start] = true;

        int current;
        while (!queue.isEmpty()) {
            current = student[queue.poll()];
            int r = current / 5;
            int c = current % 5;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 7; j++) {
                    if (!visited[j] && r + dr[i] == student[j] / 5 && c + dc[i] == student[j] % 5) {
                        queue.offer(j);
                        visited[j] = true;
                    }
                }
            }
            cnt++;
        }

        return cnt;
    }
}
