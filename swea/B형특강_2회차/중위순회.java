package B형특강_2회차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 중위순회 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static ArrayList<Integer>[] adjList;
    static char[] alphabet;
    static StringBuilder sb = new StringBuilder();;
    public static void main(String[] args) throws IOException {

        for (int test_case = 1; test_case <= 10; test_case++) {
            input();
            sb.append("#").append(test_case).append(" ");
            inOrder(1);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void inOrder(int num) {
        boolean flag = true;
        for (int node : adjList[num]) {
            inOrder(node);
            if (flag) {
                sb.append(alphabet[num]);
                flag = false;
            }
        }

        if (adjList[num].isEmpty()) sb.append(alphabet[num]);
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        alphabet = new char[N + 1];

        int from, to;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            alphabet[from] = st.nextToken().charAt(0);

            while (st.hasMoreTokens()) {
                to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
            }
        }
    }
}
