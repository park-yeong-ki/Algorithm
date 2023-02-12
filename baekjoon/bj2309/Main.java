package bj2309;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //전역변수 설정
    //9명의 난쟁이들의 키를 담을 배열 생성
    static int[] arr9 = new int[9];
    //7명의 난쟁이들의 키를 담을 배열 생성
    static int[] arr7 = new int[7];
    //결과를 담을 배열 생성
    static int[] result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //배열 요소 입력
        for (int i = 0; i < 9; i++) {
            arr9[i] = sc.nextInt();
        }

        //재귀함수 사용
        comb(0, 0);

        //오름차순으로 정렬
        Arrays.sort(result);

        for (int i = 0; i < result.length; i++) {
            //출력
            System.out.println(result[i]);
        }
    }

    //조합을 만들 재귀함수 생성
    public static void comb(int start, int idx) {
        //기저조건 설정
        if (idx == 7) {
            //키의 합을 구할 반복문 설정
            int sum = 0;
            for (int i = 0; i < idx; i++) {
                sum += arr7[i];
            }

            //합이 100인 경우 출력할 배열에 복사
            if (sum == 100) {
                result = arr7.clone();
            }

            return;
        }

        //유도부분
        for (int i = start; i < 9; i++) {
            arr7[idx] = arr9[i];
            //재귀 호출
            comb(i+1, idx+1);
        }
    }
}
