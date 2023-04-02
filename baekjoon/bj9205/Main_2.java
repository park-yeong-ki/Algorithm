package bj9205;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2 {
    static Point home;
    static Point[] store;
    static Point festival;
    static int n;
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < t; test_case++) {
            n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            store = new Point[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                store[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine());
            festival = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            bfs(home);

            bw.write(result + "\n");
        }
        bw.close();
    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[n];

        queue.offer(start);

        Point current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            if (Math.abs(current.r - festival.r) + Math.abs(current.c - festival.c) <= 1000) {
                result = "happy";
                return;
            }

            for (int i = 0; i < n; i++) {
                if (!visited[i] && Math.abs(current.r - store[i].r) + Math.abs(current.c - store[i].c) <= 1000) {
                    queue.offer(store[i]);
                    visited[i] = true;
                }
            }
        }

        result = "sad";
    }
}
