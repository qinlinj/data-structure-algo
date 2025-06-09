package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._913_minesweeper_map_generator;

/**
 * COMPLETE MINESWEEPER GAME IMPLEMENTATION
 * <p>
 * This class provides a complete, playable minesweeper game that demonstrates
 * the practical application of random mine generation algorithms. It combines
 * the theoretical concepts with an interactive gaming experience.
 * <p>
 * Key Features:
 * 1. Multiple Mine Generation Strategies:
 * - Fisher-Yates Shuffle for small-medium boards
 * - Reservoir Sampling for large boards with memory efficiency
 * - User can choose generation algorithm
 * <p>
 * 2. Complete Game Logic:
 * - Cell revelation and flagging
 * - Mine counting for numbered cells
 * - Flood fill for empty cell clusters
 * - Win/lose condition detection
 * <p>
 * 3. Interactive Console Interface:
 * - Command-based gameplay (reveal, flag, unflag)
 * - Visual board display with symbols
 * - Game state feedback and statistics
 * <p>
 * 4. Educational Demonstrations:
 * - Algorithm performance comparison
 * - Mine distribution visualization
 * - Statistical analysis of randomness
 * <p>
 * Game Rules:
 * - Reveal cells to find all non-mine cells
 * - Numbers indicate adjacent mine count
 * - Flag suspected mines
 * - Game ends when all non-mines revealed or mine hit
 * <p>
 * Applications: Educational tool, algorithm demonstration, recreational gaming
 */

public class _913_d_MinesweeperGameDemo {
    // Game state
    private final int width, height, mineCount;
    private final Cell[][] board;
    private final GenerationAlgorithm algorithm;
    private boolean gameStarted;
    private boolean gameWon;
    private boolean gameLost;
    private int revealedCells;
    private int flaggedCells;
    private long gameStartTime;
    private java.util.Random random;

    public _913_d_MinesweeperGameDemo(int width, int height, int mineCount, GenerationAlgorithm algorithm) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        this.algorithm = algorithm;
        this.board = new Cell[height][width];
        this.gameStarted = false;
        this.gameWon = false;
        this.gameLost = false;
        this.revealedCells = 0;
        this.flaggedCells = 0;
        this.random = new java.util.Random();

        initializeBoard();
    }

    /**
     * Initializes the game board with empty cells
     */
    private void initializeBoard() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y][x] = new Cell();
            }
        }
    }

    /**
     * Generates mines using the selected algorithm
     */
    private void generateMines(Position firstClick) {
        java.util.List<Position> minePositions;

        switch (algorithm) {
            case FISHER_YATES:
                minePositions = generateMinesFisherYates(firstClick);
                break;
            case RESERVOIR_SAMPLING:
                minePositions = generateMinesReservoir(firstClick);
                break;
            default:
                throw new IllegalStateException("Unknown algorithm: " + algorithm);
        }

        // Place mines on board
        for (Position pos : minePositions) {
            board[pos.y][pos.x].hasMine = true;
        }

        // Calculate adjacent mine counts
        calculateAdjacentMines();

        gameStarted = true;
        gameStartTime = System.currentTimeMillis();
    }

    /**
     * Fisher-Yates mine generation (avoiding first click)
     */
    private java.util.List<Position> generateMinesFisherYates(Position avoid) {
        java.util.List<Position> positions = new java.util.ArrayList<>();

        // Create all positions except the first click
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Position pos = new Position(x, y);
                if (!pos.equals(avoid)) {
                    positions.add(pos);
                }
            }
        }

        // Shuffle using Fisher-Yates
        for (int i = positions.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Position temp = positions.get(i);
            positions.set(i, positions.get(j));
            positions.set(j, temp);
        }

        return positions.subList(0, mineCount);
    }

    /**
     * Reservoir sampling mine generation (avoiding first click)
     */
    private java.util.List<Position> generateMinesReservoir(Position avoid) {
        Position[] reservoir = new Position[mineCount];
        int availableCells = width * height - 1; // Exclude first click
        int reservoirIndex = 0;

        for (int i = 0; i < width * height; i++) {
            int x = i % width;
            int y = i / width;
            Position pos = new Position(x, y);

            // Skip the first click position
            if (pos.equals(avoid)) continue;

            if (reservoirIndex < mineCount) {
                reservoir[reservoirIndex] = pos;
                reservoirIndex++;
            } else {
                int randomIndex = random.nextInt(reservoirIndex + 1);
                if (randomIndex < mineCount) {
                    reservoir[randomIndex] = pos;
                }
            }
        }

        return java.util.Arrays.asList(reservoir);
    }


    /**
     * Calculates adjacent mine counts for all cells
     */
    private void calculateAdjacentMines() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!board[y][x].hasMine) {
                    board[y][x].adjacentMines = countAdjacentMines(x, y);
                }
            }
        }
    }

    /**
     * Counts mines adjacent to a specific cell
     */
    private int countAdjacentMines(int x, int y) {
        int count = 0;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) continue; // Skip center cell

                int nx = x + dx;
                int ny = y + dy;

                if (isValidPosition(nx, ny) && board[ny][nx].hasMine) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Checks if position is within board bounds
     */
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Reveals a cell (main game action)
     */
    public boolean revealCell(int x, int y) {
        if (!isValidPosition(x, y) || gameLost || gameWon) {
            return false;
        }

        Cell cell = board[y][x];
        if (cell.state != CellState.HIDDEN) {
            return false; // Already revealed or flagged
        }

        // Generate mines on first click
        if (!gameStarted) {
            generateMines(new Position(x, y));
        }

        // Check if clicked on mine
        if (cell.hasMine) {
            cell.state = CellState.EXPLODED;
            gameLost = true;
            revealAllMines();
            return false;
        }

        // Reveal cell
        cell.state = CellState.REVEALED;
        revealedCells++;

        // If empty cell, flood fill reveal adjacent cells
        if (cell.adjacentMines == 0) {
            floodFillReveal(x, y);
        }

        // Check win condition
        if (revealedCells == width * height - mineCount) {
            gameWon = true;
            flagAllMines();
        }

        return true;
    }

    /**
     * Flood fill algorithm to reveal empty regions
     */
    private void floodFillReveal(int startX, int startY) {
        java.util.Queue<Position> queue = new java.util.LinkedList<>();
        queue.add(new Position(startX, startY));

        while (!queue.isEmpty()) {
            Position pos = queue.poll();

            // Check all adjacent cells
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    if (dx == 0 && dy == 0) continue;

                    int nx = pos.x + dx;
                    int ny = pos.y + dy;

                    if (isValidPosition(nx, ny)) {
                        Cell cell = board[ny][nx];
                        if (cell.state == CellState.HIDDEN && !cell.hasMine) {
                            cell.state = CellState.REVEALED;
                            revealedCells++;

                            // If also empty, add to queue for further expansion
                            if (cell.adjacentMines == 0) {
                                queue.add(new Position(nx, ny));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Toggles flag on a cell
     */
    public boolean toggleFlag(int x, int y) {
        if (!isValidPosition(x, y) || gameLost || gameWon) {
            return false;
        }

        Cell cell = board[y][x];
        if (cell.state == CellState.HIDDEN) {
            cell.state = CellState.FLAGGED;
            flaggedCells++;
            return true;
        } else if (cell.state == CellState.FLAGGED) {
            cell.state = CellState.HIDDEN;
            flaggedCells--;
            return true;
        }

        return false; // Can't flag revealed cells
    }

    /**
     * Reveals all mines (when game lost)
     */
    private void revealAllMines() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board[y][x].hasMine && board[y][x].state != CellState.EXPLODED) {
                    board[y][x].state = CellState.REVEALED;
                }
            }
        }
    }

    /**
     * Flags all mines (when game won)
     */
    private void flagAllMines() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board[y][x].hasMine) {
                    board[y][x].state = CellState.FLAGGED;
                }
            }
        }
        flaggedCells = mineCount;
    }

    /**
     * Prints the current game board
     */
    public void printBoard() {
        System.out.println("\n" + "=".repeat(width * 3 + 5));
        System.out.printf("Minesweeper %dx%d | Mines: %d | Flags: %d/%d\n",
                width, height, mineCount, flaggedCells, mineCount);
        System.out.printf("Algorithm: %s | Status: %s\n",
                algorithm, getGameStatus());
        System.out.println("=".repeat(width * 3 + 5));

        // Column headers
        System.out.print("   ");
        for (int x = 0; x < width; x++) {
            System.out.printf("%2d ", x);
        }
        System.out.println();

        // Board rows
        for (int y = 0; y < height; y++) {
            System.out.printf("%2d ", y);
            for (int x = 0; x < width; x++) {
                Cell cell = board[y][x];
                char symbol = getCellSymbol(cell);
                System.out.printf(" %c ", symbol);
            }
            System.out.println();
        }

        if (gameWon || gameLost) {
            long gameTime = System.currentTimeMillis() - gameStartTime;
            System.out.printf("\nGame completed in %.1f seconds\n", gameTime / 1000.0);
        }
    }

    /**
     * Gets display symbol for a cell
     */
    private char getCellSymbol(Cell cell) {
        switch (cell.state) {
            case HIDDEN:
                return '·';
            case FLAGGED:
                return 'F';
            case EXPLODED:
                return 'X';
            case REVEALED:
                if (cell.hasMine) {
                    return '*';
                } else if (cell.adjacentMines == 0) {
                    return ' ';
                } else {
                    return (char) ('0' + cell.adjacentMines);
                }
            default:
                return '?';
        }
    }


    /**
     * Cell states in the game
     */
    public enum CellState {
        HIDDEN,     // Not yet revealed
        REVEALED,   // Revealed (safe)
        FLAGGED,    // Marked as suspected mine
        EXPLODED    // Mine that was clicked
    }

    /**
     * Mine generation algorithms
     */
    public enum GenerationAlgorithm {
        FISHER_YATES,
        RESERVOIR_SAMPLING
    }

    /**
     * Position class for board coordinates
     */
    public static class Position {
        public final int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Position position = (Position) obj;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(x, y);
        }
    }

    /**
     * Game cell containing mine and state information
     */
    public static class Cell {
        public boolean hasMine;
        public CellState state;
        public int adjacentMines;

        public Cell() {
            this.hasMine = false;
            this.state = CellState.HIDDEN;
            this.adjacentMines = 0;
        }
    }

}

