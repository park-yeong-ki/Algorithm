package bj4803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parents;
    static void makeSet() {
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }

    static int findSet(int p) {
        if (p == parents[p]) return p;
        return parents[p] = findSet(parents[p]);
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
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test_case = 0;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;
            test_case++;

            makeSet();

            int from, to;
            List<Integer> notTree = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());

                if (!union(from, to)) {
                    int root = findSet(from);
                    notTree.add(root);
                }
            }

            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                set.add(findSet(i));
            }

            for (int num : notTree) {
                set.remove(findSet(num));
            }

            sb.append("Case " + test_case + ": ");
            if (set.isEmpty()) sb.append("No trees.");
            else if (set.size() == 1) sb.append("There is one tree.");
            else sb.append("A forest of " + set.size() + " trees.");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
