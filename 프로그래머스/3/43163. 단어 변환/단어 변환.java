class Solution {
    static String[] sArr;
    static boolean[] visited;
    static String t;
    static int ans;
    
    public int solution(String begin, String target, String[] words) {
        sArr = words;
        visited = new boolean[words.length];
        t = target;
        ans = Integer.MAX_VALUE;
        
        dfs(0, begin);
        
        return ans == Integer.MAX_VALUE ? 0 : ans; //변환할 수 없는 경우는 0 리턴
    }
    
    static void dfs(int depth, String str){
        if(depth >= ans) return; //가지치기
        
        if(str.equals(t)){ //목적 단어로 변경한 경우
            ans = Math.min(ans, depth);
            return;        
        }
        
        if(depth == sArr.length){ //모든 단어를 탐색한 경우
            return;
        }
        
        for(int i=0; i<sArr.length; i++){
            if(visited[i]) continue; //방문한 경우
            if(!isPossible(str, sArr[i])) continue; //변경할 수 없는 경우
            
            visited[i] = true;
            dfs(depth+1, sArr[i]); //선택된 단어로 변경하며 dfs
            visited[i] = false;
        }
    }
    
    static boolean isPossible(String str1, String str2){ //알파벳 변경 가능한지
        int cnt = 0;
        for(int i=0; i<str1.length(); i++){
            if(str1.charAt(i) != str2.charAt(i)){
                cnt++;
            }
        }
        
        return cnt == 1; //한 개의 알파벳만 다른지
    }
}