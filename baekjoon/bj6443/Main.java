package bj6443;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Main {
    static char[] cArr;
    static char[] result;
    static boolean[] isSelected;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            input();
            perm(0);
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        cArr = br.readLine().toCharArray();
        Arrays.sort(cArr);
        result = new char[cArr.length];
        isSelected = new boolean[cArr.length];
    }

    static void perm(int idx) {
        if (idx == cArr.length) {
            for (int i = 0; i < result.length; i++) {
                sb.append(result[i]);
            }
            sb.append("\n");
            return;
        }

        char temp = ' ';
        for (int i = 0; i < cArr.length; i++) {
            if (isSelected[i] || temp == cArr[i]) continue;
            isSelected[i] = true;
            result[idx] = cArr[i];
            temp = result[idx];
            perm(idx + 1);
            isSelected[i] = false;
        }
    }
}
