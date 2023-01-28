package bj9020;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader, BufferedWriter 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 케이스 개수 입력
        int T = Integer.parseInt(br.readLine());

        //테스트 케이스만큼 반복
        for (int test_case = 1; test_case <= T; test_case++) {
            //n 입력
            int n = Integer.parseInt(br.readLine());

            //n 크기만큼의 배열 생성
            int[] arr = new int[n];
            int size = 0;
            outer:
            for (int i = 2; i < n; i++) {
                for (int j = 2; j <= Math.sqrt(i); j++) {
                    //소수가 아닌 경우에 반복문 통과
                    if (i % j == 0) {
                        continue outer;
                    }
                }
                //소수가 확인이 되면 배열 요소에 값을 넣음
                arr[size++] = i;
            }
            //배열 요소에 소수가 들어있는 만큼 길이를 자름
            arr = Arrays.copyOf(arr, size);

            int min = Integer.MAX_VALUE;
            int num1 = 0;
            int num2 = 0;
            for (int i = 0; i < size; i++) {
                //순서만 다른 결과가 나오지 않도록 안쪽 반복문의 초기식을 j=i로 설정
                for (int j = i; j < size; j++) {
                    //배열 요소에 등록된 소수들을 활용한 x+y = n
                    //소수의 차이가 가장 작은 것을 찾기 위해 min보다 배열의 차이가 작아야 참이 되는 조건식 추가
                    if (arr[i] + arr[j] == n && min > arr[j] - arr[i]) {
                        min = arr[j] - arr[i];
                        num1 = arr[i];
                        num2 = arr[j];
                    }
                }
            }
            //출력할 내용
            bw.write(num1 + " " + num2);
            bw.newLine();
        }
        //출력
        bw.flush();
    }
}
