package bj25305;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //N, K 입력
        int N = sc.nextInt();
        int K = sc.nextInt();

        //값을 입력받을 배열 생성(Arrays.sort()를 내림차순 정렬을 사용하려면 Interger로 선언해야함)
        Integer[] arr = new Integer[N];

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        //내림차순 정렬
        Arrays.sort(arr, Comparator.reverseOrder());

        //커트라인 출력
        System.out.println(arr[K-1]);
    }
}
