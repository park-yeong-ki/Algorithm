import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        m = change(m);
        
        String[] play = new String[musicinfos.length];
        String[] title = new String[musicinfos.length];
        int[] time = new int[musicinfos.length];
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<musicinfos.length; i++){
            String[] sArr = musicinfos[i].split(","); //문자열 쪼개기 시작, 끝, 제목, 악보 순
            String[] start = sArr[0].split(":");
            String[] end = sArr[1].split(":");
            sArr[3] = change(sArr[3]);
            
            int minute = (Integer.parseInt(end[0]) - Integer.parseInt(start[0])) * 60; //시간 구하기
            minute += Integer.parseInt(end[1]) - Integer.parseInt(start[1]);
            
            int j = 0;
            while(j < minute){ //구한 시간만큼 악보정보를 반복
                sb.append(sArr[3].charAt(j % sArr[3].length())); //악보길이 초과시 처음부터 반복
                j++;
            }
            
            time[i] = minute;
            title[i] = sArr[2];
            
            play[i] = sb.toString();
            sb.setLength(0);
        }
        
        String ans = "(None)";
        int max = Integer.MIN_VALUE;
        for(int i=0; i<play.length; i++){ //문자열이 포함되어있으면 제목 저장
            if(play[i].contains(m) && max < time[i]){ //재생시간이 제일 긴 음악제목 입력 만약 재생시간이 같으면 먼저 입력된 순
                ans = title[i];
                max = Math.max(max, time[i]);
            }
        }
        
        return ans;
    }
    
    static String change(String str){ //#이 붙은 음을 소문자로 변환
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(ch == '#'){
                sb.setCharAt(sb.length()-1, (char)(sb.charAt(sb.length()-1) + 32));
            }else{
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }
}