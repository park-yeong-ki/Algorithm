package bj13549;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static class Position implements Comparable<Position>{
        int x, t;

        public Position(int x, int t) {
            this.x = x;
            this.t = t;
        }

        @Override
        public int compareTo(Position o) {
            return Integer.compare(this.t, o.t);
        }
    }
    static int K;
    static int N;
    static int[] dx = {-1, 1, 2};
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        if(N > K) {
            System.out.println(N-K);
        }else {
            bfs(new Position(N, 0));
            System.out.println(result);
        }
    }

    static void bfs(Position start) {
        PriorityQueue<Position> pq = new PriorityQueue<>();

        boolean[] visited = new boolean[K+2];

        pq.offer(start);
        visited[start.x] = true;

        Position current;
        while(!pq.isEmpty()) {
            current = pq.poll();

            if (current.x == K) {
                result = current.t;
                return;
            }

            visited[current.x] = true;

            for (int i = 0; i < 3; i++) {
                int tX;
                int tT;
                if (i < 2) {
                    tX = current.x + dx[i];
                    tT = current.t + 1;
                }
                else {
                    tX = current.x * dx[i];
                    tT = current.t;
                }


                if (tX >= 0 && tX < K+2 && !visited[tX]) {
                    pq.offer(new Position(tX, tT));
                }
            }
        }

    }
}