package bj20040;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        makeSet();
        int result = 0;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (!union(a, b)) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
    static int[] parents;
    static void makeSet() {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }
    static int findSet(int p) {
        if (p == parents[p]) {
            return p;
        }
        return parents[p] = findSet(parents[p]);
    }
    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) {
            return false;
        }

        if (aRoot < bRoot) {
            parents[bRoot] = aRoot;
        } else {
            parents[aRoot] = bRoot;
        }

        return true;
    }
}
