package bj18111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //세로 N, 가로 M, 인벤토리에는 B개의 블록
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        //집터 만들기
        //최고 높이, 최저 높이 구한다
        int minH = Integer.MAX_VALUE, maxH = Integer.MIN_VALUE;
        int[][] ground = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
                minH = Math.min(minH, ground[i][j]);
                maxH = Math.max(maxH, ground[i][j]);
            }
        }


        int rTime = Integer.MAX_VALUE;
        int rHeight = 0;
        //최저 높이, 최고 높이의 범위까지 반복
        for (int h = minH; h <= maxH; h++) {
            int tempB = B;
            int time = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    //땅이 낮을 경우
                    if (ground[i][j] < h) {
                        //인벤토리에서 블록 꺼내어 좌표 (i, j)의 가장 위에 있는 블록 위에 놓는다.
                        tempB -= h - ground[i][j];
                        time += h - ground[i][j];
                    }
                    //땅이 높은 경우
                    else if (ground[i][j] > h) {
                        //좌표 (i, j)의 가장 위에 있는 블록을 제거하여 인벤토리에 넣는다.
                        tempB += ground[i][j] - h;
                        time += 2*(ground[i][j] - h);
                    }
                }
            }

            //남은 벽돌이 0보다 큰 경우만 성공
            if (tempB >= 0){
                //작업에 걸리는 최소 시간과 그 경우 땅의 높이를 구한다.
                if (rTime > time) {
                    rTime = time;
                    rHeight = h;
                }
                //답이 여러 개 있다면 그중에서 땅의 높이가 가장 높은 것을 구한다.
                else if (rTime == time) {
                    rHeight = Math.max(rHeight, h);
                }
            }
        }

        //결과 출력
        System.out.println(rTime + " " + rHeight);
    }
}
