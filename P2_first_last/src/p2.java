import java.io.*;
import java.util.*;

public class p2 {

    // 2D array to represent the maze
    private static Tile[][] maze;
    private static int numRows, numCols;

    public static void main(String[] args) {
        // Print a welcome message indicating the program is starting
        System.out.println("Maze Solver - p2");

        // Specify the map file to be read and load it
        readMap("Test Case");

        // Solve the maze using a stack-based approach (DFS)
        solveWithStack();
    }

    // Method to read the maze from a file
    private static void readMap(String filename) {
        try {
            File file = new File(filename);  // File object for the map file
            Scanner scanner = new Scanner(file);  // Scanner to read the file

            // Read the maze dimensions
            numRows = scanner.nextInt();
            numCols = scanner.nextInt();
            int numRooms = scanner.nextInt();  // This is read but not currently used

            // Initialize the maze as a 2D Tile array
            maze = new Tile[numRows][numCols];

            // Read each row of the maze and populate the tiles
            for (int i = 0; i < numRows; i++) {
                String row = scanner.next();  // Read each row as a string

                // Fill in each tile with its row, column, and corresponding character
                for (int j = 0; j < numCols; j++) {
                    char element = row.charAt(j);  // Get the character for the current position
                    maze[i][j] = new Tile(i, j, element);  // Create a Tile object for each cell
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }

    // Method to solve the maze using a stack (DFS)
    private static void solveWithStack() {
        Stack<Tile> stack = new Stack<>();  // Stack to store the maze tiles during traversal

        Tile start = findStartTile();  // Find the start tile
        stack.push(start);  // Push the start tile onto the stack

        while (!stack.isEmpty()) {
            Tile current = stack.pop();  // Pop the top tile from the stack
            int i = current.getRow();  // Get the current row
            int j = current.getCol();  // Get the current column

            // If we find the exit tile, print the success message
            if (maze[i][j].getType() == '$') {
                System.out.println("Exit found at (" + i + ", " + j + ")");
                return;
            }

            maze[i][j].setType('.');  // Mark the current tile as visited

            // Check neighboring tiles (up, down, left, right) and push valid tiles onto the stack
            if (i > 0 && maze[i - 1][j].getType() != '.') {
                stack.push(maze[i - 1][j]);  // Move up
            }
            if (i < numRows - 1 && maze[i + 1][j].getType() != '.') {
                stack.push(maze[i + 1][j]);  // Move down
            }
            if (j > 0 && maze[i][j - 1].getType() != '.') {
                stack.push(maze[i][j - 1]);  // Move left
            }
            if (j < numCols - 1 && maze[i][j + 1].getType() != '.') {
                stack.push(maze[i][j + 1]);  // Move right
            }
        }

        // If no exit is found after the stack is empty, print failure
        System.out.println("No solution found");
    }

    // Method to find the start tile ('@') in the maze
    private static Tile findStartTile() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (maze[i][j].getType() == '@') {  // Check for the start tile
                    return maze[i][j];  // Return the start tile
                }
            }
        }
        return null;  // Return null if no start tile is found
    }
}
