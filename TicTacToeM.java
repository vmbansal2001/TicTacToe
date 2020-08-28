import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeM extends JFrame implements ActionListener {

    public static final int BOARD_SIZE = 3;
    private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
    boolean crossTurn = true;

    public TicTacToeM() {
        super.setTitle("TicTacToe (Player V/s Player)");
        super.setSize(500, 500);
        GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
        Font font = new Font("Comic Sans", 1, 100);
        super.setLayout(grid);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                JButton button = new JButton("");
                buttons[i][j] = button;
                button.setFont(font);
                button.addActionListener(this);
                super.add(button);
            }
        }
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked_button = (JButton) e.getSource();
        makeMove(clicked_button);
        GameStatus gs = this.getGameStatus();
        if (gs == GameStatus.Incomplete) {
            return;
        }
        declareWinner(gs);

        int choice = JOptionPane.showConfirmDialog(this, "Do You Want to Restart?");
        if (choice == JOptionPane.YES_OPTION) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    buttons[i][j].setText("");
                }
            }
            crossTurn = true;
        } else {
            super.dispose();
        }
    }

    private void declareWinner(GameStatus gs) {
        if (gs == GameStatus.XWins) {
            JOptionPane.showMessageDialog(this, "X wins");
        } else if (gs == GameStatus.ZWins) {
            JOptionPane.showMessageDialog(this, "0 wins");
        } else {
            JOptionPane.showMessageDialog(this, "It's A Tie");
        }
    }

    private GameStatus getGameStatus() {
        String text1 = "";
        String text2 = "";
        int row = 0;
        int col = 0;

        row = 0;
        while (row < BOARD_SIZE) {
            col = 0;
            while (col < BOARD_SIZE - 1) {
                text1 = buttons[row][col].getText();
                text2 = buttons[row][col + 1].getText();
                if (!text1.equals(text2) || text1.length() == 0) {
                    break;
                }
                col++;
            }
            if (col == BOARD_SIZE - 1) {
                if (text1.equals("X")) {
                    return GameStatus.XWins;
                } else {
                    return GameStatus.ZWins;
                }
            }
            row++;
        }

        col = 0;
        while (col < BOARD_SIZE) {
            row = 0;
            while (row < BOARD_SIZE - 1) {
                text1 = buttons[row][col].getText();
                text2 = buttons[row + 1][col].getText();
                if (!text1.equals(text2) || text1.length() == 0) {
                    break;
                }
                row++;
            }
            if (row == BOARD_SIZE - 1) {
                if (text1.equals("X")) {
                    return GameStatus.XWins;
                } else {
                    return GameStatus.ZWins;
                }
            }
            col++;
        }

        row = 0;
        col = 0;

        while (row < BOARD_SIZE - 1) {
            text1 = buttons[row][col].getText();
            text2 = buttons[row + 1][col + 1].getText();
            if (!text1.equals(text2) || text1.length() == 0) {
                break;
            }
            row++;
            col++;
        }

        if (row == BOARD_SIZE - 1) {
            if (text1.equals("X")) {
                return GameStatus.XWins;
            } else {
                return GameStatus.ZWins;
            }
        }

        row = BOARD_SIZE - 1;
        col = 0;

        while (row > 0) {
            text1 = buttons[row][col].getText();
            text2 = buttons[row - 1][col + 1].getText();
            if (!text1.equals(text2) || text1.length() == 0) {
                break;
            }
            row--;
            col++;
        }

        if (row == 0) {
            if (text1.equals("X")) {
                return GameStatus.XWins;
            } else {
                return GameStatus.ZWins;
            }
        }
        String txt = "";
        for (row = 0; row < BOARD_SIZE; row++) {
            for (col = 0; col < BOARD_SIZE; col++) {
                txt = buttons[row][col].getText();
                if (txt.length() == 0) {
                    return GameStatus.Incomplete;
                }
            }
        }
        return GameStatus.Tie;
    }

    private void makeMove(JButton clicked_button) {
        String text = clicked_button.getText();
        if (text.length() > 0) {

        } else {
            if (crossTurn) {
                clicked_button.setText("X");
                crossTurn = false;
            } else {
                clicked_button.setText("0");
                crossTurn = true;
            }
        }
    }

    public static enum GameStatus {
        Incomplete, XWins, ZWins, Tie
    }

    public static void main(String[] args) {
        TicTacToeM t = new TicTacToeM();
    }


}
