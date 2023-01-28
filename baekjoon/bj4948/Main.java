package bj4948;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader, BufferedWriter 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //n입력
        while (true) {
            int n = Integer.parseInt(br.readLine());

            //입력의 마지막은 0이므로 0일 경우 반복문 종료
            if (n == 0) {
                break;
            }

            int result = 0;
            outer:
            for (int i = n+1; i <= 2*n; i++) {
                //2부터 제곱근까지 나누며 반복문 도중 약수가 발생하면 소수가 아닌 것을 확인한다.
                //continue outer문을 사용하여 소수가 아닌 부분이 발견되면 다음 i로 넘어가는 것이 시간초과를 해결하는 방법이었다.
                for (int j = 2; j <= Math.sqrt(i); j++) {
                    if (i % j == 0) {
                        continue outer;
                    }
                }
                //반복문을 통해 소수인 것이 확인되었다면 결과값을 1씩 증가시킴
                result++;
            }

            //출력
            bw.write(String.valueOf(result));
            bw.newLine();
        }
        bw.flush();
    }
}
