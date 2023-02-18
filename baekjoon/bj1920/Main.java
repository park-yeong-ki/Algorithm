package bj1920;

import java.io.*;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //N 입력
        int N = Integer.parseInt(br.readLine());

        //숫자를 담을 셋 생성 및 요소 입력
        Set<Integer> set = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        //M입력
        int M = Integer.parseInt(br.readLine());

        //미리 입력받은 리스트에서 숫자를 찾는다
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            //set의 contains는 시간복잡도 O(1)이므로 list O(n)보다 빠르다
            if (set.contains(Integer.parseInt(st.nextToken()))) {
                bw.write(1 + "\n");
            } else {
                bw.write(0 + "\n");
            }
        }
        bw.close();
    }
}
