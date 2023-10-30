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
    int cols;
    int[] board;

    public GameBoard() {

        rows = 4;
        cols = 4;
        panel = new JPanel();

        buttons = new JButton[rows * cols];
        labels = new JLabel[rows * cols];
        board = new int[rows * cols];
        this.shuffleBoard();


        for (int i = 0; i < rows * cols; i++) {
            buttons[i] = new JButton();
            labels[i] = new JLabel();
            String text = String.valueOf(board[i]);
            buttons[i].setText(text);
            buttons[i].setFont(new Font("Helvetica", Font.BOLD, 50));
            buttons[i].addActionListener(this);
            buttons[i].add(labels[i]);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            buttons[i].setBackground(Color.white);
            this.buttons[i].setActionCommand((i / cols) + "," + (i % cols)); // r[0] och kolumn[1] (4,5)

            panel.setBackground(Color.white);
            panel.setLayout(new GridLayout(4, 4));
            panel.add(buttons[i]);
        }


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

        Random random = new Random();
        int[] array = new int[16];
        for (int i = 0; i < 16; i++) {
            array[i] = i + 1;
        }

        array[15] = -1;
        for (int i = 15; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;

        }

        for (int i = 0; i < rows * cols; i++) {
            board[i] = array[i];

        }
    }
        public void startNewGame() {
            for (int i = 0; i < rows * cols; i++) {
                String text = String.valueOf(board[i]);
                buttons[i].setText(text);
            }
        }

    public boolean winGame() {

        int count = 1;
        for (int i = 0; i < rows * cols; i++) {
            if (board[i] != count && board[i] != -1) {
                return false;
            }
            count = count + 1;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent newGameEvent) {

        int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new game?");
        if (input == 0) {
            shuffleBoard();
            startNewGame();
        }
        if (input == 1) {
            System.exit(0);
        }
    });

        String s = e.getActionCommand().toString();
        int findRow = Integer.parseInt(s.split(",")[0]); //rad
        int findColumn = Integer.parseInt(s.split(",")[1]); //kolumn


            // Sålänge vald board inte lika med -1
        if (findRow >= 0 && findRow < rows && findColumn >= 0 && findColumn < cols && board[findRow * cols + findColumn] != -1) {
            int index = findRow * cols + findColumn;

            // söker höger
            if (findColumn + 1 < cols && board[index + 1] == -1) {
                int swap = board[index]; //Sparar värdet man tryckte på i swap
                board[index] = board[index + 1];  //Värdet man tryckte på blir värdet till höger istället
                board[index + 1] = swap; //Sparar första högra värdet i swap.

               //Parsar till en sträng och switchar bricka så de visas
                buttons[index].setText(Integer.toString(board[index]));
                buttons[index + 1].setText(Integer.toString(board[index + 1]));
            }
            // söker vänster
            else if (findColumn - 1 >= 0 && board[index - 1] == -1) {
                int swap = board[index];
                board[index] = board[index - 1];
                board[index - 1] = swap;

                buttons[index].setText(Integer.toString(board[index]));
                buttons[index - 1].setText(Integer.toString(board[index - 1]));
            }
            // söker neråt
            else if (findRow + 1 < rows && board[index + cols] == -1) {
                int swap = board[index];
                board[index] = board[index + cols];
                board[index + cols] = swap;

                buttons[index].setText(Integer.toString(board[index]));
                buttons[index + cols].setText(Integer.toString(board[index + cols]));
            }
            // upp
            else if (findRow - 1 >= 0 && board[index - cols] == -1) {
                int swap = board[index];
                board[index] = board[index - cols];
                board[index - cols] = swap;

                buttons[index].setText(Integer.toString(board[index]));
                buttons[index - cols].setText(Integer.toString(board[index - cols]));
            }
        }
    }
}
