package bj1018;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //Scanner 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //N과 M입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //NxM 배열 생성
        boolean[][] board = new boolean[N][M];

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'W') board[i][j] = true;
                else board[i][j] = false;
            }
        }

        //최소값을 저장할 변수 생성
        int min = Integer.MAX_VALUE;
        //보드판에서 8x8씩 이동시켜줄 반복문 생성
        for (int row = 0; row < N - 7; row++) {
            for (int col = 0; col < M - 7; col++) {
                //첫번째 값이 B, W인 경우 모두 고려하기 위해 2번 반복
                for (int t = 0; t < 2; t++) {
                    //배열복사
                    boolean[][] temp = new boolean[N][M];
                    for (int i = 0; i < N; i++) {
                        temp[i] = board[i].clone();
                    }

                    int count = 0;

                    //첫번째 값을 반전한 경우 고려
                    if (t == 1){
                        board[row][col] = !board[row][col];
                        count++;
                    }

                    //8x8 체스판에 접근할 반복문
                    for (int i = row; i < row + 8; i++) {
                        //현재 행의 첫번째 값과 다음 행의 첫번째 값이 같다면 반전하고 카운트를 1증가
                        if (i != row + 7 && board[i][col] == board[i + 1][col]) {
                            board[i + 1][col] = !board[i + 1][col];
                            count++;
                        }
                        for (int j = col; j < col + 8; j++) {
                            //다음 열의 값이 현재 값과 같을 경우 다음 열의 값을 반전하고 카운트를 1증가
                            if (j != col + 7 && board[i][j] == board[i][j + 1]) {
                                board[i][j + 1] = !board[i][j + 1];
                                count++;
                            }
                        }
                    }
                    //배열 복원
                    board = temp;

                    //최소값 구하기
                    min = Math.min(min, count);
                }
            }
        }

        //출력
        bw.write(min + "\n");
        bw.close();
    }
}