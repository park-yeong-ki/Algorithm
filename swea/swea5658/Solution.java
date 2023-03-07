package swea5658;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 케이스의 개수 T
        int T = Integer.parseInt(br.readLine());

        //테스트 케이스 반복
        for (int test_case = 1; test_case <= T; test_case++) {
            //숫자의 개수 N과 크기 순서 K
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            //N개의 숫자 입력
            ArrayList<Character> list = new ArrayList<>();
            String num = br.readLine();
            for (int i = 0; i < N; i++) {
                list.add(num.charAt(i));
            }

            //한 변의 길이
            int length = N/4;

            //한 변의 길이만큼 회전
            ArrayList<Integer> result = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                //각 변을 10진수로 변환 후 리스트에 저장
                for (int j = 0; j < N; j+=length) {
                    String line = "";
                    for (int j2 = j; j2 < length + j; j2++) {
                        line += list.get(j2);
                    }
                    //16진수 -> 10진수 변환
                    int value = Integer.parseInt(line, 16);
                    //중복되지 않는 값만 결과 리스트에 저장
                    if(!result.contains(value))result.add(value);
                }

                //시계방향 회전
                list.add(0, list.remove(list.size()-1));
            }

            //리스트 내림차순 정렬
            Collections.sort(result, Comparator.reverseOrder());

            //출력
            bw.write("#" + test_case + " " + result.get(K-1) + "\n");
        }

        bw.close();
    }
}