package exercise.ex;

public class LCM {

    public static void main(String[] args) {
        System.out.println(gcd(1470, 5415));
        System.out.println(lcm(42));
    }

    public static long lcm(long n) {
        long res = 1;
        for (int i = 2; i <= n; i++)
            res = lcm(i, res);
        return res;
    }

    public static long lcm(long[] nums) {
        long res = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; i++)
            res = lcm(nums[i], res);
        return res;
    }

    public static long lcm(long a, long b) {
        return Math.multiplyExact(a, b) / gcd(a, b);
    }

    public static long gcd(long a, long b) {
        // a = n * b + r
        // 0 <= r <= b - 1
        // gcd(a, b) = gcd(b, r)
        while (b != 0) {
            long ab = a % b;
            a = b;
            b = ab;
        }
        return a;
    }
}
