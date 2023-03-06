package bj15658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int N;
    static int[] arr;
    static char[] pOperator;
    static ArrayList<Character> operator = new ArrayList<>();
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //수의 개수 N
        N = Integer.parseInt(br.readLine());

        //N개의 수로 이루어진 수열
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //연산자 개수 입력
        st = new StringTokenizer(br.readLine());
        int[] oNum = new int[4];
        char[] cArr = {'+', '-', '*', '/'};
        for (int i = 0; i < 4; i++) {
            oNum[i] = Integer.parseInt(st.nextToken());
        }

        //연산자 배열입력
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < oNum[i]; j++) {
                operator.add(cArr[i]);
            }
        }

        //연산자 순열
        isSelected = new boolean[operator.size()];
        pOperator = new char[N-1];
        perm(0);

        //출력
        System.out.println(max);
        System.out.println(min);
    }

    //순열
    static void perm(int idx) {
        //기저조건: 연산자를 N-1개 뽑으면 종료
        if (idx == N-1) {
            //뽑은 연산자와 계산
            int result = arr[0];
            for (int i = 0; i < N-1; i++) {
                switch (pOperator[i]) {
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
                        if (result < 0 && arr[i+1] > 0) {
                            result *= -1;
                            result /= arr[i+1];
                            result *= -1;
                        } else {
                            result /= arr[i+1];
                        }
                        break;
                }
            }

            //최대값, 최소값 갱신
            max = Math.max(max, result);
            min = Math.min(min, result);

            return;
        }

        //유도부분
        char temp = ' ';
        for (int i = 0; i < operator.size(); i++) {
            if(temp == operator.get(i)) continue;
            if(isSelected[i]) continue;
            pOperator[idx] = operator.get(i);
            isSelected[i] = true;
            temp = pOperator[idx];
            perm(idx+1);
            isSelected[i] = false;
        }
    }
}