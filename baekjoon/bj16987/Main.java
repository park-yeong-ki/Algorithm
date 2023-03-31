package bj16987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr;
    static int max = Integer.MIN_VALUE;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        egg(0);
        System.out.println(max);
    }

    static void egg(int idx) {
        if (idx == N) {
            max = Math.max(max, cnt);
            return;
        }

        if (arr[idx][0] <= 0 || cnt == N-1) {
            egg(idx + 1);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (idx == i || arr[i][0] <= 0) continue;
            arr[i][0] -= arr[idx][1];
            arr[idx][0] -= arr[i][1];
            if (arr[i][0] <= 0) cnt++;
            if (arr[idx][0] <= 0) cnt++;
            egg(idx + 1);
            if (arr[i][0] <= 0) cnt--;
            if (arr[idx][0] <= 0) cnt--;
            arr[i][0] += arr[idx][1];
            arr[idx][0] += arr[i][1];
        }
    }
}
