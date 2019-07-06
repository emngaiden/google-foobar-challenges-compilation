package Foobar.Level3.BoomBaby;

import java.math.BigInteger;

public class Solution {

    public static String solution(String x, String y) {
        return new Solution().solve(x, y);
    }

    //If looked as a binary tree, going down the left node when the
    //M bombs generate F bombs, and going right when F generates M,
    //the results mirror themselves, so we can look for M,F or F,M.
    //Also, if we go down on all the nodes on the left from any point
    //M remains constant, and if we go right F remains constant
    private String solve(String x, String y) {
        BigInteger M = new BigInteger(x), F = new BigInteger(y);
        //Using varibale c to count the generations
        BigInteger c = BigInteger.ZERO;
        //Save the desired number of bombs in a Result object
        Result desired = new Result(M, F);
        //If the input is 1 , 1, return 0 for the generation 0
        if (M.compareTo(BigInteger.ONE) == 0 && F.compareTo(BigInteger.ONE) == 0) {
            return "0";
        }
        //Fact 1: if F or M equals 1, then the result for that node equals
        // M+F-2, adding any generation we calculated before we reached that node
        if (M.compareTo(BigInteger.ONE) == 0 || F.compareTo(BigInteger.ONE) == 0) {
            return M.add(F).subtract(new BigInteger("2")).toString();
        }
        if (desired.isInvalid()) {
            return "impossible";
        }
        //Base case F=1, M=1
        Result base = new Result(BigInteger.ONE, BigInteger.ONE);
        Result aux = desired;
        //We could generate numbers and search from the base, but its faster
        //to start from the base and going up.
        //We can divide the biggest number over the lowest number and we get 
        //the number of generations before we need to change direction in our tree
        BigInteger mult;
        while (!aux.equals(base)) {
            if (aux.M.compareTo(BigInteger.ONE) == 0 || aux.F.compareTo(BigInteger.ONE) == 0) {
                return aux.M.add(aux.F).subtract(new BigInteger("2")).add(c).toString();
            }
            if (aux.isInvalid()) {
                return "impossible";
            }
            if (aux.getBigger() == 1) {
                mult = aux.M.divide(aux.F);
                aux.M = aux.M.subtract(mult.multiply(aux.F));
            } else {
                mult = aux.F.divide(aux.M);
                aux.F = aux.F.subtract(mult.multiply(aux.M));
            }
            c = c.add(mult);
        }
        return c.toString();
    }

    private static boolean isEven(BigInteger b) {
        return b.getLowestSetBit() != 0;
    }

    //Represents a node in the tree, no need to waste memory saving childs
    public static class Result {

        BigInteger M, F;

        public Result(BigInteger M, BigInteger F) {
            this.M = M;
            this.F = F;
        }

        //Fact 2: the node is invalid if
        public boolean isInvalid() {
            return //F and M are equals
                    (M.compareTo(F) == 0 && M.compareTo(BigInteger.ONE) == 0)
                    //Both F and M are even numbers
                    || (isEven(M) && isEven(F))
                    //If both numbers are multiples
                    || (M.mod(F).compareTo(BigInteger.ZERO) == 0 || F.mod(M).compareTo(BigInteger.ZERO) == 0);
        }

        //Given that the we reflected the "binary tree", F,M = M,F
        public boolean equals(Result result) {
            boolean first = F.compareTo(result.F) == 0 && M.compareTo(result.M) == 0;
            boolean second = F.compareTo(result.M) == 0 && M.compareTo(result.F) == 0;
            return first || second;
        }

        //returns which number is bigger (F or M)
        public int getBigger() {
            return M.compareTo(F);
        }
    }
}
