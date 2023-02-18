package bj16435;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //과일 개수 N, 초기 길이 L 입력
        int N  = sc.nextInt();
        int L = sc.nextInt();

        //과일 높이
        int[] h = new int[N];
        for (int i = 0; i < N; i++) {
            h[i] = sc.nextInt();
        }

        //과일 높이 정렬
        Arrays.sort(h);

        //과일의 개수만큼 반복
        for (int i = 0; i < h.length; i++) {
            //자신의 길이보다 작거나 같은 높이에 있는 과일을 먹으면 길이가 1씩 늘어난다
            if (L >= h[i]) {
                L++;
            }
        }

        //출력
        System.out.println(L);
    }
}
