package bj2810;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //좌석의 수 N
        int N = sc.nextInt();

        //문자열 입력
        char[] cArr = sc.next().toCharArray();
        //맨 왼쪽 끝의 컵홀더 개수를 초기값 설정
        int count = 1;
        //오른쪽에 컵홀더를 두는 경우만 생각
        for (int i = 0; i < N; i++) {
            //L일 경우 인덱스를 한칸 더 옮긴다
            if (cArr[i] == 'L') {
                i++;
            }
            count++;
        }

        //출력
        if (count >= N) System.out.println(N);
        else System.out.println(count);
    }
}
