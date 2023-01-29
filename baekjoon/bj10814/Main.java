package bj10814;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader, BufferedWriter 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //회원 수 입력
        int N = Integer.parseInt(br.readLine());

        //입력 받을 배열 생성
        String[][] arr = new String[N][2];

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                arr[i][j] = st.nextToken();
            }
        }

        //나이를 오름차순으로 정렬
        Arrays.sort(arr, Comparator.comparingInt((String[] s) -> Integer.parseInt(s[0])));


        //출력
        for (int i = 0; i < N; i++) {
            bw.write(arr[i][0] + " " + arr[i][1] + "\n");
        }
        bw.flush();
    }
}
