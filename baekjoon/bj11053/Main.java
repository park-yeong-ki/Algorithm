package bj11053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] A, length;
    static int N, max;
    public static void main(String[] args) throws IOException {
        input();
        dp();
        System.out.println(max);
    }

    static void dp() {
        length = new int[N];
        for (int i = 0; i < N; i++) {
            length[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) length[i] = Math.max(length[j] + 1, length[i]);
            }
            max = Math.max(max, length[i]);
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }
}