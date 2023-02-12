package bj10845;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //명령의 수 N입력
        int N = Integer.parseInt(br.readLine());

        //큐를 구현할 리스트 생성
        List<Integer> list = new LinkedList<>();

        //N까지 반복
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            //push일 경우 숫자가 따로 있으므로 배열로 나눠준다
            String[] sArr = str.split(" ");

            //명령어에 따라 주어진 명령을 실행하게 설정
            switch (sArr[0]) {
                case "push":
                    list.add(Integer.parseInt(sArr[1]));
                    break;
                case "pop":
                    if (list.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(list.remove(0) + "\n");
                    }
                    break;
                case "size":
                    bw.write(list.size() + "\n");
                    break;
                case "empty":
                    if (list.isEmpty()) {
                        bw.write(1 + "\n");
                    } else {
                        bw.write(0 + "\n");
                    }
                    break;
                case "front":
                    if (list.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(list.get(0) + "\n");
                    }
                    break;
                case "back":
                    if (list.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(list.get(list.size()-1) + "\n");
                    }
                    break;
            }
        }
        bw.close();

    }
}
