import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        long sum1 = 0;
        long sum2 = 0;
        for(int i=0; i<queue1.length; i++){
            q1.add(queue1[i]);
            sum1 += queue1[i];
            
            q2.add(queue2[i]);
            sum2 += queue2[i];
        }
        
        if((sum1 + sum2) % 2 == 1) return -1; //홀수인 경우 만들 수 없다.
        
        
        int cnt = 0;
        while(true){
            if(cnt > queue1.length * 4){
                return -1;
            }
            
            if(sum1 > sum2){
                int num1 = q1.poll();
                sum1 -= num1;
                q2.add(num1);
                sum2 += num1;
            }else if(sum1 < sum2){
                int num2 = q2.poll();
                sum2 -= num2;
                q1.add(num2);
                sum1 += num2;
            }else{
                return cnt;
            }
            
            cnt++;
        }
    }
}