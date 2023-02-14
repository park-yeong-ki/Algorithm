package bj15686;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int N;
    static int M;
    static int[][] chicken;
    static int[][] home;
    static int[][] combChicken;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int sMin = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        //N, M입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //도시 배열 생성 및 요소 입력
        int[][] city = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //치킨집 좌표 배열
        chicken = new int[N*N][2];
        //집 좌표 배열
        home = new int[N*N][2];
        //조합할 치킨집 좌표 배열
        combChicken = new int[M][2];

        //치킨집 좌표, 집 좌표 배열 요소 입력
        int homeSize = 0;
        int chickenSize = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (city[i][j] == 1) {
                    home[homeSize][0] = i;
                    home[homeSize][1] = j;
                    homeSize++;
                } else if (city[i][j] == 2) {
                    chicken[chickenSize][0] = i;
                    chicken[chickenSize][1] = j;
                    chickenSize++;
                }
            }
        }
        home = Arrays.copyOf(home, homeSize);
        chicken = Arrays.copyOf(chicken, chickenSize);

        //재귀함수 사용
        comb(0, 0);

        //출력
        bw.write(sMin + "\n");
        bw.close();
    }

    //재귀함수 생성 -> 치킨집 조합
    static void comb(int start, int idx) throws IOException {
        //기저조건 -> 치킨집 M개를 뽑는다.
        if (idx == M) {
            //M개의 조합된 치킨집과 집과의 치킨거리의 합을 구한다
            int sum = 0;
            for (int i = 0; i < home.length; i++) {
                int distance = 0;
                int dMin = Integer.MAX_VALUE;
                for (int j = 0; j < combChicken.length; j++) {
                    distance = Math.abs(combChicken[j][0] - home[i][0]) +
                            Math.abs(combChicken[j][1] - home[i][1]);

                    dMin = Math.min(dMin, distance);
                }
                sum += dMin;
            }

            //조합된 치킨집들의 치킨 거리 최소값을 구한다
            sMin = Math.min(sMin, sum);
            return;
        }

        //유도부분
        for (int i = start; i < chicken.length; i++) {
            combChicken[idx][0] = chicken[i][0];
            combChicken[idx][1] = chicken[i][1];
            comb(i+1, idx+1);
        }

    }
}
