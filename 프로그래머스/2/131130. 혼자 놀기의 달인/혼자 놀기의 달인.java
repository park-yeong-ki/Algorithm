import java.util.*;

class Solution {
    static boolean[] visited;
    static int[] arr;
    static List<Integer> list;
    
    public int solution(int[] cards) {
        visited = new boolean[cards.length]; //카드 개수만큼 방문 배열 생성 0부터 인덱스 시작
        list = new ArrayList<>();
        arr = cards; //전역변수로 생성
        for(int i=0; i<arr.length; i++){
            if(visited[i]) continue; //이미 방문한 상자면 통과
            dfs(0, i+1); 
        }
        
        Collections.sort(list, Collections.reverseOrder()); //집합 원소 개수별 내림차순 정렬
        
        int ans = 0;
        if(list.size() > 1) ans = list.get(0) * list.get(1); //가장 큰 두개만 곱하기
        
        return ans;
    }
    
    static void dfs(int cnt, int num){
        if(visited[num-1]){ //이미 열려있는 상자를 만나면 종료
            list.add(cnt); //그룹의 속한 개수를 저장
            return;
        }
        
        visited[num-1] = true;
        
        dfs(cnt+1, arr[num-1]);
        
    }
}