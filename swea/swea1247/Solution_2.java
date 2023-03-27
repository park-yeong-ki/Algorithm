package swea1247;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2 {
    static int N;
    static int[][] office;
    static int[][] customer;
    static int[][] home;
    static int[][] result;
    static boolean[] isSelected;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            office = new int[1][2];
            office[0][0] = Integer.parseInt(st.nextToken());
            office[0][1] = Integer.parseInt(st.nextToken());

            home = new int[1][2];
            home[0][0] = Integer.parseInt(st.nextToken());
            home[0][1] = Integer.parseInt(st.nextToken());

            customer = new int[N][2];
            for (int i = 0; i < N; i++) {
                customer[i][0] = Integer.parseInt(st.nextToken());
                customer[i][1] = Integer.parseInt(st.nextToken());
            }

            result = new int[N][2];
            isSelected = new boolean[N];
            perm(0);

            bw.write("#" + test_case + " " + min + "\n");
            min = Integer.MAX_VALUE;
        }
        bw.close();
    }

    static void perm(int idx) {
        int d = distance(idx);
        if (d >= min) {
            return;
        }

        if (idx == N) {
            min = Math.min(min, d);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;
            result[idx][0] = customer[i][0];
            result[idx][1] = customer[i][1];
            isSelected[i] = true;
            perm(idx + 1);
            isSelected[i] = false;
        }
    }

    static int distance(int idx) {
        int distance = Math.abs(office[0][0] - result[0][0]) + Math.abs(office[0][1] - result[0][1]);
        for (int i = 0; i < idx-1; i++) {
            distance += Math.abs(result[i][0] - result[i + 1][0]) + Math.abs(result[i][1] - result[i + 1][1]);
        }
        if (idx == N){
            distance += Math.abs(home[0][0] - result[N - 1][0]) + Math.abs(home[0][1] - result[N - 1][1]);
        }

        return distance;
    }
}
