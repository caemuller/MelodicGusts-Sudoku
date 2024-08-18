package Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel {
    // Listener Inner Class for the editable Cells
    private class CellInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get a reference of the JTextField that triggers this action event
            Cell sourceCell = (Cell) e.getSource();
            
            // Retrieve the int entered
            int numberIn = Integer.parseInt(sourceCell.getText());

            if (numberIn < 1 || numberIn > 9) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number between 1 and 9.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            sourceCell.setNumber(numberIn);

            // Check the cell validity and update the cell status and colors.
            if ( isCellValid(sourceCell) ) {
                sourceCell.status = CellStatus.CORRECT;
                JOptionPane.showMessageDialog(null, "Valid move! Keep going!", "Valid Move", JOptionPane.INFORMATION_MESSAGE);
            } else {
                sourceCell.status = CellStatus.INCORRECT;
                JOptionPane.showMessageDialog(null, "Invalid move! Please try again.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
            }
            sourceCell.update_cell_colors();
             
            /*
             * TODO
             * Check if the player has solved the puzzle after this move,
             *   by calling isSolved(). Put up a congratulation JOptionPane, if so.
             */
            if (isSolved()) {
                JOptionPane.showMessageDialog(null, "Congratulations! You have solved the puzzle!");
            }
        }
    }

    private static final long serialVersionUID = 1L; // to prevent serial warning
    // Constants for UI sizes
    public static final int GRID_SIZE = 9;
    
    public static final int SUBGRID_SIZE = 3;
    // Board width/height in pixels
    public static final int CELL_SIZE = 60; // Cell width/height in pixels
    public static final int BOARD_WIDTH = CELL_SIZE * GRID_SIZE;

    public static final int BOARD_HEIGHT = CELL_SIZE * GRID_SIZE;

    // The game board composes of 9x9 Cells (customized JTextFields)
    private Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];

    // Class that generates numbers, sectors and cell statuses
    private Generator puzzle = new Generator();

    // Constructor
    public Board() {
        super.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE)); // JPanel

        // Allocate the 2D array of Cell, and added into JPanel.
        for (int row = 0; row < GRID_SIZE; ++row) {
            for (int col = 0; col < GRID_SIZE; ++col) {
                cells[row][col] = new Cell(row, col);
                super.add(cells[row][col]); // JPanel
            }
        }

        // Allocate a ActionEvent listener for all the Cells (JTextFields)
        CellInputListener listener = new CellInputListener();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (cells[row][col].isEditable()) {
                    cells[row][col].addActionListener(listener); // only for editable rows and cols
                }
            }
        }

        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    /**
     * Generate a new puzzle and reset the game board of cells based on the puzzle.
     * You can call this method to start a new game.
     */
    public void newGame(String difficulty_level) {

        JOptionPane.showMessageDialog(null, "New Game Started with difficulty: " + difficulty_level);

        // Generate a new puzzle
        puzzle.generatePuzzle(difficulty_level);

        // Initialize all the 9x9 cells, based on the puzzle.
        for (int row = 0; row < GRID_SIZE; ++row) {
            for (int col = 0; col < GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col],
                                        puzzle.isGiven[row][col],
                                        puzzle.sectors[row][col]);

            }
        }
    }

    /**
     * Return true if the puzzle is solved
     * i.e., none of the cell have status of TO_GUESS or WRONG_GUESS
     */
    public boolean isSolved() {

        for (int row = 0; row < GRID_SIZE; ++row) {
            for (int col = 0; col < GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.EMPTY || cells[row][col].status == CellStatus.INCORRECT) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean allAnswered() {
        for (int row = 0; row < GRID_SIZE; ++row) {
            for (int col = 0; col < GRID_SIZE; ++col) {
                if (cells[row][col].status != CellStatus.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCellValid(Cell cell) {
        // Checks for equal number in vertical
        for (int row = 0; row < GRID_SIZE; ++row) {
            if (cells[row][cell.col].number == cell.number && row != cell.row) {
                return false;
            }
        }
        // Checks for equal number in horizontal
        for (int col = 0; col < GRID_SIZE; ++col) {
            if (cells[cell.row][col].number == cell.number && col != cell.col) {
                return false;
            }
        }
        // Checks for equal number in sector
        for (int row = 0; row < GRID_SIZE; ++row) {
            for (int col = 0; col < GRID_SIZE; ++col) {
                if (cells[row][col].sector == cell.sector && cells[row][col].number == cell.number &&
                    row != cell.row && col != cell.col) {
                    return false;
                }
            }
        }
        return true;
    }
}
