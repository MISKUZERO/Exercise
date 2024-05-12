package exercise;

import java.util.ArrayList;
import java.util.Scanner;

public class Question34 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boxLen = Integer.parseInt(scanner.nextLine());
        int arrayCount = Integer.parseInt(scanner.nextLine());
        ArrayList<ArrayList<Integer>> arrays = new ArrayList<>();
        for (int i = 0; i < arrayCount; i++) {
            String[] split = scanner.nextLine().split(",");
            ArrayList<Integer> array = new ArrayList<>();
            for (String s : split) {
                array.add(Integer.parseInt(s));
            }
            arrays.add(array);
        }

        ArrayList<Integer> outputs = new ArrayList<>();
        int i = 0, len = arrays.size();
        boolean empty = false;
        while (!empty) {
            empty = true;
            for (ArrayList<Integer> array : arrays) {
                if (!array.isEmpty()) {
                    empty = false;
                    break;
                }
            }
            for (int j = 0; j < boxLen; j++) {
                if (arrays.get(i).isEmpty())
                    break;
                outputs.add(arrays.get(i).remove(0));
            }
            i++;
            if (i == len)
                i = 0;
        }

        System.out.println(outputs);
    }
}
