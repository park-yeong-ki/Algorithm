package swea2001;

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

            // 변수 입력
            int N = sc.nextInt();
            int M = sc.nextInt();

            // 입력받은 길이로 배열 선언
            int[][] arr = new int[N][N];

            // 배열 요소 입력
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            //유사한 1차원 배열 문제 아이디어를 응용
            int max = 0;
            for (int i = 0; i < N-M+1; i++) {
                for (int j = 0; j < N-M+1; j++) {
                    int sum = 0;
                    for (int k = i; k < M + i; k++) {
                        for (int l = j; l < M + j; l++) {
                            sum += arr[k][l];
                        }
                    }
                    //MxM 크기 만큼의 숫자를 더한 값과 최대값 비교
                    max = Math.max(max, sum);
                }
            }

            //출력
            System.out.println("#" + test_case + " " + max);
        }
    }
}