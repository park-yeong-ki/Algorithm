package bj1620;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, String> numKey = new HashMap<>();
        HashMap<String, String> nameKey = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            numKey.put(String.valueOf(i), str);
            nameKey.put(str, String.valueOf(i));
        }

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            if (numKey.containsKey(str)) {
                bw.write(numKey.get(str) + "\n");
            } else {
                bw.write(nameKey.get(str) + "\n");
            }
        }
        bw.close();
    }
}
