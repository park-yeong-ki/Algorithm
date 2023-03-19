package bj13300;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        //학생 수, 최대 인원 수
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //1학년부터 6학년까지 성별로 구분하여 저장
        int[][] students = new int[7][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int grade = Integer.parseInt(st.nextToken());

            students[grade][gender]++;
        }

        //필요한 방의 수를 센다
        int room = 0;
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 2; j++) {
                if (students[i][j] == 0) continue;
                room += (students[i][j] / K) + (students[i][j] % K != 0 ? 1 : 0);
            }
        }

        //출력
        System.out.println(room);
    }
}
