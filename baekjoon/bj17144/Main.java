package bj17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] arr = new int[R][C];
        List<Integer> cleaner = new ArrayList<Integer>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1) {
                    cleaner.add(i);
                }
            }
        }
        int topC = cleaner.get(0);
        int botC = cleaner.get(1);

        int time = 0;
        while(time < T) {
            int[][] nArr = new int[R][C];
            nArr[topC][0] = -1;
            nArr[botC][0] = -1;
            //미세먼지 확산
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    int dust = arr[i][j]/5;
                    if (arr[i][j] != 0 && arr[i][j] != -1) {
                        int count = 0;
                        for (int j2 = 0; j2 < 4; j2++) {
                            int tR = i + dr[j2];
                            int tC = j + dc[j2];

                            if (tR >= 0 && tR <R && tC >=0 && tC <C) {
                                if (arr[tR][tC] != -1) {
                                    nArr[tR][tC] += dust;
                                    count++;
                                }
                            }
                        }
                        nArr[i][j] += arr[i][j] - dust * count;
                    }
                }
            }

            //공기청정기 작동
            //위쪽 공기청정기 -> 반시계방향
            int temp1 = nArr[topC][C-1];
            for (int i = C-1; i >= 1; i--) {
                nArr[topC][i] = nArr[topC][i-1];
            }
            nArr[topC][1] = 0;

            int temp2 = nArr[0][C-1];
            for (int i = 0; i <= topC-1; i++) {
                nArr[i][C-1] = nArr[i+1][C-1];
            }
            nArr[topC-1][C-1] = temp1;

            temp1 = nArr[0][0];
            for (int i = 0; i <= C-1-1; i++) {
                nArr[0][i] = nArr[0][i+1];
            }
            nArr[0][C-2] = temp2;


            for (int i = topC-1; i >= 1; i--) {
                nArr[i][0] = nArr[i-1][0];
            }
            nArr[1][0] = temp1;

            //아래쪽 공기청정기 -> 시계방향
            temp1 = nArr[botC][C-1];
            for (int i = C-1; i >= 1; i--) {
                nArr[botC][i] = nArr[botC][i-1];
            }
            nArr[botC][1] = 0;

            temp2 = nArr[R-1][C-1];
            for (int i = R-1; i >= botC+1; i--) {
                nArr[i][C-1] = nArr[i-1][C-1];
            }
            nArr[botC+1][C-1] = temp1;

            temp1 = nArr[R-1][0];
            for (int i = 0; i <= C-1-1; i++) {
                nArr[R-1][i] = nArr[R-1][i+1];
            }
            nArr[R-1][C-2] = temp2;

            for (int i = botC+1; i <= R-1-1; i++) {
                nArr[i][0] = nArr[i+1][0];
            }
            nArr[R-2][0] = temp1;

            arr = nArr;
            time++;
        }


        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sum += arr[i][j];
            }
        }

        System.out.println(sum+2);
    }
}