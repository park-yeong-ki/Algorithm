package bj10973;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //N입력
        int N = Integer.parseInt(br.readLine());

        //순열 입력
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //NextPermutation 응용 -> 역으로 생각한다.
        boolean result = true;

        //뒤에서부터 앞의 자리보다 작은 값을 꼭대기로 설정한다.
        int i = N - 1;
        while (i>0 && arr[i-1] <= arr[i]) --i;
        if (i == 0) {
            result = false;
        }

        //꼭대기가 존재하는지에 따라 구분
        if (result) {
            //꼭대기 앞의 위치에 있는 숫자와 바꿀 숫자를 뒤에서부터 찾는다 -> 꼭대기 앞의 위치에 있는 숫자보다 조금 작은 숫자를 찾는다
            int j = N - 1;
            while (arr[i - 1] <= arr[j]) --j;

            //숫자를 바꾼다
            swap(arr, i - 1, j);

            //바꾼 숫자 다음 자리(꼭대기)부터는 내림차순 정리한다 -> 꼭대기부터 끝까지는 오름차순이므로 대칭적으로 자리만 바꾸면 된다.
            int k = N - 1;
            while (i < k) {
                swap(arr, i++, k--);
            }

            //출력
            for (int l = 0; l < arr.length; l++) {
                bw.write(arr[l] + " ");
            }
            bw.newLine();
        } else {
            //출력
            bw.write(-1 + "\n");
        }
        bw.close();
    }
    //자리를 바꿔줄 함수
    private static void swap(int[] arr, int current, int change) {
        int temp = arr[current];
        arr[current] = arr[change];
        arr[change] = temp;
    }
}
