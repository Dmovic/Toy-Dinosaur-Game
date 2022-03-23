package modle;

import service.FreshThread;
import service.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Dinosaur {
    public BufferedImage image;     // main image
    private BufferedImage image1, image2, image3;   // run images
    public int x, y;    // coordinate
    private int jumpValue = 0;
    private boolean jumpState = false;
    private int stepTimer = 0;
    private final int JUMP_HIGHT = 100;
    private final int LOWEST_Y = 0;
    private final int FRESH = FreshThread.FRESH;

    public Dinosaur() {
        x = 50;
        y = LOWEST_Y;
        try {
            image1 = ImageIO.read(new File("image/dinosaur1.jpg"));
            image1 = ImageIO.read(new File("image/dinosaur2.jpg"));
            image1 = ImageIO.read(new File("image/dinosaur3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void step() {
        int tmp = stepTimer / 250 % 3;
        switch (tmp) {
            case 0:
                image = image1;
                break;
            case 1:
                image = image2;
                break;
            case 2:
                image = image3;
                break;
            default:
                System.out.println("Error");
        }
        stepTimer += FRESH;
    }

    public void jump() {
        if (!jumpState) {
            Sound.jump();
        }
        jumpState = true;
    }

    public void move() {
        // TODO
    }

    public Rectangle getFootBounds() {
        return new Rectangle(x + 30, y + 59, 29, 18);
    }

    public Rectangle getHeadBounds() {
        return new Rectangle(x + 66, y + 25, 32, 22);
    }
}
