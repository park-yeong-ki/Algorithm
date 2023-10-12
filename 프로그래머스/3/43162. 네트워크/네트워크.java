import java.util.*;

class Solution {
    static ArrayList<Integer>[] adjList;
    public int solution(int n, int[][] computers) {
        //인접리스트 생성
        adjList = new ArrayList[n];
        for(int i=0; i<n; i++){
            adjList[i] = new ArrayList();
        }
        
        for(int i=0; i<computers.length; i++){
            for(int j=0; j<computers[i].length; j++){
                if(i == j) continue;
                if(computers[i][j] == 1) adjList[i].add(j);
            }
        }
        
        //방문배열 생성
        boolean[] visited = new boolean[n];
        
        //각 번호의 컴퓨터 탐색
        int ans = 0;
        for(int i=0; i<n; i++){
            if(visited[i]) continue; //방문한 정점인 경우 통과
            dfs(i, visited);
            ans++; //dfs 수행횟수만큼 네트워크 개수
        }
        
        return ans;
    }
    
    static void dfs(int num, boolean[] visited){
        visited[num] = true;
        
        for(int n : adjList[num]){
            if(visited[n]) continue; //이미 방문한 정점은 통과
            dfs(n, visited);    
        }
    }
}