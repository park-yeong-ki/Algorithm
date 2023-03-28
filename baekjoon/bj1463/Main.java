package bj1463;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int X;
    static int cnt;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        X = sc.nextInt();

        bfs(X);

        System.out.println(cnt);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[X+1];

        queue.offer(start);
        visited[start] = true;

        int current, size;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (current == 1) {
                    return;
                }

                for (int j = 1; j <= 3; j++) {
                    int n = operator(j, current);
                    if (!visited[n]) {
                        queue.offer(n);
                        visited[n] = true;
                    }
                }
            }

            cnt++;
        }
    }

    static int operator(int num, int value) {
        switch (num) {
            case 1:
                if(value % 3 == 0) {
                    value /= 3;
                }
                break;
            case 2:
                if(value % 2 == 0) {
                    value /= 2;
                }

                break;
            case 3:
                value -= 1;
                break;
            default:
                break;
        }

        return value;
    }
}
