package bj10828;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //명령의 수 N입력
        int N = Integer.parseInt(br.readLine());

        //List로 Stack구현
        List list = new ArrayList<>();

        //N까지 반복
        for (int i = 0; i < N; i++) {
            String[] command = br.readLine().split(" ");
            //명령어를 각 케이스별로 비교한후 주어진 조건대로 실행하도록 설정
            switch (command[0]) {
                case "push":
                    list.add(command[1]);
                    break;
                case "pop":
                    if (!list.isEmpty()) {
                        bw.write(list.remove(list.size() - 1) + "\n");
                    } else {
                        bw.write(-1 + "\n");
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
                case "top":
                    if (!list.isEmpty()) {
                        bw.write(list.get(list.size() - 1) + "\n");
                    } else {
                        bw.write(-1 + "\n");
                    }
                    break;
            }
        }
        bw.close();
    }
}
