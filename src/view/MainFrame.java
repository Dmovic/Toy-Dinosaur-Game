package view;

import service.ScoreRecorder;
import service.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    public MainFrame() {
        restart();
        setBounds(340, 150, 1024, 720);
        setTitle("Running! Dinosaur! ");
        Sound.background();
        ScoreRecorder.init();
        addListener();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addListener() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ScoreRecorder.saveScore();
            }
        });
    }

    public void restart() {
        Container c = getContentPane();
        c.removeAll();
        GamePanel panel = new GamePanel();
        c.add(panel);
        addKeyListener(panel);
        c.validate();
    }
}
