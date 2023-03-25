package swea5215;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_2 {
    static int N;
    static int L;
    static int[][] food;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        //재료의 개수가 특정되지 않았으므로 부분집합을 이용해서 풀이
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트케이스 수 입력
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            //재료의 수, 제한 칼로리를 나타내는 N, L(1 ≤ N ≤ 20, 1 ≤ L ≤ 104)
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            //음식 맛, 칼로리 입력
            food = new int[N][2];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                food[i][0] = Integer.parseInt(st.nextToken());
                food[i][1] = Integer.parseInt(st.nextToken());
            }

            subset(0, 0, 0);

            //출력
            bw.write("#" + test_case + " " + max + "\n");

            //전역변수 초기화
            max = Integer.MIN_VALUE;
        }
        bw.close();
    }

    static void subset(int idx, int taste, int calorie) {
        //기저조건
        //제한 칼로리를 넘은 경우
        if (calorie > L) {
            return;
        }

        //부분집합 완성 -> 재료 선택 완료
        if (idx == N) {
            //맛에 대한 최대값 갱신
            max = Math.max(max, taste);
            return;
        }

        //유도부분
        //재료가 선택된 경우
        subset(idx + 1, taste + food[idx][0], calorie + food[idx][1]);
        //재료가 선택되지 않은 경우
        subset(idx + 1, taste, calorie);
    }
}
