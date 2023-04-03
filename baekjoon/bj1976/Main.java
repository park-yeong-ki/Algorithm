package bj1976;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
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

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parents = new int[N + 1];
        makeSet();

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int root = findSet(Integer.parseInt(st.nextToken()));
        boolean flag = true;
        for (int i = 0; i < M-1; i++) {
            if (root != findSet(Integer.parseInt(st.nextToken()))) {
                flag = false;
                break;
            }
        }

        if (flag) bw.write("YES");
        else bw.write("NO");

        bw.close();
    }
}
