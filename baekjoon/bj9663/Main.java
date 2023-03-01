package bj9663;

import java.util.Scanner;

public class Main {
    static int N;
    static int[] arr;
    static int count;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //N입력
        N = sc.nextInt();

        //퀸이 위치한 좌표를 저장할 배열
        arr = new int[N];
        queen(0);

        System.out.println(count);
    }

    static void queen(int row){
        //놓을 수 없는 자리에 퀸이 위치한 경우
        if (!isPossible(row - 1)) {
            return;
        }

        //기저조건 -> N개의 퀸을 배치했을 때
        if (row == N) {
            count++;
            return;
        }

        //배열의 인덱스는 퀸이 놓이는 위치의 행이고 i는 열이다.
        for (int i = 0; i < N; i++) {
            arr[row] = i;
            queen(row + 1);
        }
    }

    static boolean isPossible(int row) {
        for (int i = 0; i < row; i++) {
            //열이 같거나 대각선의 위치인 경우
            if (arr[i] == arr[row] || Math.abs(arr[i] - arr[row]) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

}
