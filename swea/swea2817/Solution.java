package swea2817;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int K;
    static int[] arr;
    static boolean[] isSelected;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 케이스의 수 T
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            //자연수 N(1 ≤ N ≤ 20)과 K(1 ≤ K ≤ 1000)
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            //N개의 자연수 수열 생성
            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            //원소의 선택여부를 설정할 배열 생성
            isSelected = new boolean[N];
            subset(0, 0);

            //결과 출력
            bw.write("#" + test_case + " " + count + "\n");

            //전역변수 초기화
            count = 0;
        }
        bw.close();
    }

    //부분집합 생성
    static void subset(int idx, int sum) {
        //기저조건
        //부분집합이 완성되지 않았더라도 합이 K보다 큰 경우
        if (sum > K) {
            return;
        }
        //부분집합 만든 경우
        if (idx == N) {
            //합이 K와 같다면 횟수를 센다.
            if (sum == K) {
                count++;
            }

            return;
        }

        //유도부분
        isSelected[idx] = true;
        subset(idx + 1, sum+arr[idx]);
        isSelected[idx] = false;
        subset(idx + 1, sum);
    }
}
