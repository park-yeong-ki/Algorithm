package swea1208;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //테스트 케이스 10개
        for (int test_case = 1; test_case <= 10; test_case++) {
            //덤프 횟수 N입력
            int N = sc.nextInt();

            //상자의 높이를 저장할 배열 생성
            int[] arr = new int[100];

            //배열 요소 입력
            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextInt();
            }

            //N만큼 반복하기
            for (int i = 0; i <= N; i++) {
                //최대높이 상자찾기
                int max = 0;
                for (int j = 1; j < arr.length; j++) {
                    if (arr[max] < arr[j]) {
                        max = j;
                    }
                }

                //최소높이 상자찾기
                int min = 0;
                for (int j = 1; j < arr.length; j++) {
                    if (arr[min] > arr[j]) {
                        min = j;
                    }
                }

                if (i < N) {
                    //최대높이 상자에서 1빼기
                    arr[max]--;
                    //최소높이 상자에서 1더하기
                    arr[min]++;
                }else {
                    //결과 출력
                    System.out.println("#" + test_case + " " + (arr[max] - arr[min]));
                }
            }

        }
    }
}