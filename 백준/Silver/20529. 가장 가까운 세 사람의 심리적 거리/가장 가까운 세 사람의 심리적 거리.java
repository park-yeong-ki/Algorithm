import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String[] sArr;
    static String[] cArr;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 0; test_case < T; test_case++) {

            HashMap<String, Integer> map = new HashMap<>(); //N의 최대 범위가 10만이므로 16가지 유형으로 한 유형당 최대 3개씩 압축시킨다.
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                String str = st.nextToken();
                if (map.containsKey(str)) {
                    if (map.get(str) < 3) map.put(str, map.get(str) + 1); //최대 3개만 저장되어야함
                } else {
                    map.put(str, 1);
                }
            }

            List<String> list = new ArrayList<>(map.keySet()); //압축한 mbti유형을 리스트에 담기
            int size = list.size();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < map.get(list.get(i)) - 1; j++) { //이미 1개는 들어가 있기때문에 -1을 해준다.
                    list.add(list.get(i));
                }
            }
            sArr = list.toArray(new String[0]); //중복을 제거한 mbti 유형만 배열에 담는다.

            min = Integer.MAX_VALUE;
            cArr = new String[3]; //nC3
            comb(0, 0);

            sb.append(min + "\n");
        }

        System.out.print(sb);
    }

    static void comb(int cnt, int start) {
        if (cnt == cArr.length) { //N개 중 3개를 조합한 경우
            min = Math.min(min, calculateDist());
            return;
        }

        for (int i = start; i < sArr.length; i++) {
            cArr[cnt] = sArr[i];
            comb(cnt + 1, i + 1);
        }
    }

    static int calculateDist() { // 세 사람의 심리적 거리 계산
        int cnt = 0;
        for (int i = 0; i < 4; i++) { //mbti는 4자리
            if (cArr[0].charAt(i) != cArr[1].charAt(i)) cnt++;
            if (cArr[0].charAt(i) != cArr[2].charAt(i)) cnt++;
            if (cArr[1].charAt(i) != cArr[2].charAt(i)) cnt++;
        }
        return cnt;
    }
}