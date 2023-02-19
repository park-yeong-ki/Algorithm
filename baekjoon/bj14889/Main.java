package bj14889;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int N;
    static int[][] S;
    static int[] result;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int min = Integer.MAX_VALUE;
    static List<Integer> ability = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //N입력
        N = Integer.parseInt(br.readLine());

        //배열 S입력
        S = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //재귀함수 사용
        result = new int[N/2];
        comb(0, 0);

        //조합이 대칭적으로 뽑힌 것을 이용하여 능력치 차이의 최솟값을 구한다.
        for (int i = 0; i < ability.size()/2; i++) {
            int diff = Math.abs(ability.get(i) - ability.get(ability.size() - 1 - i));
            min = Math.min(min, diff);
        }

        //출력
        bw.write(min + "\n");
        bw.close();
    }
    //재귀함수 생성
    static void comb(int start, int idx) throws IOException {
        //기저조건 -> N/2개를 고르면 종료
        if (idx == N/2) {
            int sum = 0;
            //N/2명씩 뽑힌 팀에서 2명씩 조합하여 능력치의 합을 구한다.
            for (int i = 0; i < result.length; i++) {
                for (int j = i+1; j < result.length; j++) {
                    sum += S[result[i]][result[j]] + S[result[j]][result[i]];
                }
            }
            ability.add(sum);

            return;
        }

        //유도부분 -> 조합 구현
        for (int i = start; i < N; i++) {
            result[idx] = i;
            comb(i + 1, idx + 1);
        }
    }
}
