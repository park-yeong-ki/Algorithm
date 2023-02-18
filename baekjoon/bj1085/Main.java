package bj1085;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        //주어진 좌표에서 직사각형의 경계선까지 거리의 최소값을 구한다.
        int num1 = Math.min(Math.abs(x - w), x);
        int num2 = Math.min(Math.abs(y - h), y);

        //출력
        bw.write(Math.min(num1, num2) + "\n");
        bw.close();
    }
}
