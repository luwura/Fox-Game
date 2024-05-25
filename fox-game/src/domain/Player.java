package domain;

import display.GamePanel;
import display.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private int spriteCounter = 0;
    private int spriteNumber = 1;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(200, 200, 4);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        getPlayerImage();
    }

    public void update() {
        if(keyHandler.isUpPressed() || keyHandler.isDownPressed() || keyHandler.isLeftPressed() || keyHandler.isRightPressed()){
            if (keyHandler.isUpPressed()) {
                setDirection("up");
                //setY(getY() - getSpeed());
            } else if (keyHandler.isDownPressed()) {
                setDirection("down");
                //setY(getY() + getSpeed());
            } else if (keyHandler.isLeftPressed()) {
                setDirection("left");
                //setX(getX() - getSpeed());
            } else if (keyHandler.isRightPressed()) {
                setDirection("right");
                //setX(getX() + getSpeed());
            }
            setCollisionOff();
            gamePanel.getCollision().checkTile(this);

            if(!collisionOn){
                switch(getDirection()) {
                    case "up":
                        setY(getY() - getSpeed());
                        break;
                    case "down":
                        setY(getY() + getSpeed());
                        break;
                    case "left":
                        setX(getX() - getSpeed());
                        break;
                    case "right":
                        setX(getX() + getSpeed());
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g) {
        BufferedImage image = null;
        switch (getDirection()) {
            case "up":
                image = (spriteNumber == 1) ? up1 : up2;
                break;
            case "down":
                image = (spriteNumber == 1) ? down1 : down2;
                break;
            case "left":
                image = (spriteNumber == 1) ? left1 : left2;
                break;
            case "right":
                image = (spriteNumber == 1) ? right1 : right2;
                break;
        }
        g.drawImage(image, getX(), getY(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player-sprites/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player-sprites/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player-sprites/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player-sprites/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player-sprites/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player-sprites/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player-sprites/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player-sprites/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
