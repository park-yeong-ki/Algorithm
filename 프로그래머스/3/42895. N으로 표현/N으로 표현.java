import java.util.*;

class Solution {
    public int solution(int N, int number) {        
        Set<Integer>[] numbers = new Set[9];
        for(int i=1; i<=8; i++){
            numbers[i] = new HashSet<>();
        }
                
        int num = N;
        for(int i=1; i<=8; i++){
            for(int j=1; j<=i-1; j++){
                for(int n1 : numbers[j]){
                    for(int n2 : numbers[i-j]){
                        numbers[i].add(n1 + n2);
                        numbers[i].add(n1 - n2);
                        numbers[i].add(n1 * n2);
                        numbers[i].add(n1 / n2);
                    }
                }
            }
            
            numbers[i].remove(0);
            numbers[i].add(num);
            
            if(numbers[i].contains(number)) return i;
            
            num = num * 10 + N;
        }
        
        return -1;
    }
}