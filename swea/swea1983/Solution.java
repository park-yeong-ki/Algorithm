package swea1983;

import java.util.Scanner;


class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++) {


            //N, K 입력
            int N = sc.nextInt();
            int K = sc.nextInt();

            //배열 생성
            int[][] arr = new int[N][3];

            //배열 요소 입력
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 3; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            //풀이
            //총점 배열 생성
            double[] scores = new double[N];
            for (int i = 0; i < N; i++) {
                double score = 0;
                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        score += arr[i][j] * 0.35;
                    } else if (j == 1) {
                        score += arr[i][j] * 0.45;
                    } else {
                        score += arr[i][j] * 0.2;
                    }
                }
                scores[i] = score;
            }

            //K번째 학생의 등수
            int count = 1;
            for (int i = 0; i < scores.length; i++) {
                if (scores[K-1] < scores[i]) {
                    count++;
                }
            }

            String result = "";

            String[] gpa = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};

            //K번째 학생의 학점
            for (int i = 1; i <= gpa.length; i++) {
                if (count <= i*N/10 && count > (i-1)*N/10) {
                    result = gpa[i-1];
                }
            }

            //출력
            System.out.print("#" + test_case + " " + result);
            System.out.println();
        }
    }
}