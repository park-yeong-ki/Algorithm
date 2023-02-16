package swea5215;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    //전역변수 설정
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] isSelected;
    static int N;
    static int L;
    static int[][] ingredients;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        //테이스 케이스 수 입력
        int T = Integer.parseInt(br.readLine());

        //테스트 케이스 수만큼 반복
        for (int test_case = 1; test_case <= T; test_case++) {
            //재료의 수, 제한 칼로리 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            //재료 정보 입력
            ingredients = new int[N][2];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                ingredients[i][0] = Integer.parseInt(st.nextToken());
                ingredients[i][1] = Integer.parseInt(st.nextToken());
            }

            //부분집합을 구하기 위한 배열 생성
            isSelected = new boolean[N];

            //재귀함수 사용
            subset(0, 0, 0);

            //결과 출력
            bw.write("#" + test_case + " " + max + "\n");
            //max 초기화
            max = Integer.MIN_VALUE;
        }
        bw.close();
    }

    //재귀함수 생성 -> 부분집합을 구하는 목적
    public static void subset(int cnt, int tasteSum, int calorieSum) throws IOException {
        //기저조건
        //칼로리의 합이 L보다 커질경우
        if (calorieSum > L) {
            return;
        }

        //모든 재료에 대해 체크했을 경우
        if (cnt == N) {
            //최대값과 맛의 합을 비교
            max = Math.max(max, tasteSum);
            return;
        }

        //유도부분
        isSelected[cnt] = true;
        subset(cnt + 1, tasteSum + ingredients[cnt][0], calorieSum + ingredients[cnt][1]);
        isSelected[cnt] = false;
        subset(cnt + 1, tasteSum, calorieSum);
    }
}
