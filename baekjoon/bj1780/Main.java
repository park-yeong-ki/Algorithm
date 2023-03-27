package bj1780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int cnt1;
    static int cnt2;
    static int cnt3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, N);

        //출략
        System.out.println(cnt1);
        System.out.println(cnt2);
        System.out.println(cnt3);
    }

    static void divide(int r, int c, int size) {
        //원소가 모두 같은지 확인하며 합을 구한다
        int sum = 0;
        boolean isSame = true;
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (arr[r][c] != arr[i][j]){
                    isSame = false;
                    break;
                }
                sum += arr[i][j];
            }
            if (!isSame) break;
        }

        //모두 -1인 경우
        if (isSame && sum == -1 * size * size) {
            cnt1++;
            return;
        }

        //모두 0인 경우
        if (isSame && sum == 0) {
            cnt2++;
            return;
        }

        //모두 1인 경우
        if (isSame && sum == size * size) {
            cnt3++;
            return;
        }

        //9영역으로 나누어 위 조건에 해당할때까지 재귀함수 실행
        int nSize = size / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                divide(r + i * nSize, c + j * nSize, nSize);
            }
        }
    }
}
