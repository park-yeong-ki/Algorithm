package swea1984;

import java.util.Scanner;
import java.util.Arrays;


class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++) {


            //배열 선언 및 요소 입력
            int[] arr = new int[10];

            for (int i = 0; i < 10; i++) {
                arr[i] = sc.nextInt();
            }

            //오름차순 정렬
            Arrays.sort(arr);

            int sum = 0;
            for (int i = 1; i < 9; i++) {
                sum += arr[i];
            }

            int result = (int) Math.round(sum/8.0);

            //출력
            System.out.println("#" + test_case + " " + result);

        }
    }
}