package bj12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //물건의 개수와 배낭의 최대 무게를 입력받는다.
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //N개 물건의 무게와 가치를 입력받는다.
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        //파라미터가 2개이므로 2차원 dp테이블 생성
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                int tW = arr[i - 1][0];
                int tV = arr[i - 1][1];

                //물건의 무게가 임시 배낭의 용량보다 큰 경우
                if (tW > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                //물건의 무게가 임시 배낭의 용량보다 작은 경우
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - tW] + tV);
                }
            }
        }

        //누적된 최대 가치합은 마지막 배열의 요소에 저장됨
        System.out.println(dp[N][K]);
    }
}