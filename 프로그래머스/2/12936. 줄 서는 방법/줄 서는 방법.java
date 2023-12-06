class Solution {
    public int[] solution(int n, long k) {
        int[] ans = new int[n];
        boolean[] visited = new boolean[n]; //사용한 숫자를 표시할 배열
        
        long num = k;
        int idx = n;
        while(idx > 1){ //마지막 자리 수 전까지 반복
            long f = 1; //팩토리얼 값
            for(int i=idx - 1; i>=1; i--){
                f *= i;
            }
            
            long a = num / f; //몫
            long b = num % f; //나머지
            
            if(b == 0){ //나머지가 0이면
                a--; 
                b = f;
            }
            
            int cnt = 0;
            for(int i=0; i<visited.length; i++){
                if(visited[i]) continue;
                if(cnt == a){
                    ans[n - idx] = i+1; //값 입력
                    visited[i] = true; //방문 표시
                    break;
                }
                cnt++;
            }
            
            
            idx--;
            num = b; //다음 자리수를 이번 나머지 값으로 초기화
        }
        
        for(int i=0; i<visited.length; i++){ //마지막 남은 자리수
            if(visited[i]) continue;
            ans[n-1] = i+1;
        }
        
        return ans;
    }
}