package bj9372;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] parents;

    static void makeSet() {
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    static int findSet(int num) {
        if (num == parents[num]) {
            return num;
        }

        return parents[num] = findSet(parents[num]);
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] mArr = new int[M][2];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                mArr[i][0] = Integer.parseInt(st.nextToken());
                mArr[i][1] = Integer.parseInt(st.nextToken());
            }

            parents = new int[N+1];
            makeSet();

            int cnt = 0;
            for (int i = 0; i < M; i++) {
                if (union(mArr[i][0], mArr[i][1])) {

                    if (++cnt == N-1) {
                        break;
                    }
                }
            }

            bw.write(cnt + "\n");
        }

        bw.close();
    }

}