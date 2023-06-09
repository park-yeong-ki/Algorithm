package bj11723;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int M = Integer.parseInt(br.readLine());

        int bit = 0;

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            int num = 0;
            if (st.hasMoreTokens()) num = Integer.parseInt(st.nextToken());
            switch (str) {
                case "add":
                    bit |= (1 << num);
                    break;
                case "remove":
                    bit &= ~(1 << num);
                    break;
                case "check":
                    if ((bit & (1 << num)) != 0) bw.write(1 + "\n");
                    else bw.write(0 + "\n");
                    break;
                case "toggle":
                    if ((bit & (1 << num)) != 0) bit &= ~(1 << num);
                    else bit |= (1 << num);
                    break;
                case "all":
                    bit |= ~0;
                    break;
                case "empty":
                    bit &= 0;
                    break;
            }
        }

        bw.close();
    }
}
