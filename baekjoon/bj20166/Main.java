package bj20166;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int N, M, T;
    static char[][] map;
    static String[] sArr;
    static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        sArr = new String[K];
        for (int i = 0; i < K; i++) {
            sArr[i] = br.readLine();
        }
        count = new int[K];

        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int l = 0; l < K; l++) {
            T = l;
            if (!hashMap.containsKey(sArr[l])) {
                hashMap.put(sArr[l], 0);
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        dfs(i, j, 0);
                    }
                }
                hashMap.put(sArr[l], count[l]);
            } else {
                count[l] = hashMap.get(sArr[l]);
            }
        }

        for (int i = 0; i < K; i++) {
            bw.write(count[i]+"\n");
        }
        bw.close();
    }

    static void dfs(int r, int c, int idx) {
        if (map[r][c] != sArr[T].charAt(idx)) {
            return;
        }

        if (idx == sArr[T].length()-1) {
            count[T]++;
            return;
        }

        for (int i = 0; i < 8; i++) {
            int tR = r + dr[i];
            int tC = c + dc[i];

            if (tR < 0) tR = N - 1;
            else if (tR >= N) tR = 0;
            if (tC < 0) tC = M - 1;
            else if (tC >= M) tC = 0;

            dfs(tR, tC, idx + 1);
        }
    }
}
