package tiles;

import display.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TileManager {
    private GamePanel gamePanel;
    private Tile[] tiles;
    private Map<String, int[][]> mapChunks;
    private int currentChunkX;
    private int currentChunkY;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tiles = new Tile[10];
        this.mapChunks = new HashMap<>();
        this.currentChunkX = 0;
        this.currentChunkY = 0;
        loadTileImages();
        loadInitialMapChunks();
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

    private void loadInitialMapChunks() {
        // Load the initial chunk
        loadMapChunk(0, 0, "map-0-0.txt");
        // Preload adjacent chunks if needed
//        loadMapChunk(1, 0, "map--1-0.txt");
//        loadMapChunk(-1, 0, "map--1-0.txt");
//        loadMapChunk(0, 1, "map-0-1.txt.txt");
//        loadMapChunk(0, -1, "map-0--1.txt");
    }

    private void loadMapChunk(int chunkX, int chunkY, String mapName) {
        try {
            String path = "/maps/" + mapName;
            URL url = getClass().getResource(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            int[][] mapTileNumbers = new int[gamePanel.getMaxScreenCols()][gamePanel.getMaxScreenRows()];

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

            mapChunks.put(chunkX + "," + chunkY, mapTileNumbers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeChunk(int newChunkX, int newChunkY) {
        if (!mapChunks.containsKey(newChunkX + "," + newChunkY)) {
            // Load new chunk if not already loaded
            String mapName = "map-" + newChunkX + "-" + newChunkY + ".txt";
            loadMapChunk(newChunkX, newChunkY, mapName);
        }
        currentChunkX = newChunkX;
        currentChunkY = newChunkY;
    }

    public int getCurrentChunkX() {
        return currentChunkX;
    }

    public int getCurrentChunkY() {
        return currentChunkY;
    }

    public void draw(Graphics2D g) {
        int[][] mapTileNumbers = mapChunks.get(currentChunkX + "," + currentChunkY);

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
