package bj1759;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static char[] alphabet;
    static int L;
    static int C;
    static char[] result;
    static char[] vowel;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //L, C입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        //알파벳 소문자 배열
        vowel = new char[]{'a', 'e', 'i', 'o', 'u'};

        //알파벳입력
        alphabet = br.readLine().replace(" ", "").toCharArray();
        //알파벳 정렬
        Arrays.sort(alphabet);

        //재귀함수 사용
        result = new char[L];
        comb(0, 0);

        bw.close();
    }

    static void comb(int start, int idx) throws IOException {
        //기저조건
        if (idx == L) {
            //모음 개수 세기
            int count = 0;
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < vowel.length; j++) {
                    if (result[i] == vowel[j]) {
                        count++;
                    }
                }
            }

            //모음이 1개이상이고 자음은 2개 이상이면 출력
            if (count >= 1 && result.length - count >= 2) {
                for (int i = 0; i < L; i++) {
                    bw.write(result[i]);
                }
                bw.newLine();
            }

            return;
        }

        //유도부분 -> 조합
        for (int i = start; i < C; i++) {
            result[idx] = alphabet[i];
            comb(i+1, idx+1);
        }
    }
}
