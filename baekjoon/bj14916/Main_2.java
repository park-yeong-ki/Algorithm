package bj14916;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main_2 {
    static int n;
    static int[] money = {2, 5};
    static int cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        bfs(n);

    }

    static void bfs(int n) {
        Queue<Integer> queue = new ArrayDeque<Integer>();

        boolean[] visited = new boolean[n+1];

        queue.offer(n);
        visited[n] = true;

        int current, size;
        while(!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (current == 0) {
                    System.out.println(cnt);
                    return;
                }

                for (int j = 0; j < 2; j++) {
                    if (current - money[j] >=0 && !visited[current - money[j]]) {
                        queue.offer(current - money[j]);
                        visited[current - money[j]] = true;
                    }
                }
            }
            cnt++;
        }

        System.out.println(-1);
    }
}