package bj10163;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //색종이가 놓이는 평면
        int[][] paper = new int[1001][1001];

        //색종이의 장수를 나타내는 정수 N
        int N = Integer.parseInt(br.readLine());

        //색종이 붙이기
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dx = Integer.parseInt(st.nextToken());
            int dy = Integer.parseInt(st.nextToken());

            for (int j = y + dy - 1; j >= y; j--) {
                for (int k = x; k < x + dx; k++) {
                    paper[paper.length - 1 - j][k] = i;
                }
            }
        }

        //색종이 개수를 세면 면적을 구할 수 있다.
        int[] count = new int[N+1];
        for (int i = 0; i < paper.length; i++) {
            for (int j = 0; j < paper[i].length; j++) {
                count[paper[i][j]]++;
            }
        }

        //출력
        for (int i = 1; i <= N; i++) {
            System.out.println(count[i]);
        }
    }
}
