package bj20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;
    static int[] arr;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        input();
        int result = 1;
        while (true) {
            beltMove();
            robotMove();
            loadRobot();
            if (isEnd()) break;
            else result++;
        }
        System.out.println(result);
    }

    static void beltMove() {
        int temp = arr[2 * N];
        for (int i = 2 * N; i >= N+1; i--) {
            arr[i] = arr[i-1];
        }
        for (int i = N; i >= 2; i--) {
            arr[i] = arr[i - 1];
            robot[i] = robot[i - 1];
        }
        arr[1] = temp;
        robot[1] = false;
        robot[N] = false;
    }

    static void robotMove() {
        for (int i = N-1; i >= 2; i--) {
            if (robot[i]) {
                if (arr[i + 1] >= 1 && !robot[i + 1]) {
                    robot[i + 1] = robot[i];
                    arr[i + 1]--;
                    robot[i] = false;
                }
            }
        }
        robot[N] = false;
    }

    static void loadRobot() {
        if (arr[1] > 0) {
            robot[1] = true;
            arr[1]--;
        }
    }

    static boolean isEnd() {
        int cnt = 0;
        for (int i = 1; i <= 2 * N; i++) {
            if (arr[i] == 0) cnt++;
        }

        if (cnt >= K) return true;
        else return false;
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[2 * N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2 * N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        robot = new boolean[N + 1];
    }
}