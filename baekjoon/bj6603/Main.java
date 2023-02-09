package bj6603;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    //클래스 변수 생성
    static int k;
    static int[] S;
    static int[] lotto;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    //조합 문제
    public static void main(String[] args) throws IOException {

        //무한반복문 생성
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            //k입력
            k = Integer.parseInt(st.nextToken());

            //k가 0일 경우 반복문 종료
            if (k == 0) {
                break;
            }

            //집합S 생성
            S = new int[k];

            //로또 배열 생성
            lotto = new int[6];

            //S 요소 입력
            for (int i = 0; i < k; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }

            //재귀 호출
            combination(0, 0);
            bw.newLine();
        }
        bw.close();
    }

    //재귀함수 생성
    public static void combination(int start, int idx) throws IOException {
        //기저조건
        if (idx == 6) {
            //로또 출력
            for (int i = 0; i < lotto.length; i++) {
                bw.write(lotto[i] + " ");
            }
            bw.newLine();
            return;
        }

        //유도부분
        for (int i = start; i < S.length; i++) {
            lotto[idx] = S[i];
            //재귀 호출
            combination(i+1, idx+1);
        }
    }
}
