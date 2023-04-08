package bj17136;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int result = -1;
    static int min = Integer.MAX_VALUE;
    static int[] count = new int[6];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[10][10];
        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 5; i >= 1; i--) {
            count[i]++;
            dfs(0, i);
            count[i]--;
        }

        System.out.println(result);
    }

    static void dfs(int depth, int size) {
        if (min <= depth) {
            return;
        }

        //1 찾기
        int r = -1;
        int c = -1;
        boolean flag = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (arr[i][j] == 1) {
                    r = i;
                    c = j;
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }

        //1을 못찾으면 색종이 붙이기 완료된 경우
        if (!flag){
            result = depth;
            min = Math.min(min, depth);
            return;
        }

        //각 색종이 개수가 5개 넘어가면 리턴
        for (int i = 1; i <= 5; i++) {
            if (count[i] > 5) {
                return;
            }
        }

        //주어진 크기가 되는지 검사
        if (r + size > 10 || c + size > 10) {
            return;
        }
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (arr[i][j] != 1) {
                    return;
                }
            }
        }

        //5->1로 내려가면서 백트래킹
        for (int s = 5; s >= 1; s--) {
            for (int i = r; i < r + size; i++) {
                for (int j = c; j < c + size; j++) {
                    arr[i][j] = 0;
                }
            }
            count[s]++;
            dfs(depth + 1, s);
            count[s]--;
            for (int i = r; i < r + size; i++) {
                for (int j = c; j < c + size; j++) {
                    arr[i][j] = 1;
                }
            }
        }
    }
}
