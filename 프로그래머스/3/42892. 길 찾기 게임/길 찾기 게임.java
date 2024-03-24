import java.util.*;

class Solution {
    static class Node{
        int x, y, n;
        Node left, right;
        
        Node(int x, int y, int n){
            this.x = x;
            this.y = y;
            this.n = n;
        }
    }
    static int[][] ans;
    static int preorder, postorder;
    public int[][] solution(int[][] nodeinfo) {
        int N = nodeinfo.length;
        
        Node[] nArr = new Node[N];
        for(int i=0; i<N; i++){
            nArr[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1);
        }
        
        Arrays.sort(nArr, new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2){
                return Integer.compare(o2.y, o1.y);
            }
        });
        
        for(int i=1; i<N; i++){
            Node parent = nArr[0];
            Node child = nArr[i];
            while(true){
                if(parent.x < child.x){
                    if(parent.right == null){
                        parent.right = child;
                        break;
                    }
                    parent = parent.right;
                }else if(parent.x > child.x){
                    if(parent.left == null){
                        parent.left = child;
                        break;
                    }
                    parent = parent.left;
                }
            }
        }
        
        preorder = postorder = 0;
        ans = new int[2][N];
        dfs(nArr[0]);
        
        return ans;
    }
    
    static void dfs(Node node){
        if(node == null) return;
        
        ans[0][preorder++] = node.n;
        dfs(node.left);
        dfs(node.right);
        ans[1][postorder++] = node.n;
    }
}