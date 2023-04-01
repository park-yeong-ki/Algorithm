package bj2448;

import java.io.*;
import java.util.Arrays;

public class Main {
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int H = N;
        int W = 2*N;
        arr = new char[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(arr[i], ' ');
        }

        star(0,0, H, W);

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                bw.write(arr[i][j]);
            }
            bw.newLine();
        }
        bw.close();
    }

    static void star(int r, int c, int hSize, int wSize) {
        if (hSize == 3 && wSize == 6) {
            for (int i = r; i < r + hSize; i++) {
                for (int j = c; j < c + wSize; j++) {
                    if (i == r){
                        if (j == c+2) arr[i][j] = '*';
                    }
                    if (i == r + 1) {
                        if (j == c+1 || j == c+3) arr[i][j] = '*';
                    }
                    if (i == r + 2) {
                        if (j != c+wSize-1) arr[i][j] = '*';
                    }
                }
            }
            return;
        }

        star(r, c + wSize / 4, hSize / 2, wSize / 2);
        star(r + hSize / 2, c, hSize / 2, wSize / 2);
        star(r + hSize / 2, c + wSize / 2, hSize / 2, wSize / 2);
    }
}
