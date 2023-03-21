package bj2477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //참외의 개수
        int K = Integer.parseInt(br.readLine());

        // 각 변의 정보 입력
        int[] count = new int[5];
        int[][] side = new int[6][2];
        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            side[i][0] = d;
            side[i][1] = l;

            //방향의 횟수를 체크한다.
            count[d]++;
        }

        //전체 구역과 참외밭이 아닌 구역을 구한다.
        int tArea = 1, nArea = 1;
        for (int i = 0; i < 6; i++) {
            //방향이 한 번만 나온 두 변을 곱하여 총 넓이를 구한다.
            if (count[side[i][0]] == 1) {
                tArea *= side[i][1];
            }
            //방향이 두 번 나온 변을 이용하여 참외밭이 아닌 부분을 구한다.
            else if (count[side[i][0]] == 2) {
                if (count[side[(6 + i - 1) % 6][0]] == 2 && count[side[(i + 1) % 6][0]] == 2) {
                    nArea *= side[i][1];
                }
            }
        }

        //참외밭 넓이
        int area = tArea - nArea;

        //결과 출력
        System.out.println(area * K);
    }
}
