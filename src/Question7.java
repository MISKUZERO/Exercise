import java.util.Scanner;

public class Question7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int l = scanner.nextInt();
        int r = scanner.nextInt();
        int count = 0;
        for (int i = l; i <= r; i++)
            for (int j = i; j >= 5; j >>= 1)
                if ((j & 0b111) == 5) {
                    count++;
                    break;
                }
        System.out.println(r - l + 1 - count);
    }
}
