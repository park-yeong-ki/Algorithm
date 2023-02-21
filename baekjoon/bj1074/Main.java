package bj1074;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int num = 0;
    static int N, r, c;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //N, r, c입력
        N = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();

        //크기를 2^N으로 설정
        int size = (int) Math.pow(2, N);

        //재귀함수 사용 -> 전체 배열을 나누기 위해 원점(0,0)과 배열의 크기를 넣어준다.
        binarySearch(0, 0, size);

        System.out.println(num);
    }

    //4분할하는 함수 -> 크기를 2씩 나눈다(N-1과 동일)
    static void binarySearch(int x, int y, int size) {
        //기저조건
        if (size == 2) {
            //마지막 크기에서 반복문을 통해 정확한 값을 구한다.
            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {
                    if (i == r && j == c) {
                        return;
                    }
                    num++;
                }
            }
            return;
        }

        //유도부분
        //좌측 상단
        if (r < x + size / 2 && c < y + size / 2) {
            binarySearch(x, y, size / 2);
        }

        //우측 상단
        else if (r < x + size / 2 && c < y + size) {
            binarySearch(x, y + size / 2, size / 2);
            num += size / 2 * size / 2;
        }

        //좌측 하단
        else if (r < x + size && c < y + size / 2) {
            binarySearch(x + size / 2, y, size / 2);
            num += 2 * size / 2 * size / 2;
        }

        //우측 하단
        else {
            binarySearch(x + size / 2, y + size / 2, size / 2);
            num += 3 * size / 2 * size / 2;
        }
    }

}
