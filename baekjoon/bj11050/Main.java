package bj11050;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        //공식 사용 -> 이항계수는 조합의 정의와 같음
        int num1 = 1;
        int num2 = 1;
        for (int i = 0; i < K; i++) {
            num1 *= (N-i);
            num2 *= i+1;
        }

        //출력
        System.out.println(num1/num2);
    }
}
