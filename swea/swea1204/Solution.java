package swea1204;

import java.util.Scanner;

class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++) {

            //입력받을 배열 생성
            int num = sc.nextInt();
            int[] scores = new int[1000];

            //배열 요소 입력
            for (int i = 0; i < 1000; i++) {
                scores[i] = sc.nextInt();
            }

            //카운트 배열 생성
            int[] count = new int[101];

            //카운트 배열 요소 입력
            for (int i = 0; i < 1000; i++) {
                count[scores[i]]++;
            }

            //최빈수와 카운트배열을 비교하며 업데이트하여 점수를 구함
            int frequent = 0;
            int score = 0;
            for (int i = 0; i < 101; i++) {
                if (frequent < count[i]) {
                    frequent = count[i];
                    score = i;
                } else if (frequent == count[i]) {
                    if (score < i) {
                        score = i;
                    }
                }
            }

            //출력
            System.out.println("#" + test_case + " " + score);
        }
    }
}