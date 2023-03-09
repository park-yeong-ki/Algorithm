package bj11047;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N과 K
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //동전 배열
        int[] coin = new int[N];
        for (int i = 0; i < coin.length; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }


        //각 동전별로 k를 구성한다
        int min = Integer.MAX_VALUE;
        //동전수 만큼 반복
        for (int i = 0; i < coin.length; i++) {
            int money = K;
            int count = 0;
            //가장 비싼 동전에서 하나씩 제외하면서 반복문 실행
            for (int j = i; j < coin.length; j++) {
                count += money / coin[N-1-j];
                money %= coin[N-1-j];

                //동전으로 돈을 완성한 경우 종료
                if (money == 0) {
                    //최소값 갱신
                    min = Math.min(min, count);
                    break;
                }
            }
        }

        //최소값 출력
        System.out.println(min);
    }
}