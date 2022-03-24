package modle;

import view.BackgroundImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Obstacle {
    public int x, y;    // coordinate
    public BufferedImage image;
    private BufferedImage stone;
    private BufferedImage cacti;
    private int speed;

    public Obstacle() {
        try {
            stone = ImageIO.read(new File("image/rock.png"));
            cacti = ImageIO.read(new File("image/cacti.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random r = new Random();
        if (r.nextInt(2) == 0) {
            image = cacti;
        } else {
            image = stone;
        }
        x = 1024;
        y = 560 - image.getHeight();
        speed = BackgroundImage.SPEED;
    }

    public void move() {
        x -= speed;
    }

    public boolean isLive() {
        if (x <= -image.getWidth()) {
            return false;
        }
        return true;
    }

    public Rectangle getBounds() {
        if (image == cacti) {
            return new Rectangle(x + 7, y, 15, image.getHeight());
        }
        return new Rectangle(x + 5, y + 4, 23, 21);
    }
}
