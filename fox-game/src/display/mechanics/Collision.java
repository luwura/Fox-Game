package display.mechanics;

import display.GamePanel;
import domain.Entity;

public class Collision {
    GamePanel gamePanel;

    public Collision(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.getX() + entity.getHitbox().x;
        int entityRightX = entity.getX() + entity.getHitbox().x + entity.getHitbox().width;
        int entityTopY = entity.getY() + entity.getHitbox().y;
        int entityBottomY = entity.getY() + entity.getHitbox().y + entity.getHitbox().height;

        int entityLeftCol = entityLeftX / gamePanel.getTileSize();
        int entityRightCol = entityRightX / gamePanel.getTileSize();
        int entityTopRow = entityTopY / gamePanel.getTileSize();
        int entityBottomRow = entityBottomY / gamePanel.getTileSize();

        int tileNum1;
        int tileNum2;

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopY - entity.getSpeed()) / gamePanel.getTileSize();
                if (entityTopRow < 0) return; // Skip collision check if out of bounds
                tileNum1 = gamePanel.getTileManager().getMapTileNumbers()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumbers()[entityRightCol][entityTopRow];
                if (gamePanel.getTileManager().getTiles()[tileNum1].isCollision() || gamePanel.getTileManager().getTiles()[tileNum2].isCollision()) {
                    entity.setY(entityTopRow * gamePanel.getTileSize() + gamePanel.getTileSize() - entity.getHitbox().y);
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.getSpeed()) / gamePanel.getTileSize();
                if (entityBottomRow >= gamePanel.getMaxScreenRows()) return; // Skip collision check if out of bounds
                tileNum1 = gamePanel.getTileManager().getMapTileNumbers()[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumbers()[entityRightCol][entityBottomRow];
                if (gamePanel.getTileManager().getTiles()[tileNum1].isCollision() || gamePanel.getTileManager().getTiles()[tileNum2].isCollision()) {
                    entity.setY(entityBottomRow * gamePanel.getTileSize() - entity.getHitbox().y - entity.getHitbox().height);
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.getSpeed()) / gamePanel.getTileSize();
                if (entityLeftCol < 0) return; // Skip collision check if out of bounds
                tileNum1 = gamePanel.getTileManager().getMapTileNumbers()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumbers()[entityLeftCol][entityBottomRow];
                if (gamePanel.getTileManager().getTiles()[tileNum1].isCollision() || gamePanel.getTileManager().getTiles()[tileNum2].isCollision()) {
                    entity.setX(entityLeftCol * gamePanel.getTileSize() + gamePanel.getTileSize() - entity.getHitbox().x);
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.getSpeed()) / gamePanel.getTileSize();
                if (entityRightCol >= gamePanel.getMaxScreenCols()) return; // Skip collision check if out of bounds
                tileNum1 = gamePanel.getTileManager().getMapTileNumbers()[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumbers()[entityRightCol][entityBottomRow];
                if (gamePanel.getTileManager().getTiles()[tileNum1].isCollision() || gamePanel.getTileManager().getTiles()[tileNum2].isCollision()) {
                    entity.setX(entityRightCol * gamePanel.getTileSize() - entity.getHitbox().x - entity.getHitbox().width);
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
