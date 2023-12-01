import java.util.*;

class Solution {
    static String str;
    static int ans;
    
    public int solution(int storey) {
        str = String.valueOf(storey);
        ans = Integer.MAX_VALUE;
        dfs(0, 0, false);
        return ans;
    }
    
    static void dfs(int depth, int cnt, boolean flag){
        if(ans <= cnt) return; //가지치기
        
        if(str.length() == depth){
            if(flag){ //다음 자리 수가 있는 경우
                ans = Math.min(ans, cnt+1);
            }else{ //다음 자리 수가 없는 경우
                ans = Math.min(ans, cnt);
            }
            
            return;
        }
        
        int num = str.charAt(depth) - '0'; //현재 자리수
        if(flag){ //만약 이전 버튼을 더해서 0으로 만든 경우
            num = num+1;
            if(num == 10){ //자리수가 넘어가는 경우
                dfs(depth+1, cnt, true);
            }else{
                dfs(depth+1, cnt + (10 - num), true);//자리 버튼을 더해서 0으로 만드는 경우 -> 다음 자리수 +1
                dfs(depth+1, cnt + num, false);//자리 버튼을 빼서 0으로 만드는 경우
            }
        }else{
            dfs(depth+1, cnt + (10 - num), true);//자리 버튼을 더해서 0으로 만드는 경우 -> 다음 자리수 +1
            dfs(depth+1, cnt + num, false);//자리 버튼을 빼서 0으로 만드는 경우
        }
        
    }
}