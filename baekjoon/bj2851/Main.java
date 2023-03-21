package bj2851;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //누적합을 구한다
        int[] num = new int[10];
        num[0] = sc.nextInt();
        for (int i = 1; i < 10; i++) {
            num[i] = num[i-1] + sc.nextInt();
        }

        //100과 누적합의 절대값 차이가 가장 작은 경우를 구한다
        int min = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < 10; i++) {
            int diff = Math.abs(num[i] - 100);
            if (min >= diff) {
                min = diff;
                result = num[i];
            }
        }

        //출력
        System.out.println(result);
    }
}
