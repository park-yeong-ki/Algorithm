import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //최소힙
        
        int eSum = 0; //총 적의 수
        int kSum = 0; //무적권의 합
        int ans = enemy.length; //모든 라운드를 막을 수 있다고 초기화
        for(int i=0; i<enemy.length; i++){
            eSum += enemy[i];
            
            if(pq.size() < k){ //k만큼의 무적권을 사용하지 않은 경우
                pq.add(enemy[i]); 
                kSum += enemy[i];
            }else{ //k만큼의 무적권을 사용해서 무적권 값을 갱신해야되는 경우
                int min = pq.poll(); //무적권의 최소값
                kSum -= min; //무적권의 합 갱신
                int max = Math.max(min, enemy[i]); //무적권의 최소값과 현재 적의 수 중 최대 값을 비교
                pq.add(max); //무적권 갱신
                kSum += max;
            }
            
            if(eSum - kSum > n){ //적을 못막는 경우
                ans = i;
                break;
            }
        }
        
        return ans;
    }
}