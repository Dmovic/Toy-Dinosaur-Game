package view;

import service.ScoreRecorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreDialog extends JDialog {
    public ScoreDialog(JFrame frame) {
        super(frame, true);
        int[] scores = ScoreRecorder.getScores();
        JPanel scoreP = new JPanel(new GridLayout(4, 1));   // four row, one column
        scoreP.setBackground(Color.WHITE);
        JLabel title = new JLabel("Score Rank", JLabel.CENTER);
        title.setFont(new Font("黑体", Font.BOLD, 20));
        title.setForeground(Color.RED);
        JLabel first = new JLabel("First: " + scores[2], JLabel.CENTER);
        JLabel second = new JLabel("Second: " + scores[1], JLabel.CENTER);
        JLabel third = new JLabel("Third: " + scores[0], JLabel.CENTER);
        JButton restart = new JButton("Restart");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // destroy dialog
            }
        });

        scoreP.add(title);
        scoreP.add(first);
        scoreP.add(second);
        scoreP.add(third);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(scoreP, BorderLayout.CENTER);
        c.add(restart, BorderLayout.SOUTH);

        setTitle("Game Over");
        int width, height;
        width = height = 200;
        int x = frame.getX() + (frame.getWidth() - width) / 2;
        int y = frame.getY() + (frame.getHeight() - height) / 2;
        setBounds(x, y, width, height);
        setVisible(true);
    }
}
