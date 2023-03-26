package swea2112;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int D;
    static int W;
    static int K;
    static int[][] film;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 케이스 수
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            //보호 필름의 두께 D, 가로크기 W, 합격기준 K
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            //보호 필름 단면의 정보
            film = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //각 막에 대해 약품A를 넣는 경우, 약품 B를 넣는 경우, 약품을 안넣는 경우에 대한 경우의 수를 구한다.
            subset(0, 0, 0);

            //출력
            bw.write("#" + test_case + " " + min + "\n");

            //전역변수 초기화
            min = Integer.MAX_VALUE;
        }
        bw.close();
    }

    static void subset(int cnt, int a, int b) {
        //기저조건
        //a,b 투입횟수가 최소투입횟수보다 크거나 같은 경우
        if (a + b >= min) {
            return;
        }

        //성능검사 통과한 경우
        if (isPassed()) {
            //약품 투입횟수 최소값 갱신
            min = Math.min(min, a + b);
            return;
        }

        //모든 막에 대한 선택이 끝난 경우
        if (cnt == D) {
            return;
        }

        //유도부분
        //원본 배열 복사
        int[] copyFilm = film[cnt].clone();

        //약품 A를 넣는 경우
        Arrays.fill(film[cnt], 0);
        subset(cnt + 1, a + 1, b);
        //약품 B를 넣는 경우
        Arrays.fill(film[cnt], 1);
        subset(cnt + 1, a, b + 1);
        //약품을 넣지 않는 경우
        film[cnt] = copyFilm;
        subset(cnt + 1, a, b);
    }

    //성능검사
    static boolean isPassed() {
        boolean result = true;
        for (int i = 0; i < W; i++) {
            int aCount = 0, bCount = 0;
            boolean flag = false;
            for (int j = 0; j < D; j++) {
                if (film[j][i] == 0) {
                    bCount = 0;
                    aCount++;
                } else {
                    aCount = 0;
                    bCount++;
                }

                //세로방향에 대해서 동일한 특성의 셀들이 K개 이상 연속적으로 있는 경우에만 성능검사를 통과
                if (aCount == K || bCount == K) {
                    flag = true;
                    break;
                }
            }
            //통과하지 못한 경우 전체 반복문 종료
            if (!flag) {
                result = false;
                break;
            }
        }

        return result;
    }
}
