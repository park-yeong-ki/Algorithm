package bj1026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        //A배열
        int[] aArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            aArr[i] = Integer.parseInt(st.nextToken());
        }

        //B배열
        st = new StringTokenizer(br.readLine());
        Integer[] bArr = new Integer[N];
        for (int i = 0; i < N; i++) {
            bArr[i] = Integer.parseInt(st.nextToken());
        }

        //각 배열에서 최대한 작은 값과 최대한 큰 값을 서로 곱해서 더해서 최솟값을 구한다.

        //오름차순 정렬
        Arrays.sort(aArr);
        //내림차순 정렬
        Arrays.sort(bArr, Comparator.reverseOrder());

        //연산
        int result = 0;
        for (int i = 0; i < N; i++) {
            result += aArr[i] * bArr[i];
        }

        //출력
        System.out.println(result);
    }
}
