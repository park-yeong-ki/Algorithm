package bj24060;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] tmp;
    static int[] A;
    static int cnt;
    static int K;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        //배열 A의 크기 N(5 ≤ N ≤ 500,000), 저장 횟수 K(1 ≤ K ≤ 108)
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //배열 입력
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        //임시저장배열
        tmp = new int[N];

        mergeSort(0, N - 1);

        //출력
        System.out.println(result);
    }

    static void mergeSort(int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            //전반부 나눔
            mergeSort(start, mid);
            //후반부 나눔
            mergeSort(mid + 1, end);
            //병합정렬
            merge(start, mid, end);
        }
    }

    static void merge(int start, int mid, int end) {
        int p = start;
        int q = mid + 1;
        int idx = start;

        while (p <= mid && q <= end) {
            if (A[p] <= A[q]) {
                tmp[idx++] = A[p++];
            } else {
                tmp[idx++] = A[q++];
            }
        }

        //왼쪽 배열이 남은 경우
        while (p <= mid) {
            tmp[idx++] = A[p++];
        }

        //오른쪽 배열이 남은 경우
        while (q <= end) {
            tmp[idx++] = A[q++];
        }

        //정렬된 배열 복사
        for (int i = start; i <= end; i++) {
            cnt++;
            A[i] = tmp[i];
            //저장횟수가 K와 같으면 값 저장
            if (cnt == K) {
                result = A[i];
                return;
            }
        }
    }
}
