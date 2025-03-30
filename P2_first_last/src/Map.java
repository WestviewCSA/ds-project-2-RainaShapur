public class Map {
	
    private Tile[][] map;
    private int rows, cols;

    public Map(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        map = new Tile[rows][cols];  // Initialize the map as a 2D array of Tiles
    }

    // Method to set a tile in the map
    public void setTile(int row, int col, char type) {
        map[row][col] = new Tile(row, col, type);
    }

    // Method to get a tile from the map
    public Tile getTile(int row, int col) {
        return map[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
