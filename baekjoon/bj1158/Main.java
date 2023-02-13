package bj1158;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //N과 K 입력
        int N = sc.nextInt();
        int K = sc.nextInt();

        List<Integer> list = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        //리스트에 1부터 N까지 숫자 입력
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        //리스트가 전부 비워질때까지 반복한다.
        while (!list.isEmpty()) {
            //k번째까지만 반복
            for (int i = 0; i < K; i++) {
                //k번째 전까지의 반복은 리스트의 앞에 있는 원소를 뒤로 재배치한다
                if (i < K-1) {
                    list.add(list.remove(0));
                }
                //k번째의 반복에 도달하면 기존 리스트에서는 제거하고 요세푸스 순열에 원소를 추가한다.
                else {
                    result.add(list.remove(0));
                }
            }

        }

        //출력
        System.out.println(result.toString().replace("[", "<").replace("]", ">"));

    }
}
