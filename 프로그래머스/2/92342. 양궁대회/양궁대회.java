class Solution {
    static int[] apeach, ryan, ans;
    static int max;
    
    public int[] solution(int n, int[] info) {
        apeach = info;
        ryan = new int[11];
        max = 0;
        ans = new int[]{-1};
        
        dfs(0, n, 0);
        
        return ans;
    }
    
    static void dfs(int idx, int arrow, int score){
        if(idx == 10){
            ryan[idx] = arrow;
            
            if(max < score){
                max = score;
                ans = ryan.clone();
            }else if(ans.length != 1 && max == score){
                boolean flag = false;
                for(int i=10; i>=0; i--){
                    if(ans[i] < ryan[i]){
                        flag = true;
                        break;
                    }else if(ans[i] > ryan[i]){
                        break;
                    }
                }
                
                if(flag) ans = ryan.clone();                
            }
            
            return;
        }
        
        if(apeach[idx] < arrow){
            ryan[idx] = apeach[idx] + 1;
            dfs(idx+1, arrow - (apeach[idx] + 1), score + (10 - idx));
        }
        
        if(apeach[idx] == 0){
            ryan[idx] = 0;
            dfs(idx+1, arrow, score);
        } else{
            ryan[idx] = 0;
            dfs(idx+1, arrow, score - (10 - idx));
        }
    }
}