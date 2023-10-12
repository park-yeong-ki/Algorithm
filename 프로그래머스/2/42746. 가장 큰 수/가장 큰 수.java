import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        //문자열로 변환
        String[] sArr = new String[numbers.length];
        for(int i=0; i<sArr.length; i++){
            sArr[i] = String.valueOf(numbers[i]);
        }
        
        //정렬하여 이어붙인다
        Arrays.sort(sArr, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                int max = Math.max(s1.length(), s2.length());
                
                int num1;
                int num2;
                for(int i=0; i<2*max; i++){ //최대 두 문자열중 길이가 긴 문자열 기준으로 비교
                    if(s1.length() <= i) num1 = s1.charAt(i % s1.length());
                    else num1 = s1.charAt(i);
                    if(s2.length() <= i) num2 = s2.charAt(i % s2.length());
                    else num2 = s2.charAt(i);
                    
                    if(num1 != num2){ //비교 자리수의 값이 같지 않다면
                        return num2 - num1; //내림차순 정렬
                    }
                }
                
                return 0; //이외에는 그대로
            }
        });
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<sArr.length; i++){
            sb.append(sArr[i]);
        }
        
        String ans = sb.toString();
        if(ans.replace("0", "").length() == 0) return "0";
        
        return ans;
    }
}