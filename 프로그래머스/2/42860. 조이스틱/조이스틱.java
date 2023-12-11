class Solution {
    static char[] arr;
    static char[] target;
    static int n, ans;
    
    public int solution(String name) {
        arr = new char[name.length()];
        target = name.toCharArray();
        n = 0;
        for(int i=0; i<name.length(); i++){
            arr[i] = 'A';
            if(arr[i] != target[i]) n++;
        }
        ans = Integer.MAX_VALUE;
        dfs(0,0,0);
        
        return ans;
    }
    
    static int change(int idx){
        int n1 = target[idx] - arr[idx];
        int n2 = 'Z' - 'A' + 1 - n1;
        arr[idx] = target[idx]; //알파벳 변경
        return Math.min(n1,n2); //최소값 반환
    }
    
    static void dfs(int depth, int idx, int cnt){
        if(ans <= cnt) return; //가지치기
        
        if(depth == n){ //모든 알파벳을 변경한 경우
            ans = cnt;
            return;
        }
        
        int r = idx; //오른쪽 방향 탐색
        int rCnt = 0;
        while(true){
            if(arr[r] != target[r]) break;
            r++;
            if(r>target.length-1) r=0;
            rCnt++;
        }
        
        int l = idx; //왼쪽 방향 탐색
        int lCnt = 0;
        while(true){
            if(arr[l] != target[l]) break;
            l--;
            if(l<0) l=target.length-1;
            lCnt++;
        }
        
        int cR = change(r); //알파벳 변환
        dfs(depth+1, r, cnt + rCnt + cR); //오른쪽 이동
        arr[r] = 'A';
        
        int cL = change(l); //알파벳 변환
        dfs(depth+1, l, cnt + lCnt + cL); //왼쪽 이동
        arr[l] = 'A';
    }
}