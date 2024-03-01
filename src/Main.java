import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[][] maze = getMaze("src/maze");
        System.out.println(findClearPath(maze));
    }

    public static String findClearPath(String[][] maze) {
        String path = "(0,0) → ";
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;
        for(int row = 0;row < maze.length;row++){
            for (int col = 0;col < maze[0].length;col++){
                if (!(row - 1 < 0)){
                    up = row - 1;
                }
                if (!(row + 1 > 0)){
                    down = row - 1;
                }
                if (!(col - 1 < 0)){
                    left = col - 1;
                }
                if (!(col + 1 > 0)){
                    right = col - 1;
                }
                if (maze[up][col].contains(".")){
                    row = up;
                    path += "(" + row + "," + col + ") → ";
                }
                else if (maze[down][col].contains(".")){
                    row = down;
                    path += "(" + row + "," + col + ") → ";
                }
                else if (maze[row][left].contains(".")){
                    col = left;
                    path += "(" + row + "," + col + ") → ";
                }
                else if (maze[up][right].contains(".")){
                    col = right;
                    path += "(" + row + "," + col + ") → ";
                }
            }
        }
        return path;
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