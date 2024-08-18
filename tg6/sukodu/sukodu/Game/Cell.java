package Game;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
/**
 * The Cell class model the cells of the Sudoku puzzle, by customizing (subclass)
 * the javax.swing.JTextField to include row/column, puzzle number and status.
 */
public class Cell extends JTextField {
    private static final long serialVersionUID = 1L; // to prevent serial warning

    // JTextField's colors and fonts to be chosen based on CellStatus
    public static final Color WHITE = Color.WHITE;
    public static final Color CUSTOM_BLUE = Color.decode("#064070");
    public static final Color CUSTOM_RED = new Color(216, 0, 0);
    public static final Color BLACK = Color.BLACK;

    public static final Color LIGHT_BLUE = Color.decode("#ADD8E6");
    public static final Color LIGHT_YELLOW = Color.decode("#fdfa72");
    public static final Color LIGHT_GREEN = Color.decode("#90EE90");
    public static final Color LIGHT_RED = Color.decode("#FFB6C1");
    public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;

    public static final Font FONT_NUMBERS = new Font("Droid Sans Mono", Font.PLAIN, 36);

    // The row and column number [0-8] of this cell
    int row, col;
    // The puzzle number [0-9] for this cell (0 means empty)
    int number;
    // The status of this cell defined in enum CellStatus
    CellStatus status;
    // Sector (minor grid) to which the cell belongs ('A' to 'I')
    char sector;

    // Constructor
    public Cell(int row, int col) {
        super(); // JTextField
        this.row = row;
        this.col = col;

        // Inherited from JTextField
        super.setBackground(WHITE);
        super.setHorizontalAlignment(JTextField.CENTER);
        super.setFont(FONT_NUMBERS);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /** Reset this cell for a new game, given the puzzle number and isGiven */
    public void newGame(int number, boolean isGiven, char sector) {
        this.number = number;
        this.sector = sector;
        this.status = isGiven ? CellStatus.GIVEN : CellStatus.EMPTY;
        update_cell_colors();
    }

    // Update JTextField colors of background and foreground (font color) based on its status
    public void update_cell_colors() {
        if (status == CellStatus.GIVEN) {
            super.setText(number + "");
            super.setEditable(false);
            super.setForeground(BLACK);
            
        } else if (status == CellStatus.EMPTY) {
            super.setText("");
            super.setEditable(true);
            super.setForeground(CUSTOM_BLUE);
        
        } else if (status == CellStatus.CORRECT) {
            super.setEditable(true);
            super.setForeground(CUSTOM_BLUE);
        } else if (status == CellStatus.INCORRECT) {
            super.setEditable(true);
            super.setForeground(CUSTOM_RED);
        }

        switch (sector) {
            case 'A':
                super.setBackground(LIGHT_BLUE);
                break;
            case 'B':
                super.setBackground(LIGHT_YELLOW);
                break;
            case 'C':
                super.setBackground(LIGHT_GREEN);
                break;
            case 'D':
                super.setBackground(LIGHT_GREEN);
                break;
            case 'E':
                super.setBackground(LIGHT_BLUE);
                break;
            case 'F':
                super.setBackground(LIGHT_YELLOW);
                break;
            case 'G':
                super.setBackground(LIGHT_YELLOW);
                break;
            case 'H':
                super.setBackground(LIGHT_GREEN);
                break;
            case 'I':
                super.setBackground(LIGHT_BLUE);
                break;
            default:
                super.setBackground(WHITE);
        }
    }
}
