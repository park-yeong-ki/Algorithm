package bj2805;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //N, M입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        //최대값 구하기
        long max = Integer.MIN_VALUE;
        //나무 배열 생성 및 요소 입력
        int[] tree = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < tree.length; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, tree[i]);
        }

        //최소값과 중간값 변수 생성
        long min = 1;
        long middle = 0;
        while(min < max) {
            //중간값 설정
            middle = (min + max)/2;

            //자른 나무 길이의 합을 구한다
            long sum = 0;
            for (int i = 0; i < tree.length; i++) {
                long tLength = tree[i] - middle;
                if (tLength > 0) {
                    sum += tLength;
                }
            }

            //이분 탐색 구현
            if (sum >= M) {
                min = middle+1;
            } else {
                max = middle;
            }
        }

        //출력
        bw.write(min-1 + "\n");
        bw.close();
    }
}