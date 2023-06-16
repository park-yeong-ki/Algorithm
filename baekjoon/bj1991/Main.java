package bj1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] adjList;
    static int N;
    static StringBuilder pre, in, post;
    public static void main(String[] args) throws IOException {
        input();
        dfs(0);
        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);
    }

    private static void dfs(int num) {
        pre.append((char) (num + 'A'));
        boolean flag = false;
        for (int child: adjList[num]) {
            if (child != -19) dfs(child);
            if (flag) continue;
            in.append((char) (num + 'A'));
            flag = true;
        }
        post.append((char) (num + 'A'));
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        char from, toR, toL;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            from = st.nextToken().charAt(0);
            toR = st.nextToken().charAt(0);
            toL = st.nextToken().charAt(0);

            adjList[from - 'A'].add(toR - 'A');
            adjList[from - 'A'].add(toL - 'A');
        }

        pre = new StringBuilder();
        in = new StringBuilder();
        post = new StringBuilder();
    }
}
