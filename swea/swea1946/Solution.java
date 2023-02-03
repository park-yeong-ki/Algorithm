package swea1946;

import java.util.Scanner;

class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++) {


            int N = sc.nextInt();
            String[][] arr = new String[N][2];

            //배열 요소 입력
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 2; j++) {
                    arr[i][j] = sc.next();
                }
            }

            //출력
            System.out.println("#" + test_case);

            //풀이
            int length = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < Integer.parseInt(arr[i][1]); j++) {
                    if (length++ != 9) {
                        System.out.print(arr[i][0]);
                    }
                    else {
                        System.out.println(arr[i][0]);
                        length = 0;
                    }
                }
            }
            System.out.println();
        }
    }
}