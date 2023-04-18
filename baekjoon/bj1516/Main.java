package bj1516;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static ArrayList<Integer>[] adjList;
    static int[] time;
    static int[] inDegree;
    static List<Building> start;
    static class Building implements Comparable<Building>{
        int num, t;

        public Building(int num, int t) {
            this.num = num;
            this.t = t;
        }

        @Override
        public int compareTo(Building o) {
            return Integer.compare(this.t, o.t);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        bfs();
        for (int i = 1; i <= N; i++) {
            bw.write(time[i] +"\n");
        }
        bw.close();
    }

    static void bfs() {
        PriorityQueue<Building> pq = new PriorityQueue<>();

        pq.addAll(start);

        Building current;
        while (!pq.isEmpty()) {
            current = pq.poll();

            for (int to: adjList[current.num]) {
                if (--inDegree[to] == 0) {
                    time[to] += current.t;
                    pq.add(new Building(to, time[to]));
                }
            }
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        time = new int[N + 1];
        inDegree = new int[N + 1];

        StringTokenizer st;
        int t, from;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            time[i] = t;
            while (true) {
                from = Integer.parseInt(st.nextToken());
                if (from == -1) break;
                adjList[from].add(i);
                inDegree[i]++;
            }
        }

        start = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                start.add(new Building(i, time[i]));
            }
        }
    }
}
