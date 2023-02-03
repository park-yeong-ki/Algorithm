package swea1895;

import java.util.Scanner;

class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++) {

            //입력
            int N = sc.nextInt();
            int[] arr = new int[N];

            //배열 요소 입력
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            //시간초과
//            long profit = 0;
//            for (int i = 0; i < N; i++) {
//                long max = 0;
//                for (int j = i+1; j < N; j++) {
//                    if (arr[i] < arr[j]) {
//                        max = Math.max(max, arr[j] - arr[i]);
//                    }
//                }
//                profit += max;
//            }

            //최대값을 뒤에서부터 세면서 차익 계산
            long profit = 0;
            int max = arr[N-1];
            for (int i = N-2; i >= 0; i--) {
                if (arr[i] < max) {
                    profit += max - arr[i];
                } else {
                    max = arr[i];
                }
            }

            //출력
            System.out.println("#" + test_case + " " + profit);
        }
    }
}