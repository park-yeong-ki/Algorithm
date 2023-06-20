package B형특강_1회차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 새로운_불면증_치료법 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int cnt = 1;
            int[] arr = new int[10];
            while (true) {
                String str = String.valueOf(N * cnt);
                for (int i = 0; i < str.length(); i++) {
                    arr[str.charAt(i) - '0']++;
                }

                boolean flag = true;
                for (int i = 0; i < 10; i++) {
                    if (arr[i] == 0) {
                        flag = false;
                        break;
                    }
                }

                if (flag) break;

                cnt++;
            }
            System.out.println("#" + test_case + " " + N * cnt);
        }
    }
}
