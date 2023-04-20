package bj2056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static ArrayList<Integer>[] adjList;
    static int[] inDegree;
    static int[] time;
    static class Work implements Comparable<Work>{
        int t, num;

        public Work(int t, int num) {
            super();
            this.t = t;
            this.num = num;
        }

        @Override
        public int compareTo(Work o) {
            return Integer.compare(this.t, o.t);
        }
    }
    static List<Work> start;
    static int result;

    public static void main(String[] args) throws NumberFormatException, IOException {
        input();
        bfs();
        System.out.println(result);
    }

    static void bfs() {
        PriorityQueue<Work> pq = new PriorityQueue<>();

        pq.addAll(start);

        Work current;
        while(!pq.isEmpty()) {
            current = pq.poll();

            result = current.t;

            for (int to : adjList[current.num]) {
                if (--inDegree[to] == 0) {
                    pq.add(new Work(current.t + time[to], to));
                }
            }
        }
    }

    static void input() throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        inDegree = new int[N+1];
        time = new int[N+1];

        StringTokenizer st;
        int t, cnt, from;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            cnt = Integer.parseInt(st.nextToken());

            time[i] = t;
            for (int j = 0; j < cnt; j++) {
                from = Integer.parseInt(st.nextToken());
                adjList[from].add(i);
                inDegree[i]++;
            }
        }

        start = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                start.add(new Work(time[i], i));
            }
        }

    }
}