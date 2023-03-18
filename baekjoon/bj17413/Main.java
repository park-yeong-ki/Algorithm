package bj17413;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //스택 생성
        Stack<Character> stack = new Stack<>();
        //문자열 입력받기
        String str = br.readLine();

        //스택을 활용하여 문제에 주어진 조건대로 문자열을 뒤집어서 출력
        boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            //태그를 만난 경우에 대한 조건 설정
            if (str.charAt(i) == '<') {
                //단어 뒤집어서 출력
                while (!stack.isEmpty()) {
                    bw.write(stack.pop());
                }
                bw.write(str.charAt(i));
                flag = true;
            } else if (str.charAt(i) == '>') {
                bw.write(str.charAt(i));
                flag = false;
            } else {
                if (flag) {
                    bw.write(str.charAt(i));
                } else {
                    //단어 뒤집어서 출력
                    if (str.charAt(i) == ' ') {
                        while (!stack.isEmpty()) {
                            bw.write(stack.pop());
                        }
                        bw.write(str.charAt(i));
                    } else {
                        stack.push(str.charAt(i));
                    }
                }
            }
        }
        //스택 비워내기
        while (!stack.isEmpty()) {
            bw.write(stack.pop());
        }

        bw.close();
    }
}
