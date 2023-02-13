package swea1228;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 케이스 10번 반복
        for (int test_case = 1; test_case <= 10; test_case++) {
            //N입력
            int N = Integer.parseInt(br.readLine());

            //리스트 생성
            List<Integer> list = new ArrayList<Integer>();


            //원본 암호문 리스트에 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            //명령어의 개수 M 입력
            int M = Integer.parseInt(br.readLine());

            //명렁어 입력
            st = new StringTokenizer(br.readLine());


            while (true) {
                //더이상 입력받을 문자가 없다면 반복문 종료
                if (!st.hasMoreTokens()) {
                    break;
                }

                //입력받은 내용이 I와 같다면 x와 y를 입력받아 리스트의 적절한 위치에 삽입한다.
                if (st.nextToken().equals("I")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());

                    for (int i = 0; i < y; i++) {
                        list.add(x+i, Integer.parseInt(st.nextToken()));
                    }
                }
            }

            //출력
            bw.write("#" + test_case + " ");
            for (int i = 0; i < 10; i++) {
                bw.write(list.get(i) + " ");
            }
            bw.newLine();
        }
        bw.close();
    }
}
