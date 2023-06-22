package bj9375;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new HashMap();
            StringTokenizer st;
            String name, sort;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                name = st.nextToken();
                sort = st.nextToken();

                if (map.containsKey(sort)) map.put(sort, map.get(sort) + 1);
                else map.put(sort, 1);
            }

            int ans = 1;
            for (int val : map.values()) {
                ans *= (val + 1);
            }
            sb.append(ans - 1 + "\n");
        }
        System.out.print(sb);
    }
}
