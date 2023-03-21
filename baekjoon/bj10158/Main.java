package bj10158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //크기 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        //초기 위치 입력
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        //움직일 시간 입력
        int t = Integer.parseInt(br.readLine());

        //가로 길이, 세로 길이 2배의 시간만큼 이동하면 각 좌표의 위치 변동이 없다 -> 반복주기 활용
        int dp = t % (2 * w);
        int dq = t % (2 * h);

        int x = 1;
        for (int i = 0; i < dp; i++) {
            //p가 벽에 부딪힐 경우 방향을 바꿔준다.
            if (p == w || p == 0) x *= -1;
            p += x;
        }

        int y = 1;
        for (int i = 0; i < dq; i++) {
            //q가 벽에 부딪힐 경우 방향을 바꿔준다.
            if (q == h || q == 0) y *= -1;
            q += y;
        }

        //출력
        System.out.println(p + " " + q);
    }
}
