package bj14002;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        LinkedList<Integer>[] dpList = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            dpList[i] = new LinkedList<>();
        }

        int ans = 0;
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            int max = -1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        max = j;
                    }
                }
            }

            if (max != -1) dpList[i].addAll(dpList[max]);
            dpList[i].add(arr[i]);

            if (dp[ans] < dp[i]) ans = i;
        }

        bw.write(dpList[ans].size() + "\n");
        for (int num : dpList[ans]) {
            bw.write(num + " ");
        }
        bw.newLine();
        bw.close();
    }
}
