package bj17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] home;
    static int count;

    public static void main(String[] args) throws IOException {
        //파이프의 가로, 세로, 대각선 방향을 구하고 각 상황별 탐색을 한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //집의 크기 N(3 ≤ N ≤ 16)
        N = Integer.parseInt(br.readLine());
        //집의 상태
        home = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                home[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        //이동
        move(1, 1, 1, 2);

        //출력
        System.out.println(count);
    }

    static void move(int r1, int c1, int r2, int c2) {
        //한쪽 끝이 N,N에 이동시 종료
        if (r2 == N && c2 == N) {
            //방법의 수 체크
            count++;
            return;
        }

        //방향 구하기 파이프
        int d = direction(r1, c1, r2, c2);

        //가로인 경우
        if (d == 1) {
            //가로로 이동하는 경우
            if (c2+1 <= N && home[r2][c2+1] == 0)
                move(r1, c1 + 1, r2, c2 + 1);
            //대각선으로 이동하는 경우
            if (r2+1 <= N && c2+1 <= N && home[r2+1][c2] == 0 && home[r2][c2+1] == 0 && home[r2+1][c2+1] == 0)
                move(r1, c1 + 1, r2 + 1, c2 + 1);
        }
        //세로인 경우
        if (d == 2) {
            //세로로 이동하는 경우
            if (r2 + 1 <= N && home[r2 + 1][c2] == 0) {
                move(r1 + 1, c1, r2 + 1, c2);
            }
            //대각선으로 이동하는 경우
            if (r2 + 1 <= N && c2 + 1 <= N && home[r2 + 1][c2] == 0 && home[r2][c2 + 1] == 0 && home[r2 + 1][c2 + 1] == 0) {
                move(r1 + 1, c1, r2 + 1, c2 + 1);
            }
        }
        //대각선인 경우
        if (d == 3) {
            //가로로 이동하는 경우
            if (c2 + 1 <= N && home[r2][c2 + 1] == 0) {
                move(r1 + 1, c1 + 1, r2, c2 + 1);
            }
            //세로로 이동하는 경우
            if (r2 + 1 <= N && home[r2 + 1][c2] == 0) {
                move(r1 + 1, c1 + 1, r2 + 1, c2);
            }
            //대각선으로 이동하는 경우
            if (r2 + 1 <= N && c2 + 1 <= N && home[r2 + 1][c2] == 0 && home[r2][c2 + 1] == 0 && home[r2 + 1][c2 + 1] == 0) {
                move(r1 + 1, c1 + 1, r2 + 1, c2 + 1);
            }
        }
    }

    //가로, 세로, 대각선 판별
    static int direction(int r1, int c1, int r2, int c2) {

        //가로 판별
        if (c2 > c1 && r2 == r1) return 1;
        //세로 판별
        else if (r2 > r1 && c1 == c2) return 2;
        //대각선 판별
        else return 3;
    }
}
