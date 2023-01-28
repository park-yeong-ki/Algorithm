package bj2587;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //5개의 수를 받을 배열 생성
        int[] arr = new int[5];

        //배열 요소 입력
        for (int i = 0; i < 5; i++) {
            arr[i] = sc.nextInt();
        }

        //정렬
        Arrays.sort(arr);

        //전체 배열 요소의 합을 구하기
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        //평균값 출력
        System.out.println(sum/ arr.length);
        //중간값 출력
        System.out.println(arr[arr.length/2]);
    }
}
