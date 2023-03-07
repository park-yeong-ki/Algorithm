package bj23970;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //배열 A, B의 크기 N
        int N = Integer.parseInt(br.readLine());

        //배열 A입력
        int[] aArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aArr.length; i++) {
            aArr[i] = Integer.parseInt(st.nextToken());
        }

        //배열 B입력
        int[] bArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = Integer.parseInt(st.nextToken());
        }

        //A, B 배열 원소가 같은지 체크하는 배열
        boolean[] check = new boolean[N];
        //초기 배열 확인
        int count = 0;
        for (int i = 0; i < aArr.length; i++) {
            if (aArr[i] == bArr[i]) {
                check[i] = true;
                count++;
            }
        }

        //버블정렬
        for (int i = 0; i < aArr.length-1; i++) {
            if(count == N) break;
            for (int j = 0; j < aArr.length-1-i; j++) {
                if (aArr[j] > aArr[j+1]) {
                    //스왑
                    int temp = aArr[j+1];
                    aArr[j+1] = aArr[j];
                    aArr[j] = temp;

                    //바꾼 자리에 대해서 B배열과 비교
                    if (aArr[j] == bArr[j]) {
                        check[j] = true;
                        count++;
                    }
                    else {
                        //같다고 표시한 자리가 버블정렬이후로 같지 않은 경우
                        if(check[j]) {
                            check[j] = false;
                            count--;
                        }
                    }

                    //바꾼 자리에 대해서 B배열과 비교
                    if (aArr[j+1] == bArr[j+1]) {
                        check[j+1] = true;
                        count++;
                    }
                    else {
                        //같다고 표시한 자리가 버블정렬이후로 같지 않은 경우
                        if(check[j+1]) {
                            check[j+1] = false;
                            count--;
                        }
                    }


                    //A, B 배열이 같은지 확인
                    if (count == N) {
                        break;
                    }
                }
            }
        }

        //출력
        if(count == N) bw.write(1 + "\n");
        else bw.write(0 + "\n");

        bw.close();
    }
}