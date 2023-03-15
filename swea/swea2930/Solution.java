package swea2930;

import java.util.Scanner;

public class Solution {
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //테스트 케이스의 수 T
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            //연산의 수를 나타내는 자연수
            int N = sc.nextInt();

            //0번 인덱스 사용안함
            arr = new int[N + 1];

            System.out.print("#" + test_case + " ");
            int lastIdx = 0;
            for (int i = 0; i < N; i++) {
                int num = sc.nextInt();

                //삽입하는 경우 -> 가장 마지막 인덱스에 입력후 정렬
                if (num == 1) {
                    arr[++lastIdx] = sc.nextInt();

                    int current = lastIdx;
                    //자식 노드가 부모 노드보다 큰 경우, 루트노드가 되지 않은 경우
                    while (current > 1 && arr[current] > arr[current / 2]) {
                        swap(current, current / 2);
                        current = current / 2;
                    }
                }
                //최댓값 출력 후 해당 키값 삭제
                else {
                    if (lastIdx > 0) {
                        System.out.print(arr[1] + " ");

                        swap(1, lastIdx);
                        arr[lastIdx--] = 0;

                        int current = 1;
                        //루트노드에서부터 자식노드와 비교하며 정렬
                        while (true) {
                            int child = current * 2;
                            if (child + 1 <= lastIdx && arr[child] < arr[child + 1]) {
                                child++;
                            }

                            if (child > lastIdx || arr[child] < arr[current]) break;

                            swap(current, child);
                            current = child;
                        }
                    } else {
                        System.out.print(-1 + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
