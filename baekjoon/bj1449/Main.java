package bj1449;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, L;
    static int[] position;
    static boolean[] isBlocked;
    static int cnt;
    public static void main(String[] args) throws IOException {
        input();
        Arrays.sort(position);
        block();
        System.out.println(cnt);
    }

    static void block() {
        isBlocked = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (isBlocked[i]) continue;
            isBlocked[i] = true;
            cnt++;
            for (int j = i + 1; j < N; j++) {
                if (position[j] < position[i] + L) {
                    isBlocked[j] = true;
                }
            }
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        position = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            position[i] = Integer.parseInt(st.nextToken());
        }
    }
}
