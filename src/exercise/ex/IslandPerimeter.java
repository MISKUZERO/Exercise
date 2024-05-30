package exercise.ex;

public class IslandPerimeter {

    public static void main(String[] args) {
        System.out.println(new IslandPerimeter().islandPerimeter(
                new int[][]{
                        {0, 1}
                }
        ));
    }

    public int islandPerimeter(int[][] grid) {
        int res = 0;
        int length = grid.length;
        for (int i = 0; i != length; i++) {
            int len = grid[i].length;
            for (int j = 0; j != len; j++) {
                if (grid[i][j] == 1) {
                    int cur = 4;
                    if (i - 1 != -1 && grid[i - 1][j] == 1) //上
                        cur--;
                    if (j - 1 != -1 && grid[i][j - 1] == 1) //左
                        cur--;
                    if (i + 1 != length && grid[i + 1][j] == 1) //下
                        cur--;
                    if (j + 1 != len && grid[i][j + 1] == 1) //右
                        cur--;
                    res += cur;
                }
            }
        }
        return res;
    }
}
