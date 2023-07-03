package bj1956;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] adjArray;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int INF = 10000 * V + 1;

        adjArray = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i != j) adjArray[i][j] = INF;
            }
        }

        int from, to, weight;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            adjArray[from][to] = Math.min(adjArray[from][to], weight);
        }

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) continue;
                for (int k = 1; k <= V; k++) {
                    if (i == k || j == k) continue;
                    adjArray[j][k] = Math.min(adjArray[j][k], adjArray[j][i] + adjArray[i][k]);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) continue;
                if (adjArray[i][j] == INF || adjArray[j][i] == INF) continue;
                ans = Math.min(ans, adjArray[i][j] + adjArray[j][i]);
            }
        }

        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }
}
