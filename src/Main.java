import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[][] maze = getMaze("src/maze");
        System.out.println(findClearPath(maze));
        seeMaze(maze);
    }

    public static String findClearPath(String[][] maze) {
        int up;
        int down;
        int left;
        int right;
        StringBuilder path = new StringBuilder();
        for(int row = 0;row < maze.length;row++){
            for (int col = 0;col < maze[0].length;col++){
                if (row - 1 >= 0){
                    up = row;
                    if (maze[up][col].equalsIgnoreCase(".")){
                        path.append("(").append(up).append(",").append(col).append(") → ");
                        maze[up][col] = "#";
                    }
                }
                if (row + 1 <= maze.length){
                    down = row;
                    if (maze[down][col].equalsIgnoreCase(".")){
                        path.append("(").append(down).append(",").append(col).append(") → ");
                        maze[down][col] = "#";
                    }
                }
                if (col - 1 >= 0){
                    left = col;
                    if (maze[row][left].equalsIgnoreCase(".")){
                        path.append("(").append(row).append(",").append(left).append(") → ");
                        maze[row][left] = "#";
                    }
                }
                if (col + 1 <= maze[0].length){
                    right = col;
                    if (maze[row][right].equalsIgnoreCase(".")){
                        path.append("(").append(row).append(",").append(right).append(") → ");
                        maze[row][right] = "#";
                    }
                }
                if (row == maze.length - 1 && col == maze[0].length - 1){
                    int pathLength = path.length();
                    path = new StringBuilder(path.substring(0, pathLength - 3));
                }
            }
        }
        return path.toString();
    }
    public static void seeMaze(String[][] maze){
        for (int r = 0; r < maze.length;r++){
            for (int c = 0; c < maze[0].length;c++){
                System.out.print(maze[r][c]);
            }
            System.out.println();
        }
    }

    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;

    }
}