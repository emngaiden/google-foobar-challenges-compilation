package Foobar.Level2.IonFluxRelabeling;

public class Solution {
    
    int highest;
    int h;
    public final int[] maxs;
    boolean first_iteration=false;
    
    public static int[] solution(int h, int[] q){
        return new Solution(h).solve(q);
    }

    public Solution(int h){
        maxs=new int[h];
        this.highest=(int)Math.pow(2, h)-1;
        this.h=h;
    }
    
    public int[] solve(int[] q) { 
        int[] ret=new int[q.length];
        for(int i=0; i<q.length; i++){
            ret[i]=calculate(q[i],highest, -1);
        };
        return ret;
    } 

    private int calculate(int i, int j, int k) {
        if(i>=highest){
            return -1;
        }
        int rightRoot=j-1;
        int leftRoot=rightRoot/2;
        if(i==j){
            return k;
        }
        if(i==leftRoot || i==rightRoot){
            return j;
        }
        if (i<leftRoot){
            return calculate(i,leftRoot,j);
        }
        if(i>leftRoot && i<rightRoot){
            return calculateToRight(i,leftRoot,j,leftRoot);
        }
        return -1;
    }

    private int calculateToRight(int i, int j, int k, int d) {
        if(i==j){
            return k;
        }
        int rightRoot=j-1;
        int leftRoot=rightRoot/2;
        if(i==rightRoot+d || i==leftRoot+d){
            return j+d;
        }
        if(i<(leftRoot+d)){
            return calculateToRight(i, leftRoot, j+d,d);
        }
        if(i>leftRoot+d && i<rightRoot+d){
            return calculateToRight(i,leftRoot, j+d,leftRoot+d);
        }
        return -1;
    }

}
