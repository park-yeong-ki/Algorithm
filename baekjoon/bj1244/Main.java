package bj1244;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader, BufferedWriter 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //스위치 개수 입력
        int N = Integer.parseInt(br.readLine());

        //스위치 배열 생성
        int[] swi = new int[N];

        //스위치 배열 요소 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            swi[i] = Integer.parseInt(st.nextToken());
        }

        //학생 수 입력
        int S = Integer.parseInt(br.readLine());

        //학생 배열 생성
        int[][] students = new int[S][2];

        //학생 배열 입력
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            students[i][0] = Integer.parseInt(st.nextToken());
            students[i][1] = Integer.parseInt(st.nextToken());
        }

        //학생 수만큼 반복한다
        for (int i = 0; i < S; i++) {
            //남학생인 경우
            if (students[i][0] == 1) {
                //스위치 개수만큼 반복한다
                for (int j = 0; j < N; j++) {
                    //스위치가 남학생이 받은 수의 배수일 경우 스위치의 상태를 바꾼다.
                    if ((j + 1) % students[i][1] == 0) {
                        swi[j] = swi[j] == 1 ? 0 : 1;
                    }
                }
            }
            //여학생인 경우
            else {
                //여학생이 받은 숫자 위치의 스위치 상태를 바꾼다.
                swi[students[i][1]-1] = swi[students[i][1]-1] == 1 ? 0 : 1;

                //대칭되는 숫자가 스위치 범위안에 들어올 경우 대칭을 검사할 수 있는 반복문 실행
                int j = 1;
                while (students[i][1] - 1 - j >= 0 && students[i][1] - 1 + j < N) {
                    //대칭이 아닐 경우 반복문을 멈춘다.
                    if (swi[students[i][1] - 1 - j] != swi[students[i][1] - 1 + j]) {
                        break;
                    }
                    //대칭일 경우 대칭인 스위치의 상태를 모두 변경
                    swi[students[i][1] - 1 - j] = swi[students[i][1] - 1 - j] == 1 ? 0 : 1;
                    swi[students[i][1] - 1 + j] = swi[students[i][1] - 1 - j];
                    j++;
                }
            }
        }

        //스위치의 개수만큼 반복하여 한줄에 20개씩 출력한다.
        for (int j = 0; j < N; j++) {
            if (j != 0 && j % 20 == 0) {
                bw.newLine();
            }
            bw.write(swi[j] + " ");
        }
        bw.close();
    }
}
