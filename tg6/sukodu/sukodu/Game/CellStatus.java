package Game;
/**
 * An enumeration of constants to represent the status
 * of each cell.
 */
public enum CellStatus {
    GIVEN,         // clue, no need to guess
    EMPTY,      // need to guess - not attempted yet
    CORRECT, // correct guess
    INCORRECT    // wrong guess
    // The puzzle is solved if none of the cells have status of EMPTY or INCORRECT
}
