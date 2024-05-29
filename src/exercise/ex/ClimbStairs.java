package exercise.ex;

public class ClimbStairs {

    public static void main(String[] args) {
        System.out.println(new ClimbStairs().climbStairs(91));
    }

    public long climbStairs(int n) {
        return dfs(n, 0, new long[n + 1]);
    }

    public static long dfs(int n, long count, long[] dp) {
        if (n < 0)
            return count;
        if (n == 0)
            return Math.incrementExact(count);
        if (dp[n] != 0)
            return dp[n];
        count = Math.addExact(dfs(n - 1, count, dp), dfs(n - 2, count, dp));
        dp[n] = count;
        return count;
    }

    public long climbStairsNew(int n) {
        int a1 = 1, a2 = 2;
        if (n == 1)
            return a1;
        if (n == 2)
            return a2;
        int a3 = a1 + a2;
        for (int i = 3; i < n; i++) {
            a1 = a2;
            a2 = a3;
            a3 = a1 + a2;
        }
        return a3;
    }
}
