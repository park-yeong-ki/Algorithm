package bj18258;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //명령의 수 N입력
        int N = Integer.parseInt(br.readLine());

        //리스트로 큐 구현
        List<Integer> list = new LinkedList<>();

        //N개의 명령 입력
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            //push일 경우 명령어와 숫자를 분리될 수 있도록 split 사용
            String[] sArr = str.split(" ");

            //명령어와 일치하는 경우에 따른 조건 설정
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
