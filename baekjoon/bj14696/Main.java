package bj14696;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //별, 동그라미, 네모, 세모, 다같으면 무승부
        int N = Integer.parseInt(br.readLine());

        //N번 반복
        for (int i = 0; i < N; i++) {
            //각 도형의 개수를 카운트 배열로 저장한다
            int[] aArr = new int[5];
            int[] bArr = new int[5];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            for (int j = 0; j < a; j++) {
                int num = Integer.parseInt(st.nextToken());
                aArr[num]++;
            }

            st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken());
            for (int j = 0; j < b; j++) {
                int num = Integer.parseInt(st.nextToken());
                bArr[num]++;
            }

            //도형의 개수를 별, 동그라미, 네모, 세모 순으로 비교한다
            for (int j = 4; j >= 1; j--) {
                //A의 개수가 큰 경우
                if (aArr[j] > bArr[j]) {
                    bw.write("A\n");
                    break;
                }
                //B의 개수가 큰 경우
                else if (aArr[j] < bArr[j]) {
                    bw.write("B\n");
                    break;
                }
                //A, B의 개수가 같은 경우
                else {
                    if (j == 1) {
                        bw.write("D\n");
                    }
                }
            }

        }
        bw.close();
    }
}
