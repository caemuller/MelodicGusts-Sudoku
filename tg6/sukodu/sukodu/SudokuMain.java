
import Game.Game;

/**
 * The main Sudoku program
 */
public class SudokuMain  {
 
   public static void main(String[] args) {
      // Run GUI codes in the Event-Dispatching thread for thread safety
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              new Game();
          }
      });
  }
  
}
