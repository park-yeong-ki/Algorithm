package bj2023;

import java.io.*;

public class Main {
    //전역변수 설정
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        //N입력
        N = Integer.parseInt(br.readLine());

        //재귀함수 사용
        perm(0, "");

        bw.close();
    }

    //재귀함수 생성
    static void perm(int cnt, String str) throws IOException {
        //기저조건
        //소수가 아니면 종료
        if (!isPrime(str)) {
            return;
        }

        //4자리수를 채운 경우 결과값 출력
        if (cnt == N) {
            bw.write(str + "\n");
            return;
        }

        //유도부분 -> 1부터 9까지 숫자를 중복순열
        for (int i = 1; i < 10; i++) {
            perm(cnt + 1, str+i);
        }
    }

    //소수인지를 검증하는 함수
    static boolean isPrime(String str) {
        //처음 입력값일 경우는 true
        if (str.equals("")) {
            return true;
        }

        //첫 숫자가 1일 경우 false
        if (str.equals("1")) {
            return false;
        }

        //문자열을 숫자로 변환
        int num = Integer.parseInt(str);

        //2부터 수의 제곱근까지 반복하며 약수 개수 체크
        int count = 1;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                count++;
            }

            //제곱근까지의 약수의 개수가 1보다 크면 소수가 아님
            if (count > 1) {
                return false;
            }
        }
        return true;
    }
}
