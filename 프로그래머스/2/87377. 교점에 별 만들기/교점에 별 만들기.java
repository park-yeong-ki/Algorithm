import java.util.*;

class Solution {
    static int[][] arr;
    static int[][] cArr;
    static Set<String> set;
    static List<long[]> list;
    static long maxR, minR, maxC, minC;
    public String[] solution(int[][] line) {
        maxR = maxC = Long.MIN_VALUE;
        minR = minC = Long.MAX_VALUE;
        arr = line;
        cArr = new int[2][3]; //2개의 직선을 조합
        set = new HashSet<>(); //중복 제거를 하기위해 생성
        list = new ArrayList<>(); //좌표 저장할 리스트
        comb(0, 0); //직선C2
        
        char[][] cArr = new char[(int)(maxR-minR)+1][(int)(maxC-minC)+1]; //행의 크기만큼 배열 생성
        for(int i=0; i<cArr.length; i++){
            Arrays.fill(cArr[i], '.');
        }
        
        for(long[] l : list){
            long r = l[0];
            long c = l[1];
            cArr[(int)(r-minR)][(int)(c-minC)] = '*';
        }
        
        String[] ans = new String[cArr.length];
        StringBuilder sb = new StringBuilder();
        for(int i=cArr.length-1; i>=0; i--){
            for(int j=0; j<cArr[i].length; j++){
                sb.append(cArr[i][j]);
            }
            ans[cArr.length - 1 - i] = sb.toString();
            sb.setLength(0);
        }
        
        return ans;
    }
    
    static void makeStar(){
        long A = cArr[0][0];
        long B = cArr[0][1];
        long E = cArr[0][2];
        long C = cArr[1][0];
        long D = cArr[1][1];
        long F = cArr[1][2];

        long down = A*D - B*C;
        
        if(down == 0) return; //일치하거나 평행한 직선
        double X = (double)(B*F-E*D) / down;
        double Y = (double)(E*C-A*F) / down;
        
        if(X != (long)X || Y != (long)Y) return; //정수로만 표현되지 않는 경우
        String str = (long)X+","+(long)Y;
        if(set.contains(str)) return; //이미 존재하는 좌표인 경우
        set.add(str);
        minR = (long)Math.min(minR, Y);
        maxR = (long)Math.max(maxR, Y);
        minC = (long)Math.min(minC, X);
        maxC = (long)Math.max(maxC, X);
        list.add(new long[]{(long)Y, (long)X});
    }
    
    static void comb(int cnt, int start){
        if(cnt == 2){
            makeStar();
            return;
        }
        
        for(int i=start; i<arr.length; i++){
            cArr[cnt] = arr[i];
            comb(cnt+1, i+1);
        }
    }
}