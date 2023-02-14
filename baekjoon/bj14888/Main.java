package bj14888;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] arr;
    static char[] operator;
    static char[] permOperator;
    static boolean[] isSelected;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        //N입력
        N = Integer.parseInt(br.readLine());

        //수열 입력
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //연산자 개수 입력
        st = new StringTokenizer(br.readLine());
        int[] operatorCount = new int[4];
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            operatorCount[i] = Integer.parseInt(st.nextToken());
            sum += operatorCount[i];
        }

        //재귀함수에서 사용할 배열 생성 -> 순열 배열, 선택체크 배열
        permOperator = new char[sum];
        isSelected = new boolean[sum];

        //연산자 배열 입력
        operator = new char[sum];
        int size = 0;
        for (int i = 0; i < operatorCount.length; i++) {
            for (int j = 0; j < operatorCount[i]; j++) {
                if (i == 0) {
                    operator[size++] = '+';
                } else if (i == 1) {
                    operator[size++] = '-';
                } else if (i == 2) {
                    operator[size++] = '*';
                } else {
                    operator[size++] = '/';
                }
            }
        }

        //재귀함수 사용
        perm(0);

        //출력
        bw.write(max + "\n");
        bw.write(min + "\n");
        bw.close();
    }

    //재귀함수 생성
    static void perm(int cnt) throws IOException {
        //기저조건 -> nPn
        if (cnt == operator.length) {

            //계산 결과 구하기
            int result = arr[0];
            for (int i = 0; i < operator.length; i++) {
                switch (permOperator[i]) {
                    case '+':
                        result += arr[i+1];
                        break;
                    case '-':
                        result -= arr[i+1];
                        break;
                    case '*':
                        result *= arr[i+1];
                        break;
                    case '/':
                        if (result < 0) {
                            result = -1 * (Math.abs(result) / arr[i + 1]);
                        } else {
                            result /= arr[i+1];
                            break;
                        }
                }
            }

            //최대값, 최소값 구하기
            min = Math.min(min, result);
            max = Math.max(max, result);

            return;
        }

        //유도부분
        for (int i = 0; i < operator.length; i++) {
            if (isSelected[i]) continue;
            permOperator[cnt] = operator[i];
            isSelected[i] = true;
            //재귀호출
            perm(cnt+1);
            isSelected[i] = false;
        }
    }
}
