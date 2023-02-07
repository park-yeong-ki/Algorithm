package bj15649;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //N, M 입력
        int N = sc.nextInt();
        int M = sc.nextInt();

        //1부터 N까지 자연수를 저장할 배열 생성
        int[] arr = new int[N+1];

        //배열 요소 입력
        for (int i = 1; i < arr.length; i++) {
            arr[i] = i;
        }
        arr = Arrays.copyOfRange(arr, 1, arr.length);

        //중복여부를 체크할 배열 생성
        boolean[] check = new boolean[N];

        //결과를 저장할 배열 생성
        int[] result = new int[M];

        //재귀 함수 사용
        recursion(0, M, arr, check, result);

    }

    //재귀 함수
    public static void recursion(int idx, int M, int[] arr, boolean[] check, int[] result) {
        //기저조건
        if (idx == M) {
            //결과 출력
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }

        //유도 부분
        for (int i = 0; i < arr.length; i++) {
            //이미 사용한 숫자면 통과
            if (check[i]) continue;
            //사용한 숫자가 아닌경우 저장
            result[idx] = arr[i];
            //사용 표시
            check[i] = true;

            //다음 인덱스에 숫자를 저장하기위해 재귀 호출
            recursion(idx+1, M, arr, check, result);
            //하나의 result가 완성되면 사용 표시 해제
            check[i] = false;
        }

    }
}