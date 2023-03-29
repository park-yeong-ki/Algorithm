package bj1343;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] cArr = br.readLine().toCharArray();

        int xCount = 0;
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] == 'X') {
                xCount++;
            } else {
                int aNum = xCount / 4;
                xCount %= 4;
                for (int j = 0; j < aNum; j++) {
                    sb.append("AAAA");
                }

                int bNum = xCount / 2;
                xCount %= 2;
                for (int j = 0; j < bNum; j++) {
                    sb.append("BB");
                }

                if (xCount != 0) {
                    flag = false;
                    break;
                }

                sb.append(".");
            }
        }
        if (flag) {
            int aNum = xCount / 4;
            xCount %= 4;
            for (int j = 0; j < aNum; j++) {
                sb.append("AAAA");
            }

            int bNum = xCount / 2;
            xCount %= 2;
            for (int j = 0; j < bNum; j++) {
                sb.append("BB");
            }

            if (xCount != 0) {
                flag = false;
            }
        }

        if (flag) System.out.println(sb);
        else System.out.println(-1);
    }
}
