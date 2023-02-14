package bj16926;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        //N, M, R입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        //배열 생성
        int[][] arr = new int[N][M];

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //입력받은 R만큼 반복해준다
        for (int rot = 0; rot < R; rot++) {
            int cnt = 0;
            //cnt변수를 활용하여 안쪽반복을 끝까지 해준다
            while(cnt < N/2 && cnt < M/2) {
                //첫째행을 한칸씩 좌측으로 땡긴다
                int temp1 = arr[cnt][cnt];
                for (int i = 1+cnt; i <= M-2-cnt; i++) {
                    arr[cnt][i-1] = arr[cnt][i];
                }

                //왼쪽열을 한칸씩 아래로 땡긴다
                int temp2 = arr[N-1-cnt][cnt];
                for (int i = N-2-cnt; i >= 1+cnt; i--) {
                    arr[i+1][cnt] = arr[i][cnt];
                }
                arr[1+cnt][cnt] = temp1;

                //마지막행을 한칸씩 우측으로 땡긴다
                temp1 = arr[N-1-cnt][M-1-cnt];
                for (int i = M-2-cnt; i >= 1+cnt; i--) {
                    arr[N-1-cnt][i+1] = arr[N-1-cnt][i];
                }
                arr[N-1-cnt][1+cnt] = temp2;

                //오른쪽열을 한칸씩 위로 땡긴다
                temp2 = arr[cnt][M-1-cnt];
                for (int i = 1+cnt; i <= N-2-cnt; i++) {
                    arr[i-1][M-1-cnt] = arr[i][M-1-cnt];
                }
                arr[N-2-cnt][M-1-cnt] = temp1;

                //첫째행의 빈자리에 입력
                arr[0+cnt][M-2-cnt] = temp2;

                cnt++;
            }
        }

        //배열 출력
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                bw.write(arr[i][j] + " ");
            }
            bw.newLine();
        }

        bw.close();

    }
}