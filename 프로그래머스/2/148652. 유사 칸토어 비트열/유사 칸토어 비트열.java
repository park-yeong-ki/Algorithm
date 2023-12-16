class Solution {
    static String str1 = "11011";
    static String str0 = "00000";
    static StringBuilder sb;
    
    public int solution(int n, long l, long r) {
        recursion(0, l-1, r-1, 0, 0);
        int ans = 0;
        for(int i=0; i<sb.length(); i++){
            if(sb.charAt(i) == '1') ans++;
        }
        return ans;
    }
    
    static void recursion(int depth, long l, long r, int ml, int mr){
        System.out.println(l + "/" + r + "/" + ml + "/" + mr);
        if(l == 0 && r == 0){ 
            sb = new StringBuilder();
            for(int i=ml; i<=mr; i++){
                sb.append(str1.charAt(i));
            }
            
            return;
        }
        
        recursion(depth+1, l/5, r/5, (int)(l%5), (int)(r%5));
        if(depth == 0) return;
        change(); //문자열을 치환한다.
        
        int start = (int)(l - l) * 5 + ml; //문자열 범위 조정
        int end = (int)(r - l) * 5 + mr;
        int length = sb.length();
        sb.append(sb.substring(start,end+1)); 
        sb.delete(0, length);    
    }
    
    static void change(){
        int length = sb.length();
        for(int i = 0; i<length; i++){ 
            if(sb.charAt(i) == '1'){
                sb.append(str1);
            }else{
                sb.append(str0);
            }
        }
        sb.delete(0, length);
    }
}