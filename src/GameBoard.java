import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameBoard extends JFrame implements ActionListener {
    JPanel panel;
    JPanel startNewGamePanel;
    JButton newGameButton;
    JButton[] buttons;
    JLabel[] labels;
    int rows;
    int columns;
    int[] board;

    public GameBoard() {

        rows = 4;
        columns = 4;
        panel = new JPanel();

        buttons = new JButton[rows * columns];
        labels = new JLabel[rows * columns];
        board = new int[rows * columns];
        this.shuffleBoard();

        // Skapar och konfigurerar knappar för varje bricka i brädet
        for (int i = 0; i < rows * columns; i++) {
            buttons[i] = new JButton();
            labels[i] = new JLabel();
            String text = String.valueOf(board[i]);
            buttons[i].setText(text);
            buttons[i].setFont(new Font("Helvetica", Font.BOLD, 50));
            buttons[i].addActionListener(this);
            buttons[i].add(labels[i]);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            buttons[i].setBackground(Color.white);
            this.buttons[i].setActionCommand((i / columns) + "," + (i % columns)); // r[0] och kolumn[1] (4,5)

            panel.setBackground(Color.white);
            panel.setLayout(new GridLayout(4, 4));
            panel.add(buttons[i]);
        }


        // Skapar och konfigurerar en knapp för att starta en ny spelomgång

        newGameButton = new JButton("Start new game");
        newGameButton.addActionListener(this);

        startNewGamePanel = new JPanel();
        startNewGamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        startNewGamePanel.add(newGameButton);


        add(panel, BorderLayout.CENTER);
        add(startNewGamePanel, BorderLayout.SOUTH);
        setResizable(false);
        setVisible(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void shuffleBoard() {

        // Skapar en instans av IsSolvable-klassen för att kontrollera om brädet är lösbart och visar ett meddelande om det är olösbart

        IsSolvable check = new IsSolvable();
        if (!check.isSolvable(board)) {
            JOptionPane.showMessageDialog(null,"Unsolvable Board");
        }

        // Skapar en array med 16 element och fyller den med värden från 1 till 16.
        Random random = new Random();
        int[] array = new int[16];
        for (int i = 0; i < 16; i++) {
            array[i] = i + 1;
        }

        // Placerar -1 på den sista positionen i arrayen och slumpmässigt blandar om värdena från index 0 till index 14 i arrayen.
        array[15] = -1;
        for (int i = 15; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;

        }

        // Kopierar värdena från arrayen till brädet som används i spelet.
        for (int i = 0; i < rows * columns; i++) {
            board[i] = array[i];

        }
    }

    public void startNewGame() {

        // Uppdaterar texten på knapparna och startar om spelet
        for (int i = 0; i < rows * columns; i++) {
            String text = String.valueOf(board[i]);
            buttons[i].setText(text);
            buttons[i].setForeground(Color.black); //Sätter om färgen till svart på alla rutor.
        }

    }

    public boolean winGame() {

        // Kontrollerar om spelbrädet har lösts genom att jämföra varje brickas position med dess nummer
        int count = 1;
        for (int i = 0; i < rows * columns; i++) {
            if (board[i] != count && board[i] != -1) {
                return false;
            }
            count = count + 1;
        }
        return true;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Kontrollerar om användaren klickar på "Start new game" knappen och startar om spelet eller avslutar det beroende på användarens val.
        if (e.getSource() == newGameButton) {
            int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new game?");
            if (input == 0) {
                shuffleBoard();
                startNewGame();
            }
            if (input == 1) {
                System.exit(0);
            }

        } else {

            String s = e.getActionCommand().toString();
            int findRow = Integer.parseInt(s.split(",")[0]); //rad
            int findColumn = Integer.parseInt(s.split(",")[1]); //kolumn


            // Sålänge vald board inte lika med -1
            if (findRow >= 0 && findRow < rows && findColumn >= 0 && findColumn < columns && board[findRow * columns + findColumn] != -1) {
                int index = findRow * columns + findColumn;

                // söker höger
                if (findColumn + 1 < columns && board[index + 1] == -1) {
                    int swap = board[index]; //Sparar värdet man tryckte på i swap
                    board[index] = board[index + 1];  //Värdet man tryckte på blir värdet till höger istället
                    board[index + 1] = swap; //Sparar första högra värdet i swap.

                    //Parsar till en sträng och switchar bricka så de visas

                    buttons[index].setText(Integer.toString(board[index]));
                    buttons[index + 1].setText(Integer.toString(board[index + 1]));
                    buttons[index].setForeground(Color.white);
                    buttons[index +1].setForeground(Color.BLACK);

                    

                }
                // söker vänster
                else if (findColumn - 1 >= 0 && board[index - 1] == -1) {
                    int swap = board[index];
                    board[index] = board[index - 1];
                    board[index - 1] = swap;

                    buttons[index].setText(Integer.toString(board[index]));
                    buttons[index - 1].setText(Integer.toString(board[index - 1]));
                    buttons[index].setForeground(Color.white);
                    buttons[index -1].setForeground(Color.BLACK);
                }
                // söker neråt
                else if (findRow + 1 < rows && board[index + columns] == -1) {
                    int swap = board[index];
                    board[index] = board[index + columns];
                    board[index + columns] = swap;

                    buttons[index].setText(Integer.toString(board[index]));
                    buttons[index + columns].setText(Integer.toString(board[index + columns]));
                    buttons[index].setForeground(Color.white);
                    buttons[index + columns].setForeground(Color.BLACK);
                }
                // upp
                else if (findRow - 1 >= 0 && board[index - columns] == -1) {
                    int swap = board[index];
                    board[index] = board[index - columns];
                    board[index - columns] = swap;

                    buttons[index].setText(Integer.toString(board[index]));
                    buttons[index - columns].setText(Integer.toString(board[index - columns]));
                    buttons[index].setForeground(Color.white);
                    buttons[index - columns].setForeground(Color.BLACK);
                }
                Boolean winner = winGame();
                if(winner == true) {
                    JOptionPane.showMessageDialog(null, "you won the game!");
                     winGame();
                }
            }
        }
    }
}

