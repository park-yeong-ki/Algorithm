package bj10816;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //카드를 담을 map 생성
        Map<Integer, Integer> map = new HashMap<>();

        //N입력
        int N = Integer.parseInt(br.readLine());

        //map에 요소 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int count = 1;
            int num = Integer.parseInt(st.nextToken());

            //이미 가지고 있는 숫자인 경우 개수를 갱신
            if (map.containsKey(num)) {
                count += map.get(num);
            }

            //map은 key의 중복을 허용하지 않기 때문에 같은 key를 넣으면 기존의 key, value가 새로 갱신됨
            map.put(num, count);
        }

        //M입력
        int M = Integer.parseInt(br.readLine());

        //M개의 수만큼 반복하면서 카드안에 값이 있는 경우와 없는 경우를 나눠 개수를 출력한다.
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (map.containsKey(num)) {
                bw.write(map.get(num) + " ");
            } else {
                bw.write(0 + " ");
            }
        }

        bw.newLine();
        bw.close();
    }
}
