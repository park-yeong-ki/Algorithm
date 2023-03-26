package swea1486;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int B;
    static int[] height;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            //점원 수 N, 높이 B
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            //점원 키 입력
            height = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }

            //키의 합에 대한 부분집합 생성 후 B와의 최소값 구한다
            subset(0, 0);

            //출력
            bw.write("#" + test_case + " " + min + "\n");

            //전역변수 초기화
            min = Integer.MAX_VALUE;
        }
        bw.close();
    }

    static void subset(int cnt, int hSum) {
        //기저조건
        //부분집합 생성완료
        if (cnt == N) {
            //키의 합이 B이상
            if (hSum >= B) {
                min = Math.min(min, hSum - B);
            }
            return;
        }

        //유도부분
        //점원 선택 경우
        subset(cnt + 1, hSum + height[cnt]);
        //점원 선택 하지 않은 경우
        subset(cnt + 1, hSum);
    }
}
