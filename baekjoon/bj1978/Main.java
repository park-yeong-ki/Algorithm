package bj1978;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //스캐너 생성
        Scanner sc = new Scanner(System.in);

        //N입력
        int N = sc.nextInt();
        //N 크기의 배열 생성
        int[] arr = new int[N];

        //배열 요소 입력
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        //카운트 배열 생성
        int[] count = new int[N];

        //소수 찾기
        for (int i = 0; i < arr.length; i++) {
            //제곱근까지만 반복해도 약수의 개수를 알 수 있다(1은 소수가 아니므로 제거하는 조건 추가)
            for (int j = 1; j <= Math.sqrt(arr[i]); j++) {
                if (arr[i] % j == 0 && arr[i] != 1) {
                    count[i]++;
                }
            }
        }

        //소수인 것을 발견하면 결과값이 1씩 증가
        int result = 0;
        for (int i = 0; i < N; i++) {
            if (count[i] == 1) {
                result++;
            }
        }

        //출력
        System.out.println(result);
    }
}
