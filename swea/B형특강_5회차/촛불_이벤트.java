package B형특강_5회차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 촛불_이벤트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            long N = Long.parseLong(br.readLine());

            long start = 1;
            long end = (long) Math.pow(10, 10);
            while (start <= end) {
                long K = (start + end) / 2;

                if (K * (K + 1) / 2 > N) {
                    end = K - 1;
                } else {
                    start = K + 1;
                }
            }

            if (end*(end+1)/2 != N) end = -1;

            sb.append("#" + test_case + " " + end + "\n");
        }
        System.out.print(sb);
    }
}
