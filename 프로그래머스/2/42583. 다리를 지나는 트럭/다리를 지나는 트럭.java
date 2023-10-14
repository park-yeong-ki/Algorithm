import java.util.*;

class Solution {
    static class Node{
        int w, t;
        
        Node(int w, int t){
            this.w = w;
            this.t = t;
        }
    }
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Node> queue = new ArrayDeque<>();
        
        int total = 0;
        int i = 0;
        int time = 0;
        int ans = 0;
        while(true){
            time++;
            if(!queue.isEmpty() && time - queue.peek().t >= bridge_length){ //다리 건너기 완료한 트럭
                total -= queue.poll().w; //총 무게에서 빼준다.
                if(i >= truck_weights.length && queue.isEmpty()){
                    ans = time;
                    break; //모든 트럭이 다리를 건너면 종료
                }
            }
            
            if(i >= truck_weights.length || total + truck_weights[i] > weight) continue; //다리 위에 있는 트럭 무게가 견딜 수 있는 무게를 초과하면 통과
            total += truck_weights[i]; 
            queue.add(new Node(truck_weights[i], time));
            i++;
        }
        
        return ans;
    }
}