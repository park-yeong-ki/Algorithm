package bj18870;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader, BufferedWriter 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //N입력
        int N = Integer.parseInt(br.readLine());
        //길이가 N인 배열 생성
        int[] arr = new int[N];

        //StringTokenizer 생성
        StringTokenizer st = new StringTokenizer(br.readLine());

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //정렬할 배열 생성
        int[] nArr = arr.clone();

        //오름차순 정렬
        Arrays.sort(nArr);

        //값과 인덱스를 동시에 저장할 해시맵 생성
        HashMap map = new HashMap<>();

        //해시맵안에 같은 값이 있는지 확인후 없으면 값과 인덱스를 넣어줌
        int idx = 0;
        for (int i = 0; i < nArr.length; i++) {
            if (!map.containsKey(nArr[i])) {
                map.put(nArr[i], idx++);
            }
        }

        //기존 배열의 값을 통해 해당 인덱스를 불러와 출력
        for (int i = 0; i < arr.length; i++) {
            bw.write(map.get(arr[i]) + " ");
        }
        bw.newLine();
        bw.close();

//        시간초과 --> 해시맵을 통해 시간복잡도를 줄여서 해결함
//        int size = 0;
//        //반복문을 통해 중복이 없는 요소만 새로운 배열에 입력
//        outer:
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = i+1; j < arr.length; j++) {
//                if (arr[i] == arr[j]) {
//                    continue outer;
//                }
//            }
//            nArr[size++] = arr[i];
//        }
//        nArr = Arrays.copyOf(nArr, size);
//
//        //오름차순 정렬
//        Arrays.sort(nArr);
//
//        //정렬된 배열의 인덱스를 이용하여 결과 출력
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < nArr.length; j++) {
//                if (arr[i] == nArr[j]) {
//                    bw.write(j + " ");
//                }
//            }
//        }
//        bw.newLine();
//        bw.close();

    }
}
