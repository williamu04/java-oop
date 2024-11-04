import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TheaterMatrix {
    private String[][] layout;
    private int rows;
    private int cols;

    public TheaterMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.layout = new String[rows][cols];
        initializeLayout();
    }

    private void initializeLayout() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                layout[i][j] = (char)('A' + i) + String.valueOf(j + 1);
            }
        }
    }

    public void displayLayout() {
        System.out.println("[LAYAR]");
        System.out.println("-".repeat(cols * 4 + 2));
        for (int i = 0; i < rows; i++) {
            System.out.print("| ");
            for (int j = 0; j < cols; j++) {
                System.out.print(layout[i][j] + "  ");
            }
            System.out.println("|");
        }
        System.out.println("-".repeat(cols * 4 + 2));
    }

    public String[][] getLayout() {
        return layout;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}

class SearchResult {
    private List<String> steps;
    private String path;
    private int totalSteps;
    private List<String[][]> iterations;

    public SearchResult(List<String> steps, String path, int totalSteps, List<String[][]> iterations) {
        this.steps = steps;
        this.path = path;
        this.totalSteps = totalSteps;
        this.iterations = iterations;
    }

    public void displayResult() {
        System.out.println("\nRute Pencarian Kursi:");
        for (int i = 0; i < iterations.size(); i++) {
            System.out.println("Iterasi " + (i + 1) + ":");
            String[][] iteration = iterations.get(i);
            for (String[] row : iteration) {
                for (String seat : row) {
                    if (seat.equals(steps.get(i))) {
                        System.out.print("[" + seat + "] ");
                    } else {
                        System.out.print(seat + "  ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }

        // Menambahkan visualisasi rute final
        System.out.println("Visualisasi Rute Final:");
        System.out.println("[LAYAR]");
        System.out.println("-".repeat(38));
        
        String[][] finalVisualization = new String[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                finalVisualization[i][j] = (char)('A' + i) + String.valueOf(j + 1);
            }
        }

        // Menandai rute dengan angka
        for (int i = 0; i < steps.size(); i++) {
            String step = steps.get(i);
            int row = step.charAt(0) - 'A';
            int col = Integer.parseInt(step.substring(1)) - 1;
            finalVisualization[row][col] = String.valueOf(i + 1);
        }

        // Menampilkan matriks dengan rute
        for (int i = 0; i < 9; i++) {
            System.out.print("| ");
            for (int j = 0; j < 9; j++) {
                String cell = finalVisualization[i][j];
                if (cell.length() == 1) {
                    // Jika cell adalah angka (bagian dari rute)
                    System.out.print(" " + cell + "  ");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("-".repeat(38));

        System.out.println("\nTotal Steps: " + totalSteps);
        System.out.println("Path Taken: " + path);
    }
}

public class CinemaSeatFinder {
    private TheaterMatrix theaterMatrix;

    public CinemaSeatFinder(int rows, int cols) {
        this.theaterMatrix = new TheaterMatrix(rows, cols);
    }

    public SearchResult findSeat(String targetSeat) {
        int targetRow = targetSeat.charAt(0) - 'A';
        int targetCol = Integer.parseInt(targetSeat.substring(1)) - 1;

        if (targetRow < 0 || targetRow >= theaterMatrix.getRows() ||
            targetCol < 0 || targetCol >= theaterMatrix.getCols()) {
            throw new IllegalArgumentException("Kursi tidak valid!");
        }

        List<String> steps = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        int totalSteps = 0;
        List<String[][]> iterations = new ArrayList<>();

        int rowStart = 0, rowEnd = theaterMatrix.getRows() - 1;
        int colStart = 0, colEnd = theaterMatrix.getCols() - 1;

        String[][] currentMatrix = theaterMatrix.getLayout();
        
        while (rowStart <= rowEnd && colStart <= colEnd) {
            int midRow = (rowStart + rowEnd) / 2;
            int midCol = (colStart + colEnd) / 2;

            String currentSeat = currentMatrix[midRow][midCol];
            steps.add(currentSeat);
            path.append(currentSeat);
            totalSteps++;

            String[][] iterationMatrix = copyMatrix(currentMatrix, rowStart, colStart, rowEnd, colEnd);
            iterations.add(iterationMatrix);

            if (midRow == targetRow && midCol == targetCol) {
                break;
            }

            path.append(" -> ");

            if (targetRow > midRow) {
                rowStart = midRow;
            } else if (targetRow < midRow) {
                rowEnd = midRow;
            }

            if (targetCol > midCol) {
                colStart = midCol;
            } else if (targetCol < midCol) {
                colEnd = midCol;
            }

            // Jika area pencarian sudah tidak bisa dibagi lagi
            if (rowEnd - rowStart <= 1 && colEnd - colStart <= 1) {
                rowStart = targetRow;
                rowEnd = targetRow;
                colStart = targetCol;
                colEnd = targetCol;
            }
        }

        return new SearchResult(steps, path.toString(), totalSteps, iterations);
    }

    private String[][] copyMatrix(String[][] original, int startRow, int startCol, int endRow, int endCol) {
        int rows = endRow - startRow + 1;
        int cols = endCol - startCol + 1;
        String[][] copy = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy[i][j] = original[startRow + i][startCol + j];
            }
        }
        return copy;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CinemaSeatFinder finder = new CinemaSeatFinder(9, 9);
            finder.theaterMatrix.displayLayout();

            while (true) {
                try {
                    System.out.print("Masukkan kursi yang dicari (misal: E5): ");
                    String targetSeat = scanner.nextLine().toUpperCase();
                    
                    if (targetSeat.equals("EXIT")) {
                        break;
                    }

                    if (!targetSeat.matches("[A-I][1-9]")) {
                        System.out.println("Format kursi tidak valid! Gunakan format seperti A1 sampai I9.");
                        continue;
                    }

                    SearchResult result = finder.findSeat(targetSeat);
                    result.displayResult();
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Terjadi kesalahan: " + e.getMessage());
                }
            }
        }
    }
}