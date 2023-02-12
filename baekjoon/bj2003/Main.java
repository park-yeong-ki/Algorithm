package bj2003;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        //N,M입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //N크기 배열 생성
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //이중반복문을 사용해 안쪽 반복문의 시작점이 1씩 앞으로 증가하도록 설정
        int count = 0;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += arr[j];

                if (sum == M) {
                    count++;
                    break;
                }
            }
        }

        //출력
        bw.write(count + "\n");
        bw.close();

    }
}
