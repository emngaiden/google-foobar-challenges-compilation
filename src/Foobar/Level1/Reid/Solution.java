package Foobar.Level1.Reid;

public class Solution {

    //Save the position (n) for wich the string is valid 
    public long array_pos = 1;
    //Save the last prime calculated
    public long last_prime = 11;
    //Initial state of the string of primes with the first 5 primes
    private String primes = "235711";
    
    public static String solution(int n) {
        return new Solution().solve(n);
    }

    public String solve(int n) {
        //base case, no ned to generate more primes
        if (n == 0) {
            return "23571";
        }
        this.solveSize(n);
        //Gets a substring 5 characters long from the resulting string
        return primes.substring(1, 6);
    }

    private static boolean isPrime(long n) {
        if (n % 2 == 0) {
            return n == 2;
        }
        if (n % 3 == 0) {
            return n == 3;
        }
        int step = 4, m = (int) Math.sqrt(n) + 1;
        for (int i = 5; i < m; step = 6 - step, i += step) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private long calculateNextPrime() {
        long aux = last_prime + 1;
        while (!isPrime(aux)) {
            aux++;
        }
        this.last_prime = aux;
        return aux;
    }
    
    //Visualization:
    /*
        n=5;
        pos=2: 235711
        pos=3: 3571113
        pos=4: 571113
        pos=5: 7111317
    */
    private void extendString(int n) {
        //repeat untill desired position is reached
        do {
            //If the string is not large enought, generate the next prime and
            //concatenate it to the string
            if (this.primes.length() < 7) {
                long nextprime = calculateNextPrime();
                String aux = Long.toString(nextprime);
                this.primes += aux;
            }
            //trim the string so it doesnt overflow the memory
            this.primes = this.primes.substring(1, primes.length());
            //increase the position
            this.array_pos++;
        } while (n > array_pos);
    }

    //If n is greater than the actual valid position, 
    //extends the string of primes
    private void solveSize(int n) {
        if (n > array_pos) {
            extendString(n);
        }
    }
}
