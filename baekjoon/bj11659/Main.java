package bj11659;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        //N, M 입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //길이가 N+1인 배열 생성 -> 1을 시작하는 행으로 여기고 0행은 사용하지 않음(초기값 0)
        int[] nArr = new int[N+1];

        st = new StringTokenizer(br.readLine());

        //nArr 요소 입력 -> 누적합으로 입력한다.
        nArr[1] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            nArr[i+1] = nArr[i] + Integer.parseInt(st.nextToken());
        }

        //Mx2 행렬 생성
        int[][] mArr = new int[M][2];

        //mArr 요소 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            mArr[i][0] = Integer.parseInt(st.nextToken());
            mArr[i][1] = Integer.parseInt(st.nextToken());
        }

        //mArr 각 행의 두번째 열과 첫번째 열의 값-1을 nArr 인덱스로 설정하여 차이를 구한다.
        for (int i = 0; i < M; i++) {
            //해당 값들을 다 포함해야 함으로 mArr[i][0]에 -1을 해준다
            bw.write(nArr[mArr[i][1]]-nArr[mArr[i][0]-1] + "\n");
        }
        bw.close();

    }
}