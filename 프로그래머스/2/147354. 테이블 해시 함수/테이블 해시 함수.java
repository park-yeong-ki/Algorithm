import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, new Comparator<int[]>(){ 
            @Override
            public int compare(int[] a, int[] b){ 
                if(a[col-1] == b[col-1]){ //col번째 칼럼이 동일하면 첫 번째 칼럼으로 내림차순 정렬
                    return Integer.compare(b[0], a[0]);
                }
                return Integer.compare(a[col-1], b[col-1]); //col번째 컬럼으로 오름차순정렬
            }
        });
        
        int ans = 0;
        for(int i=row_begin-1; i<=row_end-1; i++){ //S_i 값 구하기
            int sum = 0;
            for(int j=0; j<data[i].length; j++){
                sum += data[i][j] % (i+1);
            }
            ans ^= sum; //S_I 누적하면서 xor 비트연산
        }
        
        return ans;
    }
}