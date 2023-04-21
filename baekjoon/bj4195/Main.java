package bj4195;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int n;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            StringTokenizer st;
            Map<String, Integer> friend = new HashMap<>();
            makeSet();
            String f1, f2;
            int num = 0;
            count = new int[2 * n];
            Arrays.fill(count, 1);
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                f1 = st.nextToken();
                f2 = st.nextToken();

                if (!friend.containsKey(f1)) {
                    friend.put(f1, num++);
                }
                if (!friend.containsKey(f2)) {
                    friend.put(f2, num++);
                }

                union(friend.get(f1), friend.get(f2));

                int p = findSet(friend.get(f1));

                sb.append(count[p] + "\n");
            }
        }
        System.out.println(sb);
    }

    static int[] parents;
    static int[] count;

    static void makeSet() {
        parents = new int[n * 2];
        for (int i = 0; i < n * 2; i++) {
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

        parents[bRoot] = aRoot;

        count[aRoot] += count[bRoot];
        return true;
    }
}