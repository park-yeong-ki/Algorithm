package bj2999;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //메시지 입력
        String str = sc.next();

        //메시지 길이
        int N = str.length();

        //R, C 선택
        int R = 0, C = 0;
        for (int r = 1; r <= N; r++) {
            for (int c = r; c <= N; c++) {
                if (r * c == N) {
                    R = r;
                    C = c;
                }
            }
        }

        //메시지 만들기
        char[][] message = new char[R][C];
        int idx = 0;
        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                message[j][i] = str.charAt(idx++);
            }
        }

        //출력
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(message[i][j]);
            }
        }
        System.out.println();

    }
}
