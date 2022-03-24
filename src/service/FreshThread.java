package service;

import view.GamePanel;
import view.MainFrame;
import view.ScoreDialog;

import java.awt.*;

public class FreshThread extends Thread {
    public static final int FRESH = 18;
    GamePanel p;

    public FreshThread(GamePanel gamePanel) {
        this.p = gamePanel;
    }

    public void run() {
        while(!p.isFinish()) {
            p.repaint();
            try {
                Thread.sleep(FRESH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Container c = p.getParent();
        while (!(c instanceof MainFrame)) {
            c = c.getParent();
        }
        MainFrame frame = (MainFrame) c;
        new ScoreDialog(frame);
        frame.restart();
    }
}
