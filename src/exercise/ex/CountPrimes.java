package exercise.ex;

public class CountPrimes {

    public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrime(i))
                count++;
        return count;
    }

    public int countPrimesNew(int n) {
        boolean[] nonPrimes = new boolean[n + 1];
        //诶氏筛法
        for (int i = 2; i < n; i++) {
            if (nonPrimes[i]) continue;
            int end = n / i;
            for (int j = i;//小于x^2的数已经被其他数标记过了！
                 j <= end; j++)
                nonPrimes[i * j] = true;//合数
        }
        int count = 0;
        for (int i = 2; i < n; i++)
            if (!nonPrimes[i]) count++;
        return count;
    }

    public static boolean isPrime(int n) {
        int end = (int) Math.sqrt(n) + 1;
        for (int i = 2; i < end; i++)
            if (n % i == 0) return false;
        return n > 1;
    }
}
