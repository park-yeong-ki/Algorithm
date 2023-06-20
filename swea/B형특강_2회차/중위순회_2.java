package B형특강_2회차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 중위순회_2 {
    static char[] alphabet;
    static StringBuilder sb = new StringBuilder();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int test_case = 1; test_case <= 10; test_case++) {
            sb.append("#").append(test_case).append(" ");
            N = Integer.parseInt(br.readLine());
            alphabet = new char[N + 1];
            for (int i = 1; i <= N; i++) {
                alphabet[i] = br.readLine().split(" ")[1].charAt(0);
            }

            dfs(1);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int num) {
        if (num > N) return;

        dfs(2 * num);
        sb.append(alphabet[num]);
        dfs(2 * num + 1);
    }
}
