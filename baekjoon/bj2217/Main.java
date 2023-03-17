package bj2217;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //로프 개수
        int N = sc.nextInt();

        //로프 배열
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        //로프의 내림차순 정렬
        Arrays.sort(arr, Comparator.reverseOrder());

        //arr[0]
        //arr[1] * 2
        //arr[2] * 3;
        //...

        //내림차순으로 정렬한 상태이므로 각 인덱스 값과 로프 개수의 곱을 비교하여 최대중량을 구할 수 있다.
        int num = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            num = arr[i] * (i+1);
            max = Math.max(max, num);
        }

        //출력
        System.out.println(max);
    }
}