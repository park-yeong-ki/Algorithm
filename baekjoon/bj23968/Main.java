package bj23968;

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

        //배열 A의 크기 N, 교환 횟수 K 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //배열 원소 입력
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //버블 정렬 N-1번 비교
        int count = 0;
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < arr.length-1; i++) {
            //비교를 할수록 비교횟수가 줄어든다
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    //스왑
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    //스왑 횟수 체크
                    count++;
                    if (count == K) {
                        num1 = arr[j];
                        num2 = arr[j+1];
                    }
                }
            }
        }

        //출력
        if (num1 != 0) {
            bw.write(num1 + "\n");
            bw.write(num2 + "\n");
        }else {
            bw.write(-1 + "\n");
        }
        bw.close();
    }
}