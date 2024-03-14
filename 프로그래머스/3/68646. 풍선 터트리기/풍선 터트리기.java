import java.util.*;

class Solution {
    public int solution(int[] a) {
        int left = Integer.MAX_VALUE;
        PriorityQueue<Integer> right = new PriorityQueue<>();
        right.add(Integer.MAX_VALUE);
        Set<Integer> set = new HashSet<>();
        
        for(int num : a){
            right.add(num);
        }
        
        int ans = 0;
        for(int num : a){
            set.add(num);
            while(set.contains(right.peek())){
                right.poll();
            }
            
            if(left > num || right.peek() > num){
                ans++;
            }
            
            left = Math.min(left, num);
        }
        
        
        return ans;
    }
}