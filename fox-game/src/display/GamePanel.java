package display;

import domain.Player;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //screen settings
    public final int originalTileSize = 16; //16x16 tiles

    //today's computers have higher resolutions than back in the day when pixel games were
    //popular, so we'll scale to match now's standards
    final int scale = 3; //48x48 scales tiles
    final int tileSize = originalTileSize*scale;//=48
    final int maxScreenCols = 16;
    final int maxScreenRows = 12;
    final int screenWidth = tileSize*maxScreenCols;//=768
    final int screenHeight = tileSize*maxScreenRows;//=576

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCols() {
        return maxScreenCols;
    }

    public int getMaxScreenRows() {
        return maxScreenRows;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyHandler);

    //player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    int FPS = 60;
    TileManager tileManager = new TileManager(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.pink);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
            double drawInterval = 1000000000/FPS;
            double nextDrawTime = System.nanoTime()+drawInterval;

            while(gameThread!=null) {

                update();
                repaint();
                try {
                    double remainingTime = nextDrawTime - System.nanoTime();
                    remainingTime = remainingTime/1000000000;

                    if(remainingTime<0){
                        remainingTime = 0;
                    }
                    Thread.sleep((long) remainingTime);
                    nextDrawTime += drawInterval;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
    public void update() {
        player.update();
        checkForChunkChange();
    }

    public void checkForChunkChange() {
        int playerX = player.getX();
        int playerY = player.getY();
        int tileSize = getTileSize();
        int maxCols = getMaxScreenCols();
        int maxRows = getMaxScreenRows();

        if (playerX < 0) {
            tileManager.changeChunk(tileManager.getCurrentChunkX() - 1, tileManager.getCurrentChunkY());
            player.setX((maxCols - 1) * tileSize);
        } else if (playerX >= maxCols * tileSize) {
            tileManager.changeChunk(tileManager.getCurrentChunkX() + 1, tileManager.getCurrentChunkY());
            player.setX(0);
        } else if (playerY < 0) {
            tileManager.changeChunk(tileManager.getCurrentChunkX(), tileManager.getCurrentChunkY() - 1);
            player.setY((maxRows - 1) * tileSize);
        } else if (playerY >= maxRows * tileSize) {
            tileManager.changeChunk(tileManager.getCurrentChunkX(), tileManager.getCurrentChunkY() + 1);
            player.setY(0);
        }
    }
}
