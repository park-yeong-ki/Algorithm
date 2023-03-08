package bj1920;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //자연수 N
        int N = Integer.parseInt(br.readLine());
        //N개의 원소를 가진 정수 배열
        int[] nArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(st.nextToken());
        }

        //자연수 M
        int M = Integer.parseInt(br.readLine());
        //M개의 원소를 가진 정수 배열
        int[] mArr = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            mArr[i] = Integer.parseInt(st.nextToken());
        }

        //이진 탐색 전 오름차순 정렬
        Arrays.sort(nArr);

        //M개의 원소와 비교
        for (int i = 0; i < M; i++) {
            //이진 탐색 구현
            int start = 0;
            int end = N-1;
            boolean flag = false;
            while (start <= end) {
                int middle = (start + end) / 2;

                if (nArr[middle] == mArr[i]) {
                    flag = true;
                    break;
                } else if (nArr[middle] > mArr[i]) {
                    end = middle - 1;
                } else {
                    start = middle + 1;
                }
            }

            //결과 출력
            if (flag) bw.write(1 + "\n");
            else bw.write(0 + "\n");
        }
        bw.close();
    }
}
