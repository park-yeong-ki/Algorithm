import java.util.*;

class Solution {
    static int[] arr;
    static boolean[] visited;
    static Set<Integer> set;
    
    public int solution(String numbers) {
        //한자리 숫자로 모두 변환
        arr = new int[numbers.length()];
        for(int i=0; i<numbers.length(); i++){
            arr[i] = numbers.charAt(i) - '0';
        }
        visited = new boolean[numbers.length()];
        set = new HashSet<>();
        
        //nP1 ~ nPn까지 값을 중복을 제거하여 저장
        for(int i=1; i<=arr.length; i++){
            perm(0, i, 0);
        }
        
        List<Integer> list = new ArrayList<>(set);
        
        int ans = 0;
        for(int n : list){ //소수 판별
            if(n < 2) continue; //n이 0, 1인 경우 제외
            
            boolean flag = true;
            for(int i=2; i<=Math.sqrt(n); i++){
                if(n % i == 0){
                    flag = false;
                    break;
                }               
            }
            
            if(flag) ans++;
        }
        
        return ans;
    }
    
    static void perm(int idx, int c, int num){
        if(idx == c){ //nPc값만큼 뽑기
            set.add(num);
            return;
        }
        
        for(int i=0; i<arr.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
            perm(idx+1, c, num*10 + arr[i]);
            visited[i] = false;
        }
    }
}