package bj1005;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static int[] tArr;
    static ArrayList<Building>[] adjList;
    static int[] inDegree;
    static int W;
    static int result;
    static class Building implements Comparable<Building> {
        int to, time;
        public Building(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Building o) {
            return Integer.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            tArr = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                tArr[i] = Integer.parseInt(st.nextToken());
            }

            adjList = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }
            int from, to;
            inDegree = new int[N + 1];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                adjList[from].add(new Building(to, tArr[to]));
                inDegree[to]++;
            }

            W = Integer.parseInt(br.readLine());

            List<Building> start = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if (inDegree[i] == 0) {
                    start.add(new Building(i, tArr[i]));
                }
            }

            bfs(start);
            bw.write(result + "\n");
        }
        bw.close();
    }

    static void bfs(List<Building> start) {
        PriorityQueue<Building> pq = new PriorityQueue<>();

        pq.addAll(start);

        result = 0;
        int size;
        Building current;
        while (!pq.isEmpty()) {
            size = pq.size();

            for (int i = 0; i < size; i++) {
                current = pq.poll();

                if (current.to == W) {
                    result = current.time;
                    return;
                }

                for (Building b : adjList[current.to]) {
                    if (--inDegree[b.to] == 0) {
                        pq.add(new Building(b.to, current.time + b.time));
                    }
                }
            }
        }
    }
}