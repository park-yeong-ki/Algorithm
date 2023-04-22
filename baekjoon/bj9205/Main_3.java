package bj9205;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main_3 {
    static class Point{
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int n;
    static Point end;
    static Point[] store;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 0; test_case < T; test_case++) {
            n = sc.nextInt();

            Point start = new Point(sc.nextInt(), sc.nextInt());
            store = new Point[n];
            for (int i = 0; i < n; i++) {
                store[i] = new Point(sc.nextInt(), sc.nextInt());
            }
            end = new Point(sc.nextInt(), sc.nextInt());

            bfs(start);
        }
        System.out.println(sb);
    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[n];

        queue.add(start);

        Point current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            if (Math.abs(current.r - end.r) + Math.abs(current.c - end.c) <= 1000) {
                sb.append("happy\n");
                return;
            }

            for (int i = 0; i < n; i++) {
                if (!visited[i] && Math.abs(current.r - store[i].r) + Math.abs(current.c - store[i].c) <= 1000) {
                    queue.add(store[i]);
                    visited[i] = true;
                }
            }
        }
        sb.append("sad\n");
    }
}
