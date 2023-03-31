package swea1952;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int[] price;
    static int[] day;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            price = new int[4];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                price[i] = Integer.parseInt(st.nextToken());
            }

            day = new int[12];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) {
                day[i] = Integer.parseInt(st.nextToken());
            }

            min = price[3];
            calculate(0, 0);

            bw.write("#" + test_case + " " + min + "\n");
        }
        bw.close();
    }

    static void calculate(int cnt, int sum){
        if (sum >= min) {
            return;
        }

        if (cnt == 12) {
            min = Math.min(min, sum);
            return;
        }

        calculate(cnt + 1, sum + price[0] * day[cnt]);
        calculate(cnt + 1, sum + price[1]);
        if (cnt + 3 <= 12) {
            calculate(cnt + 3, sum + price[2]);
        }
    }
}
