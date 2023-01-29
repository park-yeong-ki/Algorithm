package bj1427;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //숫자 입력
        String N = sc.next();

        //각 자리 수로 나누어 배열로 변경
        String[] arr = N.split("");

        //내림차순 정렬
        Arrays.sort(arr, Comparator.reverseOrder());

        //출력
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}
