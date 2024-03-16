import java.util.*;

class Solution {
    static ArrayList<Integer>[] adjList;
    static int[] dist;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        dist = new int[n+1];
        Arrays.fill(dist, -1);
        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            adjList[i] = new ArrayList<>();
        }
        
        int from, to;
        for(int[] e : roads){
            from = e[0];
            to = e[1];
            
            adjList[from].add(to);
            adjList[to].add(from);
        }
        
        bfs(destination);
        
        int[] ans = new int[sources.length];
        for(int i=0; i<sources.length; i++){
            ans[i] = dist[sources[i]];
        }
        
        return ans;
    }
    
    static void bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        
        queue.add(start);
        dist[start] = 0;
        
        int current;
        int size;
        int level = 0;
        while(!queue.isEmpty()){
            size = queue.size();
            
            for(int i=0; i<size; i++){
                current = queue.poll();
                
                for(int next : adjList[current]){
                    if(dist[next] != -1) continue;
                    queue.add(next);
                    dist[next] = level+1;
                }
            }
            
            level++;
        }
    }
}