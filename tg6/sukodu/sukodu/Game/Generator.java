package Game;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Grid numbers generator
 * Gets a generated grid from https://github.com/bertoort/sugoku
 */
public class Generator {
    int[][] numbers = new int[Board.GRID_SIZE][Board.GRID_SIZE];
    boolean[][] isGiven = new boolean[Board.GRID_SIZE][Board.GRID_SIZE];
    char[][] sectors = new char[Board.GRID_SIZE][Board.GRID_SIZE];

    // Constructor
    public Generator() {
        super();
    }

    // Generate a new puzzle given the difficulty level.
    // This method sets the numbers, isGiven and sectors matrixes
    public void generatePuzzle(String difficulty_level) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sugoku.onrender.com/board?difficulty=" + difficulty_level))
                .timeout(java.time.Duration.ofSeconds(30))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String jsonString = response.body();
            int rowIndex = 0;
            int colIndex = 0;

            // Loop through the response string and extract integers
            for (int i = 0; i < jsonString.length(); i++) {
                char ch = jsonString.charAt(i);
                if (Character.isDigit(ch)) {
                    numbers[rowIndex][colIndex] = Character.getNumericValue(ch);
                    colIndex++;
                    if (colIndex == Board.GRID_SIZE) {
                        colIndex = 0;
                        rowIndex++;
                    }
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Hardcoded puzzle in case of API fail
            numbers = new int[][] {
                {9, 8, 0, 0, 0, 0, 1, 6, 0},
                {0, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 0, 0, 0, 5, 0},
                {0, 0, 0, 0, 5, 0, 0, 0, 7},
                {0, 5, 0, 7, 0, 1, 0, 2, 0},
                {6, 0, 9, 0, 8, 2, 3, 1, 5},
                {0, 3, 0, 6, 0, 4, 0, 8, 0},
                {7, 0, 2, 0, 0, 0, 0, 0, 6},
                {0, 4, 0, 9, 0, 5, 0, 3, 0}};
        }
 
        // Matrix defining which positions are given from the start
        for (int row = 0; row < Board.GRID_SIZE; ++row) {
            for (int col = 0; col < Board.GRID_SIZE; ++col) {
                isGiven[row][col] = (numbers[row][col] != 0) ? true : false;
            }
        }

        // Matrix defining sectors
        sectors = new char[][] {
                {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C'},
                {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C'},
                {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C'},
                {'D', 'D', 'D', 'E', 'E', 'E', 'F', 'F', 'F'},
                {'D', 'D', 'D', 'E', 'E', 'E', 'F', 'F', 'F'},
                {'D', 'D', 'D', 'E', 'E', 'E', 'F', 'F', 'F'},
                {'G', 'G', 'G', 'H', 'H', 'H', 'I', 'I', 'I'},
                {'G', 'G', 'G', 'H', 'H', 'H', 'I', 'I', 'I'},
                {'G', 'G', 'G', 'H', 'H', 'H', 'I', 'I', 'I'}};
    }
}
