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
    private final int JUMP_HEIGHT = 100;
    private final int LOWEST_Y = 500;
    private final int FRESH = FreshThread.FRESH;

    public Dinosaur() {
        x = 50;
        y = LOWEST_Y;
        try {
            image1 = ImageIO.read(new File("image/dinosaur13.jpg"));
            image2 = ImageIO.read(new File("image/dinosaur23.jpg"));
            image3 = ImageIO.read(new File("image/dinosaur33.jpg"));
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
        step();
        if (jumpState) {
            if (y >= LOWEST_Y) {
                jumpValue = -4;
            }
            if (y <= LOWEST_Y - JUMP_HEIGHT) {
                jumpValue = 4;
            }
            y += jumpValue;
            if (y >= LOWEST_Y) {
                jumpState = false;
            }
        }
    }

    public Rectangle getFootBounds() {
        return new Rectangle(x + 20, y + 29, 29, 18);
    }

    public Rectangle getHeadBounds() {
        return new Rectangle(x + 16, y + 25, 32, 22);
    }
}
