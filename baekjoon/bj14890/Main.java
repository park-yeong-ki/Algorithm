package bj14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int L;
    static int[][] arr;
    static int street;
    public static void main(String[] args) throws IOException {
        input();
        check("row");
        check("col");
        System.out.println(street);
    }
    static void check(String dir) {
        for (int i = 0; i < N; i++) {
            int h = dir.equals("row") ? arr[i][0] : arr[0][i];
            int cnt = 1;
            boolean flag = true;
            for (int j = 1; j < N; j++) {
                int next = dir.equals("row") ? arr[i][j] : arr[j][i];
                if (h == next) { //다음 칸과 높이가 같은 경우
                    cnt++;
                } else {
                    int down = 0;
                    if (next - h == 1) { //다음 칸이 1만큼 큰 경우
                        if (cnt < L) { //경사로를 놓을 칸이 되지 않는 경우
                            flag = false;
                            break;
                        }
                    } else if (next - h == -1) { //다음 칸이 1만큼 작은 경우
                        if (cnt < 0) {
                            flag = false;
                            break;
                        }
                        down = L;
                    } else if (Math.abs(next - h) > 1) { //칸의 높이차이가 1보다 큰 경우
                        flag = false;
                        break;
                    }
                    h = next;
                    cnt = 1 - down;
                }
            }
            if (cnt >= 0 && flag) street++;
        }
    }
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
