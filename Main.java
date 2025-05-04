import java.util.*;

public class Main{
    static List<int[]> path = new ArrayList<>();
    static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main( String[] args ){
        char[][] test = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solve(test);
        for (char[] row: test){
            for (char ch: row){
                System.out.print(ch+" ");
            }
            System.out.println();
        }
    }
    public static void solve(char[][] board) {
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == 'O'){
                    path.clear();

                    if (bfs(board, i, j)){ // if didn't reach bounds
                        for (int[] cell: path)
                            board[cell[0]][cell[1]] = 'X';
                    }
                }
            }
        }
    }
    public static boolean bfs(char[][] board, int x, int y){
        boolean reachedBounds = false;
        int rows = board.length, cols = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        path.add(new int[]{x, y});

        while (!queue.isEmpty()){
            for (int i = queue.size(); i > 0; i--){
                int[] popped = queue.poll();

                for (int[] dir: directions){
                    int newI = popped[0]+dir[0], newJ = popped[1]+dir[1];

                    if (newI < 0 || newI >= rows || newJ < 0 || newJ >= cols){
                        reachedBounds = true;
                        continue;
                    }
                    if (board[newI][newJ] == 'O'){
                        int[] newOCell = new int[] {newI, newJ};
                        path.add(newOCell);
                        queue.add(newOCell);
                    }
                }
            }
        }
        return !reachedBounds;
    }
}