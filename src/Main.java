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
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return "Invalid maze";
        }
        StringBuilder path = new StringBuilder();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        findClearPathHelper(maze, 0, 0, path, visited);
        return path.toString();
    }

    public static boolean findClearPathHelper(String[][] maze, int row, int col, StringBuilder path, boolean[][] visited) {
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length || visited[row][col] || maze[row][col].equals("#")) {
            return false;
        }

        int pathLength = path.length(); // Store the current length of the path
        path.append("(").append(row).append(",").append(col).append(") → ");
        visited[row][col] = true;

        if (row == maze.length - 1 && col == maze[0].length - 1) {
            int rowDigits = String.valueOf(row).length();
            int colDigits = String.valueOf(col).length();
            path.deleteCharAt(pathLength + rowDigits + colDigits + 4);
            return true;
        }

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            if (findClearPathHelper(maze, newRow, newCol, path, visited)) {
                return true;
            }
        }
        // Backtrack by removing the last "(row,col) → " part added
        path.setLength(pathLength);
        visited[row][col] = false;
        return false;
    }
    public static void seeMaze(String[][] maze){
        for (String[] strings : maze) {
            for (int c = 0; c < maze[0].length; c++) {
                System.out.print(strings[c]);
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

        ArrayList<String> fileData = new ArrayList<>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.getFirst().length();

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