import java.util.*;

class Solution {
    static class Node{
        Node prev, next;
        boolean status;
    }
    public String solution(int n, int k, String[] cmd) {
        Node[] nArr = new Node[n];
        nArr[0] = new Node();
        for(int i=1; i<n; i++){
            nArr[i] = new Node();
            nArr[i].prev = nArr[i-1];
            nArr[i-1].next = nArr[i];
        }
        
        Stack<Node> stack = new Stack<>();
        
        Node current = nArr[k];
        char order;
        int X;
        Node prev, next, remove;
        for(int i=0; i<cmd.length; i++){
            order = cmd[i].charAt(0);
            
            switch(order){
                case 'U':
                    X = Integer.parseInt(cmd[i].substring(2));
                    while(X-- > 0){
                        current = current.prev;
                    }
                    break;
                case 'D':
                    X = Integer.parseInt(cmd[i].substring(2));
                    while(X-- > 0){
                        current = current.next;
                    }
                    break;
                case 'C':
                    prev = current.prev;
                    next = current.next;
                    if(prev != null) prev.next = next;
                    if(next != null) next.prev = prev;
                    
                    current.status = true;
                    stack.push(current);
                    
                    current = next == null ? prev : next;
                    break;
                case 'Z':
                    remove = stack.pop();
                    remove.status = false;
                    
                    prev = remove.prev;
                    next = remove.next;
                    
                    if(prev != null) prev.next = remove;
                    if(next != null) next.prev = remove;
                    break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            if(nArr[i].status == false) sb.append("O");
            else sb.append("X");
        }
        
        return sb.toString();
    }
}