package domain;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    private int x,y;
    private int speed;
    private int spriteCounter;
    private int spriteNumber=1;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction="down";
    public Rectangle hitbox;
    public boolean collisionOn;

    public Entity(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        hitbox = new Rectangle(8,16,32,32);
        collisionOn = false;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public void setCollisionOn(){
        collisionOn = true;
    }
    public void setCollisionOff(){
        collisionOn = false;
    }


    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNumber() {
        return spriteNumber;
    }

    public void setSpriteNumber(int spriteNumber) {
        this.spriteNumber = spriteNumber;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
