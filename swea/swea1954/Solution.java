package swea1954;

import java.util.Scanner;


class Solution
{
    public static void main(String args[]) throws Exception {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        //1부터 T까지 반복
        for(int test_case = 1; test_case <= T; test_case++) {

            //입력
            int N = sc.nextInt();
            //NxN 행렬 생성
            int[][] arr = new int[N][N];

            //풀이
            //배열 안에 들어갈 변수 선언
            int a = 1;
            //열과 행을 줄여나갈 변수 선언
            int m = 0;

            //N이 m보다 클 경우에만 반복문 실행
            while (m < N ) {
                //윗 행 요소 입력
                for (int i = 0 + m; i < N - m; i++) {
                    arr[m][i] = a++;
                }

                //우측 열 요소 입력
                for (int i = 1 + m; i < N - 1 - m; i++) {
                    arr[i][N - 1 - m] = a++;
                }

                //아랫 행 요소 입력
                for (int i = N - 1 - m; i >= 0 + m; i--) {
                    arr[N - 1 - m][i] = a++;
                }

                //좌측 열 요소 입력
                for (int i = N - 2 - m; i >= 1 + m; i--) {
                    arr[i][m] = a++;
                }
                //변수 m 1씩 증가
                m++;
            }

            //N이 홀수일 경우 a값이 중복 증가한 경우을 뺴줌
            if (N % 2 == 1) {
                arr[N/2][N/2] = a-2;
            }

            //출력
            System.out.println("#" + test_case);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(arr[i][j]);
                    //배열의 마지막 열이 아닌 경우는 공백, 마지막 열인 경우는 줄바꿈 처리
                    if (j != N-1) System.out.print(" ");
                    else System.out.println();
                }
            }
        }
    }
}