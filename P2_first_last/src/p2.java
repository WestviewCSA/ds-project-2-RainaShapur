import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class p2 {

    // 2D array to represent the maze
    private static Tile[][] maze;
    private static int numRows, numCols;

    public static void main(String[] args) {
        // Print out initial message to signify the program is starting
        System.out.println("Maze Solver - p2");

        // Specify the map file to be read
        readMap("Test Case");

        // Solve the maze using a stack-based approach
        solveWithStack();
    }

    // Method to read the maze from a given file
    private static void readMap(String filename) {
        try {
            File file = new File(filename);  // File object for reading the map file
            Scanner scanner = new Scanner(file);  // Scanner to read the file

            // Read the dimensions of the maze and number of rooms (not currently used)
            numRows = scanner.nextInt();
            numCols = scanner.nextInt();
            int numRooms = scanner.nextInt();  // This variable is read but not used in this implementation

            // Initialize the maze as a 2D Tile array
            maze = new Tile[numRows][numCols];

            // Read the rest of the lines, each corresponding to a row of the maze
            for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
                String row = scanner.next();  // Read each row as a string

                // Loop through the columns of the current row
                for (int colIndex = 0; colIndex < numCols; colIndex++) {
                    char element = row.charAt(colIndex);  // Get the character at the current position
                    maze[rowIndex][colIndex] = new Tile(rowIndex, colIndex, element);  // Create a new Tile object for each position
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }

    // Method to solve the maze using a stack-based approach (DFS)
    private static void solveWithStack() {
        // Initialize a stack to store the positions during the traversal
        Stack<Tile> stack = new Stack<>();

        // Find the starting position ('S') and push it onto the stack
        Tile start = findStartTile();
        stack.push(start);

        // Loop until the stack is empty or the exit ('E') is found
        while (!stack.isEmpty()) {
            Tile current = stack.pop();  // Pop the top element from the stack
            int x = current.getRow();  // Current row
            int y = current.getCol();  // Current column

            // If we found the exit, print a success message and return
            if (maze[x][y].getType() == 'E') {
                System.out.println("Exit found at (" + x + ", " + y + ")");
                return;
            }

            // Mark the current position as visited by changing its type to '.'
            maze[x][y].setType('.');

            // Check the 4 possible directions (up, down, left, right) and push valid tiles onto the stack
            if (x > 0 && maze[x - 1][y].getType() != '#') {
                stack.push(maze[x - 1][y]);  // Up
            }
            if (x < numRows - 1 && maze[x + 1][y].getType() != '#') {
                stack.push(maze[x + 1][y]);  // Down
            }
            if (y > 0 && maze[x][y - 1].getType() != '#') {
                stack.push(maze[x][y - 1]);  // Left
            }
            if (y < numCols - 1 && maze[x][y + 1].getType() != '#') {
                stack.push(maze[x][y + 1]);  // Right
            }
        }

        // If the stack is empty and no exit was found, print a failure message
        System.out.println("No solution found");
    }

    // Method to find the starting position ('S') in the maze
    private static Tile findStartTile() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (maze[i][j].getType() == 'S') {
                    return maze[i][j];  // Return the tile where 'S' is located
                }
            }
        }
        return null;  // Return null if no start position is found
    }
}
