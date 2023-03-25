package bj2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] paper;
    static int blue;
    static int white;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //한 변의 길이 N
        N = Integer.parseInt(br.readLine());

        //색종이
        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        division(0, 0, N);

        //출력
        System.out.println(white);
        System.out.println(blue);
    }

    //4구역으로 분할
    static void division(int r, int c, int size) {

        //주어진 범위의 숫자의 합을 구한다
        int sum = 0;
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                sum += paper[i][j];
            }
        }

        //파란색 색종이인지 확인
        if (sum == size * size) {
            blue++;
            return;
        }

        //하얀색 색종이인지 확인
        if (sum == 0) {
            white++;
            return;
        }

        //1구역
        division(r, c, size / 2);

        //2구역
        division(r, c + size / 2, size / 2);

        //3구역
        division(r + size / 2, c, size / 2);

        //4구역
        division(r + size / 2, c + size / 2, size / 2);
    }
}
