package bj9095;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //전역변수 설정
    static int n;
    static int[] arr;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //테스트 케이스 입력
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            //정수 n입력
            n = sc.nextInt();

            //배열의 크기를 1부터 n까지 반복하면서 방법의 수를 체크한다.
            for (int j = 1; j <= n; j++) {
                arr = new int[j];
                //재귀함수 사용
                perm(0);
            }

            //결과 출력
            System.out.println(count);

            //카운트 초기화
            count = 0;
        }
    }

    //재귀함수 생성
    public static void perm(int cnt) {
        //기저조건: 배열이 전부 채워지면 종료
        if (cnt == arr.length) {
            //배열의 합을 구한다
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
            }

            //배열의 합이 입력받은 n과 일치하면 방법의 수를 체크함
            if (sum == n) {
                count++;
            }
            return;
        }

        //유도부분 -> 중복순열 구현
        for (int i = 1; i <= 3; i++) {
            arr[cnt] = i;
            //재귀호출
            perm(cnt+1);
        }
    }
}
