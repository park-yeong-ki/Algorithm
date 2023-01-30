package bj2609;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 선언
        Scanner sc = new Scanner(System.in);

        //수 입력
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int min = Math.min(num1, num2);
        int max = Math.max(num1, num2);
        int[] arr = new int[min];

        //입력받은 두 수 가운데 작은 수의 제곱근까지의 약수 구하기
        int size = 0;
        for (int i = 1; i <= Math.sqrt(min); i++) {
            if (min % i == 0) {
                arr[size++] = i;
            }
        }
        arr = Arrays.copyOf(arr, 2 * size);

        //제곱근까지의 약수를 이용하여 나머지 약수 구하기
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            if (min / arr[i] != arr[i]) {
                arr[size++] = min / arr[i];
            }
        }
        arr = Arrays.copyOf(arr, size);

        //입력받은 두 수 가운데 큰 수를 기존에 구한 오름차순대로 정렬된 약수 배열을 이용해 최대 공약수를 구함
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max % arr[i] == 0) {
                result = arr[i];
            }
        }

        //출력
        //최대 공약수
        System.out.println(result);
        //최소 공배수 = 두 수의 곱 / 최대 공약수
        System.out.println(num1 * num2 / result);
    }
}
