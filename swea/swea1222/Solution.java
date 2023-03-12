package swea1222;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 케이스 10개
        for (int test_case = 1; test_case <= 10; test_case++) {
            //문자열 계산식의 길이 n
            int n = Integer.parseInt(br.readLine());

            //문자열 계산식 입력
            char[] cArr = br.readLine().toCharArray();

            //스택 생성
            Stack<Character> stack1 = new Stack<>();

            //후위 표기식으로 변경
            int size = 0;
            for (int i = 0; i < n; i++) {
                //피연산자일 경우
                if (cArr[i] != '+') {
                    cArr[size++] = cArr[i];
                }
                //연산자일 경우
                else {
                    //스택안에 들어있는 연산자의 우선순위 별로 구분 -> 같거나 빠른 연산자라면 출력, 느린 경우 스택안에 있는 연산자를 출력 후 스택에 저장, 스택이 비어있는 경우에도 스택에 저장
                    if (!stack1.isEmpty() && stack1.peek() == '+') {
                        cArr[size++] = cArr[i];
                    } else {
                        stack1.push(cArr[i]);
                    }
                }
            }
            //스택 비워내기
            while (!stack1.isEmpty()) {
                cArr[size++] = stack1.pop();
            }

            //후위표기식 계산
            Stack<Integer> stack2 = new Stack<>();
            for (int i = 0; i < n; i++) {
                //피연산자일 경우
                if (cArr[i] != '+') {
                    stack2.push(cArr[i] - '0');
                }
                //연산자일 경우
                else {
                    //필요한 피연산자를 꺼내어 연산 후 다시 스택에 저장
                    stack2.push(stack2.pop() + stack2.pop());
                }
            }

            //연산 결과 출력
            bw.write("#" + test_case + " " + stack2.pop() + "\n");
        }
        bw.close();
    }
}
