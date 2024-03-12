import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, String> rMap = new HashMap();
        Map<String, Integer> pMap = new HashMap();
        
        for(int i=0; i<enroll.length; i++){
            rMap.put(enroll[i], referral[i]);
        }
        
        for(int i=0; i<seller.length; i++){
            String s = seller[i];
            int a = amount[i] * 100;
            int next;
            while(!s.equals("-") && a > 0){
                next = a / 10;
                pMap.put(s, pMap.getOrDefault(s, 0) + a - next);
                
                a = next;
                s = rMap.get(s);
            }
        }
        
        int[] ans = new int[enroll.length];
        for(int i=0; i<enroll.length; i++){
            ans[i] = pMap.getOrDefault(enroll[i], 0);
        }
        
        return ans;
    }
}