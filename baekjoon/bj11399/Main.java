package bj11399;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //사람의 수 N
        int N = sc.nextInt();

        //인출 시간 배열
        int[] P = new int[N];
        for (int i = 0; i < N; i++) {
            P[i] = sc.nextInt();
        }

        //인출시간 오름차순 정렬
        Arrays.sort(P);

        //배열을 누적합으로 변환하고 변환한 값의 합을 구한다
        int sum = P[0];
        for (int i = 1; i < N; i++) {
            P[i] += P[i-1];
            sum += P[i];
        }

        //출력
        System.out.println(sum);
    }
}
