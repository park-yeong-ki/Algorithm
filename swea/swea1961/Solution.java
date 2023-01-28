package swea1961;

import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception {
		//Scanner 생성
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        //1부터 T까지 반복
        for(int test_case = 1; test_case <= T; test_case++)
        {
            //N입력
            int N = sc.nextInt();

            //NxN 배열 생성
            int[][] arr = new int[N][N];

            //배열 요소 입력
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            //풀이
            //90도 180도 270도 별로 담을 수 있는 3차원 배열 생성
            int[][][] result = new int[3][N][N];
            for (int n = 0; n < 3; n++) {
                //회전된 배열 요소를 입력할 2차원 배열 생성
                int[][] nArr = new int[N][N];

                //새로 만든 배열에 배열 요소 입력
                for (int i = 0; i < nArr.length; i++) {
                    for (int j = 0; j < nArr.length; j++) {
                        nArr[j][N-1-i] = arr[i][j];
                    }
                }

                //순차적으로 회전된 배열 입력, arr에 회전된 배열 대입
                result[n] = arr = nArr;
            }

            //출력
            System.out.println("#" + test_case);
            for (int i = 0; i < N; i++) {
                //각 배열의 첫번째 인덱스의 값이 전부 나열되도록 반목문 구현
                for (int j = 0; j < 3; j++) {
                    for (int j2 = 0; j2 < N; j2++) {
                        System.out.print(result[j][i][j2]);
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }

        }
    }
}