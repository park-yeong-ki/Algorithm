package swea1218;

import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //총 10개의 테스트케이스
        for (int test_case = 1; test_case <= 10; test_case++) {
            //입력될 문자열 길이 입력
            int length = sc.nextInt();

            //스택 생성
            Stack stack = new Stack<>();

            //문자열을 char배열로 변경
            char[] cArr = sc.next().toCharArray();

            //여는 괄호와 닫는 괄호에 대한 배열 생성
            char[] open = {'{', '[', '('};
            char[] close = {'}', ']', ')'};

            //초기값은 유효하다고 설정
            int result = 1;
            //문자열 길이만큼 반복
            outer:
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < open.length; j++) {
                    //문자열이 여는 괄호라면 스택에 저장
                    if (cArr[i] == open[j]) {
                        stack.push(cArr[i]);
                    } else if (cArr[i] == close[j]) {
                        //닫는 괄호일 경우 스택에 아무것도 남아있지 않거나 스택에서 꺼낸 값이 짝에 맞는 여는 괄호가 아니라면 유효하지 않음으로 설정하고 반복문 종료
                        if (stack.size() == 0 || (char) stack.pop() != open[j]) {
                            result = 0;
                            break outer;
                        }
                    }
                }
            }

            //출력
            System.out.println("#" + test_case + " " + result);
        }
    }
}
