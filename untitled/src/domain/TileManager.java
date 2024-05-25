package domain;

import display.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class TileManager {
    private GamePanel gamePanel;
    private Tile[] tiles;
    private int[][] mapTileNumbers;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tiles = new Tile[10];
        this.mapTileNumbers = new int[gamePanel.getMaxScreenCols()][gamePanel.getMaxScreenRows()];
        loadTileImages();
        loadMap("start-map.txt");
    }

    private void loadTileImages() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass-tile.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/round-tree.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/shop.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMap(String mapName) {
        try {
            String path = "/maps/" + mapName;
            URL url = getClass().getResource(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < gamePanel.getMaxScreenRows()) {
                String[] numbers = line.split(" ");
                for (int col = 0; col < gamePanel.getMaxScreenCols(); col++) {
                    int tileNum = Integer.parseInt(numbers[col]);
                    mapTileNumbers[col][row] = tileNum;
                }
                row++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        for (int row = 0; row < gamePanel.getMaxScreenRows(); row++) {
            for (int col = 0; col < gamePanel.getMaxScreenCols(); col++) {
                int tileNumber = mapTileNumbers[col][row];
                int x = col * gamePanel.getTileSize();
                int y = row * gamePanel.getTileSize();
                g.drawImage(tiles[tileNumber].image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            }
        }
    }
}
