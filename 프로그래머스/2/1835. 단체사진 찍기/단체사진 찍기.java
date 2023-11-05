import java.util.*;

class Solution {
    static char[] arr = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static Map<Character, Integer> map;
    static boolean[] visited;
    static String[] condition;
    static int ans;
     
    public int solution(int n, String[] data) {
        condition = data;
        map = new HashMap<>();
        visited = new boolean[arr.length];
        ans = 0;
        perm(0); //알파벳 순열 -> 맵을 통해 각 알파벳의 위치를 저장
        
        return ans;
    }
    
    static boolean isPossible(){
        for(int i=0; i<condition.length; i++){
            char a1 = condition[i].charAt(0);
            char a2 = condition[i].charAt(2);
            char o = condition[i].charAt(3);
            int n = condition[i].charAt(4) - '0';
            
            int d = Math.abs(map.get(a1) - map.get(a2)) - 1; //조건 내 알파벳 사이의 거리
            
            switch(o){ //연산자의 부호에 따라 조건을 나누어 부합하지 못하면 false 반환
                case '<':
                    if(d >= n) return false;
                    break;
                case '=':
                    if(d != n) return false;
                    break;
                case '>':
                    if(d <= n) return false;
                    break;
            }
            
        }
        
        return true; //모든 조건에 부합하는 경우
    }
    
    static void perm(int idx){
        if(idx == arr.length){
            if(isPossible()) ans++; //완성된 순열이 조건을 통과하면 정답에 추가
            return;
        }
        
        for(int i=0; i<arr.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
            map.put(arr[i], idx);
            perm(idx+1);
            visited[i] = false;
        }
    }
}