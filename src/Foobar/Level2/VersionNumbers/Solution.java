package Foobar.Level2.VersionNumbers;

public class Solution { 
    
    public String[] last_list;
    public String[] sorted;
    
    public static String[] solution(String[] l){
        return new Solution().solve(l);
    }
    
    private void sort(String[] l){
        this.last_list=l;
        this.sorted=l.clone();
        quickSort(sorted,0,l.length-1);
    }
    
    private void quickSort(String[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
    }
    
    private int partition(String[] arr, int left, int right){
        int i = left, j = right;
        String pivot = arr[((left + right) / 2)];
        while (i <= j) {
            while(compare(arr[i],pivot)==-1){
                i++;
            }
            while (compare(arr[j],pivot) == 1){
                j--;
            }
            if (i <= j) {
                String aux=arr[i];
                arr[i]=arr[j];
                arr[j]=aux;
                i++;
                j--;
            }
        }
        return i;
    }
    
    private int compare(String a, String b){
        int max=3;
        String[] as=a.split("\\."), bs=b.split("\\.");
        for(int i=0; i<max; i++){
            int pa, pb;
            try{
                pa=Integer.parseInt(as[i]);
                pb=Integer.parseInt(bs[i]);
            }catch(ArrayIndexOutOfBoundsException e){
                if(as.length<bs.length){
                    return -1;
                }
                if(as.length>bs.length){
                    return 1;
                }
                return 0;
            }
            if(pa<pb){
                return -1;
            }
            if(pa>pb){
                return 1;
            } 
        }
        return 0;
    }
    
    public String[] solve(String[] l){
        this.sort(l);
        return this.sorted;
    }
}
