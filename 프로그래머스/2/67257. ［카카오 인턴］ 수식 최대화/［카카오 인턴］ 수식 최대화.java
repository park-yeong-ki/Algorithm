import java.util.*;

class Solution {
    static char[] cArr;
    static char[] pArr;
    static boolean[] visited;
    static long max;
    static String origin;
    
    public long solution(String expression) {
        expression = expression.replace("+", "a");
        expression = expression.replace("-", "b");
        expression = expression.replace("*", "c");
        
        origin = expression;
        
        String s = expression.replaceAll("[0-9]", "");
        
        Set<Character> set = new HashSet();
        for(int i=0; i<s.length(); i++){
            set.add(s.charAt(i));
        }
        
        Character[] arr = set.toArray(new Character[0]);
        
        cArr = new char[arr.length];
        for(int i=0; i<arr.length; i++){
            cArr[i] = arr[i];
        }
                
        visited = new boolean[cArr.length];
        pArr = new char[cArr.length];
        max = Long.MIN_VALUE;
        
        perm(0); // 연산자 순열
        
        return max;
    }
    
    static String calculate(char ch, String str){  
        
        //해당 연산자가 존재하는지 확인 후 연산한 문자열 반환
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == ch){                
                //좌측 문자열, 우측 문자열 구하기
                int left = i-1;
                int right = i+1;
                
                StringBuilder sb = new StringBuilder();
                while(0 <= left){
                    if(str.charAt(left) == 'a' || str.charAt(left) == 'b' || str.charAt(left) == 'c') break; //다음 연산자를 만나면 종료
                    sb.append(str.charAt(left));
                    left--;
                }
                left++;
                
                long leftNum = Long.parseLong(sb.reverse().toString());
                sb.setLength(0);
                        
                while(right < str.length()){
                    if(str.charAt(right) == 'a' || str.charAt(right) == 'b' || str.charAt(right) == 'c') break; //다음 연산자를 만나면 종료
                    sb.append(str.charAt(right));
                    right++;
                }
                
                long rightNum = Long.parseLong(sb.toString());
                sb.setLength(0);
                                
                String prevS = str.substring(0, left);
                String nextS = str.substring(right, str.length());
                
                long result = 0;
                switch (ch){
                    case 'a':
                        result = leftNum + rightNum;
                        break;
                    case 'b':
                        result = leftNum - rightNum;
                        break;
                    case 'c':
                        result = leftNum * rightNum;
                        break;
                }
                
                sb.append(prevS).append(result).append(nextS);
                
                return sb.toString();
            }
        }
        
        return str;
    }
    
    static void perm(int idx){ 
        if(idx == cArr.length){
            String str = origin;
            
            //문자열 돌며 새로운 연산자 우선순위를 적용하여 최대값 갱신
            for(int i=0; i<pArr.length; i++){
                while(true){
                    String prev = str; 
                    str = calculate(pArr[i], str);
                    
                    if(prev.equals(str)) break; //더이상 변화가 없으면 루프문 종료
                }
            }
            
            max = Math.max(max, Math.abs(Long.parseLong(str))); //최대값 갱신
            
            return;
        }
        
        for(int i=0; i<cArr.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
            pArr[idx] = cArr[i];
            perm(idx+1);
            visited[i] = false;
        }
    }
}