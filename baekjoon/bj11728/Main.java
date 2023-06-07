package bj11728;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nArr = new int[N];
        int[] mArr = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            mArr[i] = Integer.parseInt(st.nextToken());
        }

        int nIdx = 0;
        int mIdx = 0;
        while (nIdx < N && mIdx < M) {
            if (nArr[nIdx] <= mArr[mIdx]) {
                bw.write(nArr[nIdx] + " ");
                nIdx++;
            } else {
                bw.write(mArr[mIdx] + " ");
                mIdx++;
            }
        }

        for (int i = nIdx; i < N; i++) {
            bw.write(nArr[i] + " ");
        }
        for (int i = mIdx; i < M; i++) {
            bw.write(mArr[i] + " ");
        }
        bw.newLine();

        bw.close();
    }
}
