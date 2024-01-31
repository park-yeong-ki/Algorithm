import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Map<String, Integer>> map = new HashMap<>();
        Map<String, Integer> giftScore = new HashMap<>();
        
        for(int i=0; i<friends.length; i++){
            map.put(friends[i], new HashMap<>());
        }
        
        String[] friend;
        for(int i=0; i<gifts.length; i++){
            friend = gifts[i].split(" ");
            map.get(friend[0]).put(friend[1], map.get(friend[0]).getOrDefault(friend[1], 0) + 1);
            giftScore.put(friend[0], giftScore.getOrDefault(friend[0], 0) + 1);
            giftScore.put(friend[1], giftScore.getOrDefault(friend[1], 0) - 1);
        }
        
        Map<String, Integer> nextMonth = new HashMap<>();
        int A, B;
        for(int i=0; i<friends.length; i++){
            for(int j=i+1; j<friends.length; j++){
                A = map.get(friends[i]).getOrDefault(friends[j], 0);
                B = map.get(friends[j]).getOrDefault(friends[i], 0);
                
                if(A > B){
                    nextMonth.put(friends[i], nextMonth.getOrDefault(friends[i], 0) + 1);
                }else if(A < B){
                    nextMonth.put(friends[j], nextMonth.getOrDefault(friends[j], 0) + 1);
                }else{
                    A = giftScore.getOrDefault(friends[i], 0);
                    B = giftScore.getOrDefault(friends[j], 0);
                    if(A > B){
                        nextMonth.put(friends[i], nextMonth.getOrDefault(friends[i], 0) + 1);
                    }else if(A < B){
                        nextMonth.put(friends[j], nextMonth.getOrDefault(friends[j], 0) + 1);
                    }
                }
            }
        }
        
        int ans = 0;
        
        for(String key : nextMonth.keySet()){
            ans = Math.max(ans, nextMonth.get(key));
        }
        
        return ans;
    }
}