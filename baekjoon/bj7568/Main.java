package bj7568;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //전체 사람 수 입력
        int N  = sc.nextInt();

        //키, 몸무게 배열 생성
        int[][] person = new int[N][2];

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                person[i][j] = sc.nextInt();
            }
        }

        //카운트 배열 생성
        int[] count = new int[N];
        //배열 요소 초기값을 1로 설정
        Arrays.fill(count, 1);

        //반복문을 통해서 다른 사람보다 덩치가 작은 경우 카운트 배열 요소 1씩 증가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (person[i][0] < person[j][0]) {
                    if (person[i][1] < person[j][1]) {
                        count[i]++;
                    }
                }
            }
        }

        //출력
        for (int i = 0; i < count.length; i++) {
            System.out.print(count[i] + " ");
        }
        System.out.println();

    }
}
