import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {

    JRadioButton r1, r2;
    JLabel l1, l2;
    JButton b1;

    TicTacToe() {

        super.setTitle("TicTacToe");
        super.setLayout(new FlowLayout());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(500, 500);

        Font font = new Font("Comic Sans", Font.BOLD, 77);
        Font font1 = new Font("Comic Sans", Font.CENTER_BASELINE, 37);

        r1 = new JRadioButton("Player V/s Player");
        r1.setFont(font1);
        r2 = new JRadioButton("Player V/s Computer");
        r2.setFont(font1);

        ButtonGroup g1 = new ButtonGroup();
        g1.add(r1);
        g1.add(r2);

        l1 = new JLabel("Welcome");
        l1.setFont(font);

        l2 = new JLabel("Choose The Game Mode :");
        l2.setFont(font1);

        b1 = new JButton("Play");
        b1.setFont(font1);
        b1.addActionListener(this);
        super.add(l1);
        super.add(l2);
        super.add(r1);
        super.add(r2);
        super.add(b1);

        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (r1.isSelected()) {
            new TicTacToeM();
            super.dispose();
        } else if (r2.isSelected()) {
            new TicTacToeA();
            super.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please Choose A Game Mode");
        }

    }

    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
    }
}
