package bj12851;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int[] dx = { -1, 1, 2 };
    static int N;
    static int K;
    static int time;
    static int cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        if (N >= K) {
            System.out.println(N - K);
            System.out.println(1);
        } else {
            bfs(N);
            System.out.println(time);
            System.out.println(cnt);
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();

        int[] visited = new int[K + 2];
        Arrays.fill(visited, Integer.MAX_VALUE);

        queue.offer(start);
        visited[start] = time;

        int current;
        int size;
        boolean flag = false;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (current == K) {
                    cnt++;
                    flag = true;
                }

                for (int j = 0; j < 3; j++) {
                    int tX;
                    if (j < 2) tX = current + dx[j];
                    else tX = current * dx[j];

                    if (tX <= K + 1 && tX >= 0 && visited[tX] >= time+1) {
                        queue.offer(tX);
                        visited[tX] = time+1;
                    }
                }
            }

            if (flag) return;

            time++;
        }
    }
}