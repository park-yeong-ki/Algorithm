package bj11404;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m, INF;
    static int[][] adjArray;
    public static void main(String[] args) throws IOException {
        input();
        floyd();
        output();
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                bw.write(adjArray[i][j] + " ");
            }
            bw.newLine();
        }
        bw.close();
    }

    static void floyd() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    adjArray[j][k] = Math.min(adjArray[j][k], adjArray[j][i] + adjArray[i][k]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (adjArray[i][j] == INF) adjArray[i][j] = 0;
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        INF = 100000 * n + 1;

        adjArray = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) adjArray[i][j] = INF;
            }
        }

        StringTokenizer st;
        int from, to, weight;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            adjArray[from][to] = Math.min(adjArray[from][to], weight);
        }
    }
}
