package bj17406;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int K;
    static int[][] rcs;
    static boolean[] isSelected;
    static int[][] permRcs;
    static int min = Integer.MAX_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] arr;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        //N,M,K 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //배열생성 및 요소 입력
        arr = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //회전 연산의 정보 입력
        rcs = new int[K][3];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rcs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //연산 순서 순열
        isSelected = new boolean[K];
        permRcs = new int[K][3];

        //재귀함수 사용
        perm(0);

        //출력
        bw.write(min + "\n");
        bw.close();
    }

    //재귀함수 생성 -> 연산 순서 순열
    public static void perm(int cnt) throws IOException {
        //기저조건
        if (cnt == K) {

            //기존 배열 복사
            int[][] nArr = new int[N+1][M+1];
            for (int i = 0; i < nArr.length; i++) {
                nArr[i] = arr[i].clone();
            }

            //연산 수행
            //연산의 개수 만큼인 K번 반복
            for (int i = 0; i < K; i++) {
                //x1좌표
                int x1 = permRcs[i][0] - permRcs[i][2];
                //y1좌표
                int y1 = permRcs[i][1] - permRcs[i][2];
                //x2좌표
                int x2 = permRcs[i][0] + permRcs[i][2];
                //y2좌표
                int y2 = permRcs[i][1] + permRcs[i][2];

                int m = 0;
                while(m < (x2-x1+1)/2 && m < (y2-y1+1)/2) {
                    //가장 윗행 이동하기
                    //우측 상단 모서리 부분 임시값 저장
                    int temp1 = arr[x1+m][y2-m];
                    for (int j = y2-1-m; j >= y1+m; j--) {
                        arr[x1+m][j+1] = arr[x1+m][j];
                    }

                    //가장 우측열 이동하기
                    //우측 하단 모서리 부분 임시값 저장
                    int temp2 = arr[x2-m][y2-m];
                    for (int j = x2-1-m; j >= x1+1+m; j--) {
                        arr[j+1][y2-m] = arr[j][y2-m];
                    }
                    //빈 공간에 임시 값 붙이기
                    arr[x1+1+m][y2-m] = temp1;


                    //가장 아랫행 이동하기
                    //좌측 하단 모서리 부분 임시값 저장
                    temp1 = arr[x2-m][y1+m];
                    for (int j = y1+1+m; j <= y2-1-m; j++) {
                        arr[x2-m][j-1] = arr[x2-m][j];
                    }
                    //빈 공간에 임시 값 붙이기
                    arr[x2-m][y2-1-m] = temp2;

                    //가장 왼쪽열 이동하기
                    //좌측 상단 모서리 부분 임시값 저장
                    temp2 = arr[x1+m][y1+m];
                    for (int j = x1+1+m; j <= x2-1-m; j++) {
                        arr[j-1][y1+m] = arr[j][y1+m];
                    }
                    //빈 공간에 임시 값 붙이기
                    arr[x2-1-m][y1+m] = temp1;

                    //가장 윗행 빈 공간에 임시 값 붙이기
                    arr[x1+m][y1+1+m] = temp2;

                    //m은 1씩 증가
                    m++;
                }
            }

            //각 행의 합을 구한 후 최소값 출력
            for (int i = 1; i <= N; i++) {
                int sum = 0;
                for (int j = 1; j <= M; j++) {
                    sum += arr[i][j];
                }
                min = Math.min(min, sum);
            }

            //원본 배열 되돌리기
            arr = nArr;

            return;
        }

        //유도부분
        for (int i = 0; i < K; i++) {
            if (isSelected[i]) continue;
            permRcs[cnt] = rcs[i];
            isSelected[i] = true;
            perm(cnt+1);
            isSelected[i] = false;
        }
    }
}