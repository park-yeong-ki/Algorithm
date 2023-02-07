package bj11729;

import java.util.Scanner;

public class Main {
    //호출횟수를 저장할 변수 선언 및 초기화
    static int cnt = 0;
    //이동순서를 저장할 StringBuilder 생성
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //원판의 개수 입력
        int N = sc.nextInt();

        //재귀함수 사용
        hanoi(N, 1, 2, 3);
        System.out.println(cnt);
        System.out.print(sb.toString());
    }
    //재귀함수 생성
    public static void hanoi(int N, int start, int middle, int end){
        //기저조건: 옮길 원판이 없으면 종료
        if (N == 0) {
            return;
        }

        //시작기둥에 있는 N-1개의 원판을 끝기둥을 이용하여 중간기둥으로 이동
        hanoi(N-1, start, end, middle);

        //시작기둥에 있는 N번째 원판을 끝기둥으로 이동
        sb.append(start + " " + end + "\n");
        cnt++;

        //중간기둥에 있는 N-1개의 원판을 시작기둥을 이용하여 끝기둥으로 이동
        hanoi(N-1, middle, start, end);
    }
}
