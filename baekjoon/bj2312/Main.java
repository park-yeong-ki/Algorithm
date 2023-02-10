package bj2312;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //테스트 케이스 입력
        int T = sc.nextInt();

        //T만큼 반복
        for (int i = 0; i < T; i++) {
            //N입력
            int N = sc.nextInt();

            //약수를 담을 배열 생성
            int[] arr = new int[(int)Math.sqrt(N)];

            //제곱근까지의 약수 찾기
            int size = 0;
            for (int j = 1; j <= Math.sqrt(N); j++) {
                if (N % j == 0) {
                    arr[size++] = j;
                }
            }
            arr = Arrays.copyOf(arr, size*2);

            //나머지 약수 구하기
            for (int j = size-1; j >= 0; j--) {
                if (N/arr[j] != arr[j]) {
                    arr[size++] = N / arr[j];
                }
            }
            arr = Arrays.copyOf(arr, size);

            //소인수 배열 생성
            int[] prime = new int[size];
            int pSize = 0;

            //소인수 구하기
            for (int j = 0; j < arr.length; j++) {
                int count = 0;
                for (int j2 = 1; j2 <= Math.sqrt(arr[j]); j2++) {
                    if (arr[j] % j2 == 0) {
                        count++;
                    }
                }
                if (arr[j] != 1 && count < 2) {
                    prime[pSize++] = arr[j];
                }
            }
            prime = Arrays.copyOf(prime, pSize);

            //소인수가 곱해진 횟수를 구하기
            for (int j = 0; j < prime.length; j++) {
                int count = 0;
                while(N % prime[j] == 0) {
                    N /= prime[j];
                    count++;
                }
                //출력
                System.out.println(prime[j] + " " + count);
            }

        }
    }
}