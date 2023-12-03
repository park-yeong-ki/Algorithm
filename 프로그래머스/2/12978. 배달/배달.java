import java.util.*;

class Solution {
    static class Node implements Comparable<Node>{
        int to, weight;
        
        Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Node n){ //가중치 오름차순 정렬
            return Integer.compare(this.weight, n.weight);
        }
    }
    static ArrayList<Node>[] adjList;
    static int ans;
    
    public int solution(int N, int[][] road, int K) {
        adjList = new ArrayList[N+1]; //1번 마을부터 시작
        for(int i=1; i<=N; i++){
            adjList[i] = new ArrayList<>();
        }
        
        for(int i=0; i<road.length; i++){ //인접리스트에 값 넣기
            int from = road[i][0];
            int to = road[i][1];
            int weight = road[i][2];
            
            adjList[from].add(new Node(to, weight)); //무향 그래프
            adjList[to].add(new Node(from, weight)); 
        }
        
        ans = 0;
        dijkstra(new Node(1, 0), K); //1번 마을에서 배달 가능한 마을 찾기
        
        return ans; 
    }
    
    static void dijkstra(Node start, int limit){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[adjList.length]; //마을의 개수만큼 생성
        
        pq.add(start);
        
        Node current;
        while(!pq.isEmpty()){
            current = pq.poll();
            
            if(visited[current.to]) continue; //이미 방문한 지점이면 통과
            visited[current.to] = true;
            
            if(current.weight <= limit) ans++;
            else return; //가능한 시간을 초과한 경우
            
            for(Node n : adjList[current.to]){ //인접리스트 탐색
                if(visited[n.to]) continue; //이미 방문한 지점은 통과
                pq.add(new Node(n.to, current.weight + n.weight));
            }
        }
        
    }
}