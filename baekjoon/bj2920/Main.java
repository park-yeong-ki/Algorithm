package bj2920;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 선언
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[8];
        
        //배열 입력
        for (int i = 0; i < 8; i++) {
            arr[i] = sc.nextInt();    
        }

        //오름차순 카운트 변수와 내림차순 카운트 변수 선언
        int ascending = 0;
        int descending = 0;

        //음의 개수만큼 반복하여 카운트 변수 값 설정
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == i+1) {
                ascending++;
            } else if (arr[i] == 8-i) {
                descending++;
            }
        }

        //카운트 변수를 이용하여 각 조건에 맞으면 출력
        if (ascending == 8) {
            System.out.println("ascending");
        } else if (descending == 8) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }

    }
}
