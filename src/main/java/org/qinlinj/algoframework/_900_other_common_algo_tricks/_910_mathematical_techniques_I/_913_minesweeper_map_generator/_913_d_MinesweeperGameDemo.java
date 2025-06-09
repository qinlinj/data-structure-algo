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

