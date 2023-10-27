import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameBoard extends JFrame implements ActionListener {
    JPanel panel;
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

            panel.setBackground(Color.white);
            panel.setLayout(new GridLayout(4, 4));
            panel.add(buttons[i]);
        }

        add(panel);
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}