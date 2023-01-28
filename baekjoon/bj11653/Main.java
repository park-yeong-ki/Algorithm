package bj11653;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //N입력
        int N = sc.nextInt();

        //약수 구하기(제곱근까지 반복)
        int[] arr = new int[N+1];
        int size = 0;
        for (int i = 1; i <= Math.sqrt(N); i++) {
            if (N % i == 0) {
                arr[size++] = i;
            }
        }

        //약수 구하기(제곱근까지 반복문을 돌려 나온 약수로 나눠 추가적인 약수 구하기)
        int a = size;
        for (int i = a-1; i >= 0; i--) {
            //중복 제거
            if (N/arr[i] == arr[i]) {
                continue;
            }
            arr[size++] = N/arr[i];
        }

        arr = Arrays.copyOfRange(arr, 1 , size);

        //약수중에 소수 찾기위해 count 배열 생성
        int[] count = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j <= Math.sqrt(arr[i]); j++) {
                if (arr[i] % j == 0) {
                    count[i]++;
                }
            }
        }

        //count[i]가 1인 경우는 소인수이며 소인수로 N을 나누며 소인수를 출력한다.
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 1) {
                while (N % arr[i] == 0) {
                    N /= arr[i];
                    System.out.println(arr[i]);
                }
            }
        }

    }
}
