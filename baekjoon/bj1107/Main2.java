package bj1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static int N, min, length;
    static boolean[] button;
    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0);
        System.out.println(min);
    }

    static void dfs(int cnt, int channel) {
        if (cnt > min) return;
        if (cnt != 0) min = Math.min(min, Math.abs(N - channel) + cnt);
        if (cnt == length + 1) return;

        for (int i = 0; i < 10; i++) {
            if (button[i]) continue;
            dfs(cnt + 1, channel * 10 + i);
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        button = new boolean[10];

        min = Math.abs(N - 100);
        length = String.valueOf(N).length();

        if (M == 0) return;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            button[Integer.parseInt(st.nextToken())] = true;
        }
    }
}
