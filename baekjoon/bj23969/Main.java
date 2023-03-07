package bj23969;

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

        //배열 A의 크기 N, 교환 횟수 K
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //배열 입력
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //버블정렬
        int count = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    //스왑
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    //교환횟수
                    count++;

                    //k번 교환했으면 반복 종료
                    if (count == K) {
                        flag = true;
                        break;
                    }
                }
            }
            if(flag) break;
        }

        //출력
        if(flag) {
            for (int i = 0; i < arr.length; i++) {
                bw.write(arr[i] + " ");
            }
        } else {
            bw.write(-1 + "\n");
        }

        bw.close();
    }

}