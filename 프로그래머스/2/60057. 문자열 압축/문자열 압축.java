class Solution {
    public int solution(String s) {        
        StringBuilder sb = new StringBuilder();
        int min = Integer.MAX_VALUE;
        int idx = 1;
        while(idx <= s.length()){
            String prev = s.substring(0, idx);
            int cnt = 1;
            int last = 0;
            for(int i=idx; i<s.length(); i+=idx){
                if(i+idx > s.length()){
                    last = i;
                    break;
                }
                
                String current = s.substring(i, i+idx);
                if(prev.equals(current)){
                    cnt++;
                }else{
                    if(cnt == 1) sb.append(prev);
                    else sb.append(cnt).append(prev);
                    cnt = 1;
                }
                prev = current;
            }
            
            if(cnt == 1) sb.append(prev);
            else sb.append(cnt).append(prev);
            
            if(last != 0) sb.append(s.substring(last, s.length()));
            
            min = Math.min(min, sb.length()); //최소값 갱신
                                    
            sb.setLength(0);
            idx++;
        }
        
        return min;
    }
}