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
        checkRow();
        checkCol();
        System.out.println(street);
    }
    static void checkRow() {
        for (int i = 0; i < N; i++) {
            int h = arr[i][0];
            int cnt = 1;
            boolean flag = true;
            for (int j = 1; j < N; j++) {
                if (h == arr[i][j]) { //다음 칸과 높이가 같은 경우
                    cnt++;
                } else {
                    int down = 0;
                    if (arr[i][j] - h == 1) { //다음 칸이 1만큼 큰 경우
                        if (cnt < L) { //경사로를 놓을 칸이 되지 않는 경우
                            flag = false;
                            break;
                        }
                    } else if (arr[i][j] - h == -1) { //다음 칸이 1만큼 작은 경우
                        if (cnt < 0) {
                            flag = false;
                            break;
                        }
                        down = L;
                    } else if (Math.abs(arr[i][j] - h) > 1) { //칸의 높이차이가 1보다 큰 경우
                        flag = false;
                        break;
                    }
                    h = arr[i][j];
                    cnt = 1 - down;
                }
            }
            if (cnt >= 0 && flag) street++;
        }
    }
    static void checkCol() {
        for (int i = 0; i < N; i++) {
            int h = arr[0][i];
            int cnt = 1;
            boolean flag = true;
            for (int j = 1; j < N; j++) {
                if (h == arr[j][i]) { //다음 칸과 높이가 같은 경우
                    cnt++;
                } else {
                    int down = 0;
                    if (arr[j][i] - h == 1) { //다음 칸이 1만큼 큰 경우
                        if (cnt < L) { //경사로를 놓을 칸이 되지 않는 경우
                            flag = false;
                            break;
                        }
                    } else if (arr[j][i] - h == -1) { //다음 칸이 1만큼 작은 경우
                        if (cnt < 0) {
                            flag = false;
                            break;
                        }
                        down = L;
                    } else if (Math.abs(arr[j][i] - h) > 1) { //칸의 높이차이가 1보다 큰 경우
                        flag = false;
                        break;
                    }
                    h = arr[j][i];
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
