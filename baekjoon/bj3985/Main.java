package bj3985;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 롤 케이크의 길이 L
        int L = sc.nextInt();

        // 방청객의 수 N
        int N = sc.nextInt();
        int[][] person = new int[N][2];
        int max = Integer.MIN_VALUE;
        int expect = 0;
        for (int i = 0; i < N; i++) {
            person[i][0] = sc.nextInt();
            person[i][1] = sc.nextInt();

            //기대 방청객 번호
            if (max < person[i][1] - person[i][0]) {
                max = person[i][1] - person[i][0];
                expect = i+1;
            }
        }

        //케이크에 조각 체크
        int[] cake = new int[L+1];
        for (int i = 0; i < N; i++) {
            for (int j = person[i][0]; j <= person[i][1]; j++) {
                if(cake[j] == 0) cake[j] = i+1;
            }
        }

        //가져간 케이크 수를 체크한다.
        int[] count = new int[N+1];
        for (int i = 1; i <= L; i++) {
            count[cake[i]]++;
        }

        //가장 많이 가져간 방청객을 찾는다.
        int result = 1;
        for (int i = 2; i <= N; i++) {
            if(count[result] < count[i]) {
                result = i;
            }
        }

        //기대 출력
        System.out.println(expect);
        //실제 출력
        System.out.println(result);

    }
}