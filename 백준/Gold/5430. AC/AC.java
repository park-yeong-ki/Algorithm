import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());

            String arr = br.readLine();
            LinkedList<Integer> list = new LinkedList<>(); //앞, 뒤만 제거하므로 링크드리스트 사용
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<arr.length(); j++){ //리스트에 숫자만 입력
                char ch = arr.charAt(j);

                if (ch < '0' || ch > '9') { //숫자가 아닌 경우
                    if (sb.length() == 0) continue; //아무것도 없는 경우
                    list.add(Integer.parseInt(sb.toString()));
                    sb.setLength(0);
                } else {
                    sb.append(ch);
                }
            }

            boolean flag = false;
            boolean isCompleted = true;

            for (int j = 0; j < p.length(); j++) { //함수 실행
                if (p.charAt(j) == 'R') { //뒤집기
                    flag = !flag;
                } else { //첫번째 수 버리기
                    if (list.isEmpty()){ //리스트에 제거할 숫자가 없는 경우
                        ans.append("error\n");
                        isCompleted = false;
                        break;
                    }

                    if (flag) { //뒤집어진 경우
                        list.removeLast(); //뒤에서 제거
                    } else { //정방향인 경우
                        list.removeFirst(); //앞에서 제거
                    }
                }
            }

            if (isCompleted) { //명령어를 모두 실행한 경우
                int size = list.size();
                ans.append("[");
                if (flag) { //최종적으로 뒤집혀진 경우
                    for (int j = 0; j < size; j++) {
                        if (j != 0) ans.append(",");
                        ans.append(list.removeLast());
                    }
                } else {
                    for (int j = 0; j < size; j++) {
                        if (j != 0) ans.append(",");
                        ans.append(list.removeFirst());
                    }
                }
                ans.append("]\n");
            }

        }

        System.out.println(ans);

    }
}