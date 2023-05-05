package bj7795;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] nArr, mArr;
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            input();
            count();
        }
        System.out.println(sb);
    }

    static void count() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (nArr[i] > mArr[j]) {
                    cnt++;
                }else {
                    break;
                }
            }
        }
        sb.append(cnt+"\n");
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nArr = new int[N];
        mArr = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nArr);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            mArr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(mArr);
    }
}