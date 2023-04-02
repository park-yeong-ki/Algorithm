package bj9205;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] home;
    static int[][] store;
    static int[][] festival;
    static String result = "sad";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            home = new int[][]{{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())}};

            store = new int[n][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                store[i][0] = Integer.parseInt(st.nextToken());
                store[i][1] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            festival = new int[][]{{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())}};

            dfs(home[0][0], home[0][1], new boolean[n], -1);

            bw.write(result + "\n");
            result = "sad";
        }
        bw.close();
    }

    static void dfs(int x, int y, boolean[] visited, int num) {
        if (Math.abs(festival[0][0] - x) + Math.abs(festival[0][1] - y) <= 1000) {
            result = "happy";
            return;
        }

        if (num != -1) {
            visited[num] = true;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && Math.abs(store[i][0] - x) + Math.abs(store[i][1] - y) <= 1000) {
                dfs(store[i][0], store[i][1], visited, i);
            }
        }
    }
}