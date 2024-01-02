class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int time = attacks[0][0];
        int max = attacks[attacks.length-1][0];
        int maxH = health;
        
        int idx = 0;
        int cnt = 0;
        while(time <= max){
            if(time == attacks[idx][0]){
                health -= attacks[idx][1];
                idx++;
                if(health <= 0) return -1;
                
                cnt = 0;
            }else{
                health += bandage[1];
                cnt++;
                cnt %= bandage[0];
                if(cnt == 0) health += bandage[2];
                
                if(health > maxH) health = maxH; 
            }
            
            time++;
        }
        
        return health;
    }
}