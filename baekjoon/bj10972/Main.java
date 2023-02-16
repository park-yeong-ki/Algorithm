package bj10972;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //N입력
        int N  = Integer.parseInt(br.readLine());

        //순열을 입력받는다
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean hasValue = true;

        //NextPermutaion
        //뒤쪽부터 꼭대기를 찾는다
        int i = N-1;
        while (i>0 && arr[i-1] >= arr[i]) --i;
        if (i == 0) hasValue = false;

        if (hasValue) {
            //꼭대기 바로 앞자리에 교환할 값을 뒤쪽부터 찾는다
            int j = N-1;
            while (arr[i-1]>=arr[j]) --j;

            //꼭대기 바로 앞자리와 그 자리값보다 한 단계 큰 자리수를 교환
            swap(arr, i-1, j);

            //꼭대기부터 맨 뒤까지 오름차순으로 정렬
            int k = N-1;
            while (i < k) {
                swap(arr, i++, k--);
            }

            //출력
            for (int k2 = 0; k2 < arr.length; k2++) {
                bw.write(arr[k2] + " ");
            }

        } else {
            //출력
            bw.write(-1 + " ");
        }

        bw.newLine();
        bw.close();
    }

    //자리를 바꿔주는 함수
    private static void swap(int[] arr, int tV, int cV) {
        int temp = arr[tV];
        arr[tV] = arr[cV];
        arr[cV] = temp;
    }
}