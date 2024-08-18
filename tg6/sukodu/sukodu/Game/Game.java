package Game;

import java.awt.*;

import javax.swing.*;

public class Game extends JFrame {

   private static final String GameName = "SuKoDu";

    private static final long serialVersionUID = 1L; // to prevent serial warning

    private JComboBox<String> difficultyComboBox;
   
    Board board = new Board();

    JButton btnNewGame = new JButton("New Game");
    JButton btnCheck = new JButton("Check Solution");
   
    public Game()  {
      
      // Show a welcome message, before starting the game
      JOptionPane.showMessageDialog(null, "     Welcome to "+GameName+"!"+ "\n" , "Welcome", JOptionPane.PLAIN_MESSAGE);

      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
 
      // Add difficulty selection pane
      JPanel topPanel = new JPanel();

      difficultyComboBox = new JComboBox<>(new String[]{"easy", "medium", "hard"});
      topPanel.add(new JLabel("Select Difficulty:"));
      topPanel.add(difficultyComboBox);
      cp.add(topPanel, BorderLayout.NORTH);
      cp.add(board, BorderLayout.CENTER);

      // Add a button to the south to re-start the game via board.newGame() with selected difficulty
      JPanel bottomPanel = new JPanel();
      bottomPanel.add(btnNewGame);
      bottomPanel.add(btnCheck);
      cp.add(bottomPanel, BorderLayout.SOUTH);

      pack();     // Pack the UI components, instead of using setSize()
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
      setTitle(GameName);
      setVisible(true);
  
      // Add trigger to initialize the game
      btnNewGame.addActionListener(e -> board.newGame(difficultyComboBox.getSelectedItem().toString()));
   
      // Add trigger to check the solution


      if (board.allAnswered())
      {
         if (board.isSolved()) {
            JOptionPane.showMessageDialog(null, "Congratulations! You have solved the puzzle!");
         } else {
            JOptionPane.showMessageDialog(null, "Sorry! You have not solved the puzzle!");
         }

      }else{
         JOptionPane.showMessageDialog(null, "Sorry! You have not solved the puzzle!");
      }

   }
    
}
