package display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W: upPressed = true;
            break;
            case KeyEvent.VK_S: downPressed = true;
            break;
            case KeyEvent.VK_A: leftPressed = true;
            break;
            case KeyEvent.VK_D: rightPressed = true;
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W: upPressed = false;
            break;
            case KeyEvent.VK_S: downPressed = false;
            break;
            case KeyEvent.VK_A: leftPressed = false;
            break;
            case KeyEvent.VK_D: rightPressed = false;
            break;
        }
    }
}
