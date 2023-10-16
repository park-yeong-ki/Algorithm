import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = recursion(p);
        return answer;
    }
    
    static String recursion(String w){       
        if(w.equals("")){ //빈 문자열인 경우, 빈 문자열 반환
            return "";
        }
        
        if(isRight(w)){ //이미 올바른 문자열이면 그대로 반환
            return w;
        }
        
        int num1 = 0;
        int num2 = 0;
        StringBuilder u = new StringBuilder();
        StringBuilder v = new StringBuilder();
        for(int i=0; i<w.length(); i++){
            if(w.charAt(i) == '(') num1++;
            else num2++;
            
            if(num1 == num2){ //더이상 분리할 수 없는 균형잡힌 괄호 문자열인 경우
                u.append(w.substring(0, i+1));
                v.append(w.substring(i+1, w.length()));
                break;
            }
        }
                
        if(isRight(u.toString())){ //u가 올바른 괄호 문자열이라면
            u.append(recursion(v.toString()));
            return u.toString();
        }else{ //u가 올바른 괄호 문자열이 아니라면
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(recursion(v.toString())).append(")");

            u.deleteCharAt(0);
            u.deleteCharAt(u.length()-1);
            
            for(int i=0; i<u.length(); i++){ //괄호방향 뒤집기
                u.setCharAt(i, u.charAt(i) == '(' ? ')' : '(');
            }
            
            sb.append(u.toString());
            return sb.toString();
        }
    }
    
    static boolean isRight(String str){
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == '('){
                stack.push(str.charAt(i));
            }else{
                if(!stack.isEmpty()){
                    stack.pop();
                }else{
                    return false;    
                }
            }
        }
        
        return true;
    }
}