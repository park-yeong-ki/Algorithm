package bj2798;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //N, M 입력
        int N = sc.nextInt();
        int M = sc.nextInt();

        //카드 배열 생성
        int[] card = new int[N];

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            card[i] = sc.nextInt();
        }

        //세 숫자를 조합하여 더한 값이 M보다 작거나 같은 조건에서 제일 큰 수를 찾는다.
        int sum = 0;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    sum = card[i] + card[j] + card[k];
                    if (M >= sum && result < sum) {
                        result = sum;
                    }
                }
            }
        }

        //출력
        System.out.println(result);
    }
}
