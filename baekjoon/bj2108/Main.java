package bj2108;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader, BufferedWriter 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //수의 개수 N 입력
        int N = Integer.parseInt(br.readLine());

        //값을 입력받을 배열 생성
        int[] arr = new int[N];

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        //카운트 배열 생성(-4000부터 4000까지의 정수)
        int[] count = new int[8001];

        //카운트 배열 이용(-4000은 0 인덱스에 들어가도록 설정)
        for (int i = 0; i < N; i++) {
            count[arr[i]+4000]++;
        }

        //배열을 재정렬하기 위한 size 변수 생성
        int size = 0;
        //최빈값
        int modeCount = 0;
        for (int i = 0; i < count.length; i++) {
            //배열 재정렬
            if (count[i] != 0) {
                for (int j = 0; j < count[i]; j++) {
                    arr[size++] = i-4000;
                }
                //최빈값 횟수 구하기
                modeCount = Math.max(modeCount, count[i]);
            }
        }

        //산술평균 구하기
        int avg = 0;
        for (int i = 0; i < arr.length; i++) {
            avg += arr[i];
        }
        avg = Math.round((float) avg / arr.length);

        //중앙값 구하기
        int middle = arr[arr.length/2];

        //최빈값 구하기
        int mode = 0;
        //최빈값이 여러개일 경우 두 번째로 작은 값을 최빈값으로 설정하기 위한 변수 선언
        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            if (num == 2) {
                break;
            }
            if (count[arr[i] + 4000] == modeCount && mode != arr[i]) {
                mode = arr[i];
                num++;
            }
        }


        //범위 구하기
        int range = arr[arr.length - 1] - arr[0];

        //출력할 내용
        bw.write(avg + "\n");
        bw.write(middle + "\n");
        bw.write(mode + "\n");
        bw.write(range + "\n");

        //출력
        bw.flush();
    }
}
