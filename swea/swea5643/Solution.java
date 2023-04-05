package swea5643;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static ArrayList<Integer>[] adjList;
    static int[] up;
    static int[] down;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            adjList = new ArrayList[N+1];

            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }

            StringTokenizer st;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adjList[from].add(to);
            }

            up = new int[N+1];
            down = new int[N+1];

            for (int i = 1; i <= N; i++) {
                dfs(i, i, new boolean[N+1]);
            }

            int result = 0;
            for (int i = 1; i <= N; i++) {
                if (up[i] + down[i] == N-1) {
                    result++;
                }
            }

            bw.write("#" + test_case + " " + result + "\n");
        }
        bw.close();
    }

    static void dfs(int start, int student, boolean[] visited) {
        visited[student] = true;

        if (start != student) {
            up[start]++;
            down[student]++;
        }

        for (int upStudent : adjList[student]) {
            if(!visited[upStudent]) {
                dfs(start, upStudent, visited);
            }
        }

    }
}