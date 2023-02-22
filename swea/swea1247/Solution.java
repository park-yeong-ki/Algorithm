package swea1247;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    //전역변수 설정
    static int N;
    static int[][] office, home, customer;
    static int[][] pArr;
    static boolean[] isSelected;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //테스트 케이스 수 입력
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            //고객의 수 입력
            N = Integer.parseInt(br.readLine());

            //좌표 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            office = new int[1][2];
            home = new int[1][2];
            customer = new int[N][2];

            //회사, 집 주소
            office[0][0] = Integer.parseInt(st.nextToken());
            office[0][1] = Integer.parseInt(st.nextToken());
            home[0][0] = Integer.parseInt(st.nextToken());
            home[0][1] = Integer.parseInt(st.nextToken());
            //손님 주소
            for (int i = 0; i < N; i++) {
                customer[i][0] = Integer.parseInt(st.nextToken());
                customer[i][1] = Integer.parseInt(st.nextToken());
            }

            //재귀함수 사용
            pArr = new int[N][2];
            isSelected = new boolean[N];
            perm(0);

            //가장 짧은 경로의 이동거리 출력
            bw.write("#" + test_case + " " + min + "\n");
            //최소값 초기화
            min = Integer.MAX_VALUE;
        }

        bw.close();

    }

    //손님들의 좌표를 순열로 구현후 고정된 회사와 집의 위치까지의 최단경로 이동거리를 구한다.
    static void perm(int idx) throws IOException {
        //기저조건
        if (idx == N) {
            //이동거리 구하기
            //회사에서 첫번째 손님까지의 거리
            int sum = Math.abs(office[0][0] - pArr[0][0]) +
                    Math.abs(office[0][1] - pArr[0][1]);

            //첫번째 손님부터 마지막 손님까지의 거리
            for (int i = 0; i < N-1; i++) {
                sum += Math.abs(pArr[i][0] - pArr[i+1][0]) +
                        Math.abs(pArr[i][1] - pArr[i+1][1]);
            }

            //마지막 손님에서 집까지의 거리
            sum += Math.abs(home[0][0] - pArr[N-1][0]) +
                    Math.abs(home[0][1] - pArr[N-1][1]);

            //최소값인지 확인 후 갱신
            min = Math.min(min, sum);

            return;
        }

        //유도부분
        for (int i = 0; i < N; i++) {
            if(isSelected[i]) continue;
            pArr[idx] = customer[i];
            isSelected[i] = true;
            perm(idx + 1);
            isSelected[i] = false;
        }
    }
}