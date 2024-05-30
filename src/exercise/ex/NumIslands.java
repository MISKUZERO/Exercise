package exercise.ex;

public class NumIslands {

    public static void main(String[] args) {
        char[][] chars = {
                {'0', '1', '1'},
                {'1', '0', '1', '1'},
                {'0', '1'}
        };
        System.out.println(new NumIslands().numIslands(chars));
    }

    public int numIslands(char[][] grid) {
        int length = grid.length, count = 0;
        boolean[][] visits = new boolean[length][];
        for (int i = 0; i < length; i++)
            visits[i] = new boolean[grid[i].length];
        for (int i = 0; i < length; i++) {
            int len = grid[i].length;
            for (int j = 0; j < len; j++)
                if (!visits[i][j] && grid[i][j] == '1') {
                    dfs(grid, visits, i, j, length);
                    count++;
                }
        }
        return count;
    }

    public static void dfs(char[][] grid, boolean[][] visits, int r, int c, int rLen) {
        if (r == -1 || r == rLen) return;
        int cLen = visits[r].length;
        if (c == -1 || c >= cLen || visits[r][c] || grid[r][c] == '0') return;
        visits[r][c] = true;//访问
        dfs(grid, visits, r - 1, c, rLen);
        dfs(grid, visits, r + 1, c, rLen);
        dfs(grid, visits, r, c - 1, rLen);
        dfs(grid, visits, r, c + 1, rLen);
    }

}
