import java.util.*;

class Solution {
    static ArrayList<Integer>[] adjList;
    static int cnt; 
    
    public int solution(int n, int[][] wires) {
        //인접 간선리스트 생성
        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            adjList[i] = new ArrayList<>();
        }
        
        int ans = Integer.MAX_VALUE;
        
        //간선 추가
        for(int i=0; i<wires.length; i++){
            int to, from;
            for(int j=0; j<wires.length; j++){
                if(i == j) continue;
                to = wires[j][0];
                from = wires[j][1];
                //무방향
                adjList[to].add(from);
                adjList[from].add(to);
            }
            
            
            cnt = 0;
            dfs(1, new boolean[n+1]);
            ans = Math.min(ans, Math.abs((n-cnt) - cnt));
            
            
            for(int j=1; j<=n; j++){ //리스트 지우기
                adjList[j].clear();
            }
        }
        
        return ans;
    }
    
    static void dfs(int num, boolean[] visited){
        visited[num] = true;
        cnt++;
        
        for(int n : adjList[num]){
            if(visited[n]) continue;
            dfs(n, visited);
        }
    }
}