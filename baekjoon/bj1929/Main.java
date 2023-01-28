package bj1929;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //Scanner 사용했을 경우 시간 초과
        //BufferedReader, BufferedWriter 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        //M, N 입력
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        //카운트 배열 생성
        int[] count = new int[N+1];
        for (int i = M; i <= N; i++) {
            for (int j = 1; j <= Math.sqrt(i); j++) {
                //제곱근까지 반복하여 찾은 약수의 개수를 카운트 배열에 저장
                if (i % j == 0 && i != 1) {
                    count[i]++;
                }
            }
        }

        for (int i = 0; i < count.length; i++) {
            //소수일 경우 출력
            if (count[i] == 1) {
                bw.write(String.valueOf(i));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
