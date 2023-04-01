package bj2447;

import java.io.*;
import java.util.Arrays;

public class Main {
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        arr = new char[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(arr[i], ' ');
        }

        star(0, 0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(arr[i][j]);
            }
            bw.newLine();
        }
        bw.close();
    }

    static void star(int r, int c, int size) {
        if (size == 3) {
            for (int i = r; i < r + size; i++) {
                for (int j = c; j < c + size; j++) {
                    if (i == r+1 && j == c+1) continue;
                    else arr[i][j] = '*';
                }
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) continue;
                else star(r + i * size / 3, c + j * size / 3, size / 3);
            }
        }
    }
}
