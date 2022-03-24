package view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundImage {
    public BufferedImage image;
    private BufferedImage image1, image2;
    private Graphics2D g;
    public int x1, x2;
    public static final int SPEED = 5;

    public BackgroundImage() {
        try {
            image1 = ImageIO.read(new File("image/background.png"));
            image2 = ImageIO.read(new File("image/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        image = new BufferedImage(1024, 720, BufferedImage.TYPE_INT_RGB);
        g = image.createGraphics();
        x1 = 0;
        x2 = 1024;
        g.drawImage(image1, x1, 0, null);
    }

    public void roll() {
        x1 -= SPEED;
        x2 -= SPEED;
        if (x1 <= -800) {
            x1 = 800;
        }
        if (x2 <= -800) {
            x2 = 800;
        }
        g.drawImage(image1, x1, 0, null);
        g.drawImage(image2, x2, 0, null);
    }
}
