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
        
        String[][] finalVisualization = new String[iterations.get(0).length][iterations.get(0)[0].length];
        for (int i = 0; i < finalVisualization.length; i++) {
            for (int j = 0; j < finalVisualization[i].length; j++) {
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
        for (int i = 0; i < finalVisualization.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < finalVisualization[i].length; j++) {
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

public class CinemaSeatFinder2 {
    private TheaterMatrix theaterMatrix;

    public CinemaSeatFinder2(int rows, int cols) {
        this.theaterMatrix = new TheaterMatrix(rows, cols);
    }

    public SearchResult findSeat(String targetSeat) {
        int targetRow = targetSeat.charAt(0) - 'A';
        int targetCol = Integer.parseInt(targetSeat.substring(1)) -  1;

        List<String> steps = new ArrayList<>();
        List<String[][]> iterations = new ArrayList<>();
        String path = "";

        int rowStart = 0;
        int rowEnd = theaterMatrix.getRows() - 1;
        int colStart = 0;
        int colEnd = theaterMatrix.getCols() - 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            int midRow = rowStart + (rowEnd - rowStart) / 2;
            int midCol = colStart + (colEnd - colStart) / 2;

            String midSeat = theaterMatrix.getLayout()[midRow][midCol];
            steps.add(midSeat);
            iterations.add(copyMatrix(theaterMatrix.getLayout()));

            if (midRow == targetRow && midCol == targetCol) {
                path += midSeat;
                break;
            }

            if (targetRow < midRow) {
                rowEnd = midRow - 1;
            } else {
                rowStart = midRow + 1;
            }

            if (targetCol < midCol) {
                colEnd = midCol - 1;
            } else {
                colStart = midCol + 1;
            }

            path += midSeat + " -> ";
        }

        return new SearchResult(steps, path.substring(0, path.length() - 4), steps.size(), iterations);
    }

    private String[][] copyMatrix(String[][] original) {
        String[][] copy = new String[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[i].length; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = scanner.nextInt();

        CinemaSeatFinder2 finder = new CinemaSeatFinder2(rows, cols);
        finder.theaterMatrix.displayLayout();

        while (true) {
            System.out.print("Enter the target seat (e.g., A1) or 'EXIT' to quit: ");
            String targetSeat = scanner.next();

            if (targetSeat.equalsIgnoreCase("EXIT")) {
                break;
            }

            if (targetSeat.length() < 2 || targetSeat.charAt(0) < 'A' || targetSeat.charAt(0) > 'I' || !Character.isDigit(targetSeat.charAt(1))) {
                System.out.println("Invalid seat format. Please enter a seat in the format A1 to I9.");
                continue;
            }

            int targetRow = targetSeat.charAt(0) - 'A';
            int targetCol = Integer.parseInt(targetSeat.substring(1)) - 1;

            if (targetRow < 0 || targetRow >= rows || targetCol < 0 || targetCol >= cols) {
                System.out.println("Seat is out of bounds. Please enter a seat within the theater dimensions.");
                continue;
            }

            SearchResult result = finder.findSeat(targetSeat);
            result.displayResult();
        }
        scanner.close();
    }
}