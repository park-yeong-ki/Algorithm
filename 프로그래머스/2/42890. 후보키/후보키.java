import java.util.*;
import java.lang.*;

class Solution {
    static int num, row, col;
    static String[][] arr;
    static List<String> ans;
    
    public int solution(String[][] relation) {
        arr = relation;
        row = relation.length;
        col = relation[0].length;
                
        ans = new ArrayList<>(); //완성된 후보키 저장
        //조합
        num = 1;
        while(num <= col){
            comb(0, 0, "");
            num++;
        }
        
        return ans.size();
    }
    
    
    static void comb(int cnt, int start, String str){
        if(cnt == num){
            System.out.println(str);
            for(int i=0; i<ans.size(); i++){
                int count = 0;
                for(int j=0; j<ans.get(i).length(); j++){
                    if(str.contains(String.valueOf(ans.get(i).charAt(j)))){
                        count++;
                    }
                }
                if(count == ans.get(i).length()){
                    return;
                }
            }
            
            
            StringBuilder sb = new StringBuilder();
            Set<String> set = new HashSet<>();
            boolean flag = true;
            
            outer:
            for(int i=0; i<row; i++){
                for(int j=0; j<num; j++){
                    sb.append(arr[i][str.charAt(j)-'0']);
                }
                
                if(set.contains(sb.toString())){
                    flag = false;
                    break outer;
                }else{
                    set.add(sb.toString());
                    sb.setLength(0);
                }
            }
            
            if(flag){ //후보키인 경우
                ans.add(str); //후보키 저장
                System.out.println("성공");
            }
            
            return;
        }
        
        for(int i=start; i<col; i++){
            comb(cnt+1, i+1, str+i);
        }
    }
}