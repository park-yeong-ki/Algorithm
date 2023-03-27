package swea1247;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_3 {
    static boolean[] isSelected;
    static int N;
    static int[][] customer;
    static int[][] office;
    static int[][] home;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테케 횟수
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            //입력
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

            //dfs사용
            isSelected = new boolean[N];
            dfs(0, office[0][0], office[0][1], 0);

            //출력
            bw.write("#" + test_case + " " + min + "\n");
            //전역변수 초기화
            min = Integer.MAX_VALUE;
        }
        bw.close();
    }

    static void dfs(int depth, int x, int y, int distance) {
        //가지치기
        if (distance >= min) {
            return;
        }

        //고객 좌표가 끝나면 집거리 구하기
        if (depth == N) {
            distance += Math.abs(home[0][0] - x) + Math.abs(home[0][1] - y);
            min = Math.min(min, distance);
            return;
        }

        //다음 고객 좌표를 선택할 수 있는 방법
        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;
            isSelected[i] = true;
            dfs(depth + 1, customer[i][0], customer[i][1], distance + Math.abs(customer[i][0] - x) + Math.abs(customer[i][1] - y));
            isSelected[i] = false;
        }
    }
}
