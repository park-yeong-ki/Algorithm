import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Integer> stack = new Stack<>();
        
        int cnt = 0;
        boolean flag = true;
        for(int i=0; i<number.length(); i++){
            int n = number.charAt(i) - '0';
            if(stack.isEmpty() || stack.peek() >= n){
                stack.push(n);
            }else{
                if(flag){ //아직 k개를 제거하지 않은 경우
                    while(!stack.isEmpty() && stack.peek() < n){
                        stack.pop();
                        cnt++;
                        if(cnt == k){ //모두 제거한 경우
                            flag = false;
                            break;
                        }
                    }
                }
                
                stack.push(n);
            }            
        }
        
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        
        sb.reverse(); //역순으로 변경
        for(int i=0; i<k-cnt; i++){ //제거 못한 개수가 남아있는 경우 뒤에서부터 제거
            sb.deleteCharAt(sb.length()-1);
        }
        
        return sb.toString();
    }
}