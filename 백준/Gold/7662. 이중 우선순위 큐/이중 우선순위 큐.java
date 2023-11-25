import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static PriorityQueue<Integer> maxHeap;
    static PriorityQueue<Integer> minHeap;
    static Map<Integer, Integer> map1;
    static Map<Integer, Integer> map2;
    static Integer n1, n2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            maxHeap = new PriorityQueue<>(new Comparator<Integer>() { //최대힙
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o2, o1);
                }
            });
            minHeap = new PriorityQueue<>(); //최소힙

            map1 = new HashMap<>(); //최대힙에서 삭제한 수
            map2 = new HashMap<>(); //최소힙에서 삭제한 수

            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                String opr = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (opr.equals("I")) { //삽입
                    minHeap.add(num);
                    maxHeap.add(num);
                } else { //삭제
                    if (num == 1) { //최대힙 삭제
//                        System.out.print("D 1: ");
                        D1();
//                        System.out.println();
                    } else { //최소힙 삭제
//                        System.out.print("D -1: ");
                        D2();
//                        System.out.println();
                    }
                }
            }

            n1 = n2 = null;
            D1();//최대값
            D2();//최소값

            if (n1 != null && n2 != null) {
                sb.append(n1).append(" ").append(n2);
            } else if (n1 != null) {
                sb.append(n1).append(" ").append(n1);
            } else if (n2 != null) {
                sb.append(n2).append(" ").append(n2);
            } else {
                sb.append("EMPTY");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void D1() { // D 1인경우
        if (maxHeap.isEmpty()) return;
        int max = maxHeap.poll();

        if (map2.containsKey(max)) { //제거한 숫자가 최소힙에서 삭제한 수인 경우
            int cnt = map2.get(max) - 1;
            if (cnt == 0) {
                map2.remove(max);
            } else {
                map2.put(max, cnt);
            }
            D1();
        } else { //최소힙에서 삭제한 수가 아닌 경우
            if (map1.containsKey(max)) {
                map1.put(max, map1.get(max) + 1);
            } else {
                map1.put(max, 1);
            }
            n1 = max;
//            System.out.print(max);
        }
    }

    static void D2() { // D -1인경우
        if (minHeap.isEmpty()) return;
        int min = minHeap.poll();

        if (map1.containsKey(min)) { //제거한 숫자가 최대힙에서 삭제한 수인 경우
            int cnt = map1.get(min) - 1;
            if (cnt == 0) {
                map1.remove(min);
            } else {
                map1.put(min, cnt);
            }
            D2();
        } else { //최대힙에서 삭제한 수가 아닌 경우
            if (map2.containsKey(min)) {
                map2.put(min, map2.get(min) + 1);
            } else {
                map2.put(min, 1);
            }
            n2 = min;
//            System.out.print(min);
        }
    }
}