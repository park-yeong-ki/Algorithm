package bj1003;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 케이스
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            //0과 1인 경우의 값을 설정한다.
            int[][] fibonacci = new int[N+2][2];
            fibonacci[0][0] = 1;
            fibonacci[0][1] = 0;
            fibonacci[1][0] = 0;
            fibonacci[1][1] = 1;

            //누적합을 이용하여 0과 1의 개수를 구한다.
            for (int i = 2; i <= N; i++) {
                fibonacci[i][0] = fibonacci[i - 1][0] + fibonacci[i - 2][0];
                fibonacci[i][1] = fibonacci[i - 1][1] + fibonacci[i - 2][1];
            }

            //출력
            bw.write(fibonacci[N][0] + " " + fibonacci[N][1]);
            bw.newLine();
        }
        bw.close();
    }
}
