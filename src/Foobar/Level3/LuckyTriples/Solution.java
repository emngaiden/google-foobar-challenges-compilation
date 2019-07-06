package Foobar.Level3.LuckyTriples;

public class Solution {

    public static int solution(int[] n){
        return new Solution().solve(n);
    }

    public int solve(int[] list) {
        if (list.length < 3) {
            return 0;
        }
        int foundCounter[] = new int[list.length];
        int ltcounter = 0;
        for (int i = 0; i < foundCounter.length; i++) {
            for (int j = 0; j < i; j++) {
                if (list[i] % list[j] == 0) {
                    foundCounter[i]++;
                    ltcounter += foundCounter[j];
                }
            }
        }
        return ltcounter;
    }
}
