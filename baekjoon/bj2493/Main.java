package bj2493;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//스택의 개념을 리스트로 구현
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //탑의 수 입력
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        //입력받을 값을 저장할 리스트와 행을 저장할 리스트 생성
        List<Integer> list = new ArrayList<>();
        List<Integer> idx = new ArrayList<>();

        //N까지 반복
        for (int i = 1; i <= N; i++) {
            //입력된 값을 저장
            int num = Integer.parseInt(st.nextToken());

            while(true) {
                //리스트가 비워져있는 경우
                if (!list.isEmpty()) {
                    //가장 최근에 저장된 리스트 값보다 입력된 값이 클 경우
                    if (list.get(list.size()-1) < num) {
                        //최근에 저장된 리스트 값을 제거한다.
                        list.remove(list.size()-1);
                        idx.remove(idx.size()-1);
                    }
                    //가장 최근에 저장된 리스트 값보다 입력된 값이 작거나 같은 경우 -> 신호를 수신할 수 있는 경우
                    else {
                        bw.write(idx.get(idx.size()-1) + " ");
                        list.add(num);
                        idx.add(i);
                        break;
                    }
                }
                //리스트가 비워져있지 않은 경우 -> 신호를 수신하지 못하는 경우
                else {
                    bw.write(0 + " ");
                    list.add(num);
                    idx.add(i);
                    break;
                }
            }
        }
        bw.newLine();
        bw.close();


    }
}
