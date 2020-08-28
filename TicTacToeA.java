import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToeA extends JFrame implements ActionListener {

    public static final int BOARD_SIZE = 3;
    JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
    boolean crossTurn = false;
    Random r = new Random();
    int rr = r.nextInt(BOARD_SIZE);
    int rc = r.nextInt(BOARD_SIZE);


    public TicTacToeA() {

        super.setTitle("TicTacToe (Player V/s Computer)");
        super.setSize(500, 500);
        GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
        super.setLayout(grid);
        Font font = new Font("Comic Sans", Font.BOLD, 100);
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton button = new JButton("");
                if (row == rr && col == rc) {
                    button.setText("X");
                }
                button.addActionListener(this);
                button.setFont(font);
                buttons[row][col] = button;
                super.add(button);
            }
        }
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked_button = (JButton) e.getSource();
        String txt = clicked_button.getText();
        if (txt.length() > 0) {
            JOptionPane.showMessageDialog(this, "Invalid Move");
            return;
        } else {
            clicked_button.setText("0");
        }
        GameStatus gs = getGameStatus();
        if (gs == GameStatus.Incomplete) {
            if (!WinAlgo()) {
                if (!AutoMove(0, 0)) {
                    JOptionPane.showMessageDialog(this, "0 Wins");
                    restart();
                }
            }
            GameStatus gs2 = getGameStatus();
            if (gs2 == GameStatus.Incomplete) {
                return;
            }
            declareWinner(gs2);
        } else {
            declareWinner(gs);
        }

        restart();

    }

    public void restart() {
        int choice = JOptionPane.showConfirmDialog(this, "Do You Want to Restart?");
        if (choice == JOptionPane.YES_OPTION) {
            rr = r.nextInt(BOARD_SIZE);
            rc = r.nextInt(BOARD_SIZE);
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    buttons[i][j].setText("");
                    if (i == rr && j == rc) {
                        buttons[i][j].setText("X");
                    }
                }
            }
            crossTurn = false;
        } else if (choice == JOptionPane.NO_OPTION) {
            super.dispose();
        }
    }

    public boolean AutoMove(int row, int col) {

        if (row == BOARD_SIZE) {
            return false;
        }

        if (col == BOARD_SIZE) {
            return AutoMove(row + 1, 0);
        }

        String text = buttons[row][col].getText();
        if (text.length() > 0) {
            return AutoMove(row, col + 1);
        } else {
            buttons[row][col].setText("X");
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (buttons[i][j].getText().length() > 0) {

                    } else {
                        buttons[i][j].setText("0");
                        GameStatus gs = getGameStatus();
                        buttons[i][j].setText("");
                        if (gs == GameStatus.Zwins) {
                            buttons[row][col].setText("");
                            return AutoMove(row, col + 1);
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean WinAlgo() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (buttons[i][j].getText().length() > 0) {

                } else {
                    buttons[i][j].setText("X");
                    GameStatus gs = getGameStatus();
                    if (gs == GameStatus.Xwins) {
                        return true;
                    } else {
                        buttons[i][j].setText("");
                    }
                }
            }
        }
        return false;
    }

    public GameStatus getGameStatus() {
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
                    return TicTacToeA.GameStatus.Xwins;
                } else {
                    return TicTacToeA.GameStatus.Zwins;
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
                    return TicTacToeA.GameStatus.Xwins;
                } else {
                    return TicTacToeA.GameStatus.Zwins;
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
                return TicTacToeA.GameStatus.Xwins;
            } else {
                return TicTacToeA.GameStatus.Zwins;
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
                return TicTacToeA.GameStatus.Xwins;
            } else {
                return TicTacToeA.GameStatus.Zwins;
            }
        }
        String txt = "";
        for (row = 0; row < BOARD_SIZE; row++) {
            for (col = 0; col < BOARD_SIZE; col++) {
                txt = buttons[row][col].getText();
                if (txt.length() == 0) {
                    return TicTacToeA.GameStatus.Incomplete;
                }
            }
        }
        return TicTacToeA.GameStatus.Tie;

    }

    public void declareWinner(GameStatus gs) {
        if (gs == GameStatus.Zwins) {
            JOptionPane.showMessageDialog(this, "0 Wins");
        }
        if (gs == GameStatus.Xwins) {
            JOptionPane.showMessageDialog(this, "X Wins");
        }
        if (gs == GameStatus.Tie) {
            JOptionPane.showMessageDialog(this, "It is a Tie !");
        }
    }

    public static enum GameStatus {
        Incomplete, Xwins, Zwins, Tie
    }

    public static void main(String[] args) {
        TicTacToeA t = new TicTacToeA();
    }
}
