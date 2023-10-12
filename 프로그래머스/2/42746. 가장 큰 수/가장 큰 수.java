import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        //문자열로 변환
        String[] sArr = new String[numbers.length];
        for(int i=0; i<sArr.length; i++){
            sArr[i] = String.valueOf(numbers[i]);
        }
        
        //이어붙인값을 비교하며 정렬
        Arrays.sort(sArr, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                return Integer.parseInt(s2+s1) - Integer.parseInt(s1+s2);
            }
        });
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<sArr.length; i++){
            sb.append(sArr[i]);
        }
        
        return sb.toString().charAt(0) == '0' ? "0" : sb.toString();
    }
}