package bj6987;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int[][] arr = new int[6][3];
    static int[][] result = new int[6][3];
    static char[] team1 = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 9}; //9는 ArrayIndexOutOfBoundsException 방지용
    static char[] team2 = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5, 9}; //9는 ArrayIndexOutOfBoundsException 방지용
    static int count = 0;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //네 가지 결과이므로 4번 반복한다.
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            //각 국가별 승무패로 분리
            for (int country = 0; country < 6; country++) {
                for (int wtl = 0; wtl < 3; wtl++) {
                    arr[country][wtl] = Integer.parseInt(st.nextToken());
                }
            }

            //재귀함수 사용
            worldcup(team1[0], team2[0], 0);

            //출력
            if (isPossible) bw.write(1 + " ");
            else bw.write(0 + " ");

            //결과 출력 후 플래그 초기화
            isPossible = false;
        }

        bw.close();
    }

    //경기 별 승무패를 정해줄 재귀함수 생성
    static void worldcup(int t1, int t2, int count) throws IOException {
        //이미 가능하다고 결론이 나온경우는 종료
        if (isPossible) {
            return;
        }

        //기저조건 -> 6c2는 15이므로 15경기를 마치면 종료
        if (15 == count) {

            //주어진 결과에 대해서 검사
            isPossible = true;
            outer:
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (result[i][j] != arr[i][j]) {
                        isPossible = false;
                        break outer;
                    }
                }
            }

            return;
        }

        //유도부분
        //이긴경우
        if(result[t1][0] < arr[t1][0] && result[t2][2] < arr[t2][2]) {
            result[t1][0]++; //t1 1승 표시
            result[t2][2]++; //t2 1패 표시
            worldcup(team1[count+1], team2[count+1], count+1);
            result[t1][0]--; //재귀를 마친 후 초기화
            result[t2][2]--; //재귀를 마친 후 초기화
        }

        //비긴경우
        if (result[t1][1] < arr[t1][1] && result[t2][1] < arr[t2][1]) {
            result[t1][1]++; //t1 1무 표시
            result[t2][1]++; //t2 1무 표시
            worldcup(team1[count+1], team2[count+1], count+1);
            result[t1][1]--; //재귀를 마친 후 초기화
            result[t2][1]--; //재귀를 마친 후 초기화
        }

        //진경우
        if (result[t1][2] < arr[t1][2] && result[t2][0] < arr[t2][0]) {
            result[t1][2]++; //t1 1패 표시
            result[t2][0]++; //t2 1승 표시
            worldcup(team1[count+1], team2[count+1], count+1);
            result[t1][2]--; //재귀를 마친 후 초기화
            result[t2][0]--; //재귀를 마친 후 초기화
        }
    }
}