class Solution {
    static int N, ans;
    static int[] arr;
    
    public int solution(int n) {
        N = n;
        arr = new int[n]; //좌표를 저장할 배열 -> 인덱스가 행, 원소가 열
        ans = 0;
        dfs(0);
        return ans;
    }
    
    static boolean isPossible(int r, int c){ //퀸을 배치할 수 있는지 탐색
        for(int i=0; i<r; i++){ //이전 행까지의 원소와 비교
            int row = i;
            int col = arr[i];
            
            if(col == c) return false; //열 비교
            if(Math.abs(row - r) == Math.abs(col - c)) return false; //대각선 비교
        }
        
        return true;
    }
    
    static void dfs(int idx){
        if(idx == N){ //n개의 퀸을 전부 배치한 경우
            ans++;
            return;
        }
        
        for(int i=0; i<N; i++){ //각 행의 열마다 뽑는다.
            if(!isPossible(idx, i)) continue; //놓을 수 없는 위치인 경우
            arr[idx] = i;
            dfs(idx+1);
        }
    }
}