package bj2961;

import java.util.Scanner;

public class Main {
    //전역변수 선언
    static int N;
    static int[][] arr;
    static boolean[] isSelected;
    static int min = Integer.MAX_VALUE;

    //부분집합 문제
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //재료의 개수 N입력
        N = sc.nextInt();

        //재료 배열 생성
        arr = new int[N][2];

        //부분집합을 구하기 위한 배열생성
        isSelected = new boolean[N];

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        //재귀함수 사용
        tasteDiff(0);

        //결과 출력
        System.out.println(min);
    }
    //재귀함수 생성
    public static void tasteDiff(int cnt) {
        //기저조건: cnt가 n만큼 반복하여 isSelected가 전부 채워진 경우
        if (cnt == N) {
            //신맛
            int S = 1;
            //쓴맛
            int B = 0;
            //공집합검사 변수
            int num = 0;
            for (int i = 0; i < N; i++) {
                //만들어진 부분집합의 신맛과 쓴맛을 각각 문제에서 주어진 대로 계산
                if (isSelected[i]) {
                    S *= arr[i][0];
                    B += arr[i][1];
                } else {
                    num++;
                }
            }

            //공집합일 경우 제외하여 신맛과 쓴맛의 차이의 최소값을 구한다.
            if (num != N) min = Math.min(min, Math.abs(S - B));

            return;
        }

        //유도부분: 부분집합을 만들기위해 값이 있는 부분은 true, 그렇지 않은 부분은 false로 구분
        isSelected[cnt] = true;
        tasteDiff(cnt + 1);
        isSelected[cnt] = false;
        tasteDiff(cnt + 1);
    }
}
