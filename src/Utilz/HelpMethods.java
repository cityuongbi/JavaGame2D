package Utilz;

import Main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods
{
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData)
    {
        return !IsSolid(x, y, lvlData) && !IsSolid(x + width, y + height, lvlData) && !IsSolid(x + width, y, lvlData) && !IsSolid(x, y + height, lvlData);
    }

    public static boolean IsSolid(float x, float y, int[][] lvlData)
    {
        if (x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)
            return true;
        int xIdx = (int) (x / Game.TILES_SIZE);
        int yIdx = (int) (y / Game.TILES_SIZE);
        int val = lvlData[yIdx][xIdx];
        if (val >= 48 || val < 0 || val != 11)
            return true;
        return false;
    }

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitBox, float xSpeed)
    {
        int curTile = (int) (hitBox.x / Game.TILES_SIZE);
        if (xSpeed > 0)
        {
            int tileXPos = curTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitBox.width);
            return tileXPos + xOffset - 1;
        }
        else
            return curTile * Game.TILES_SIZE;
    }

    public static int GetEntityPosUnderRoofOrAboveFloor(Rectangle2D.Float hitBox, float airSpeed)
    {
        int curTile = (int) (hitBox.y / Game.TILES_SIZE);
        if (airSpeed > 0)
        {
            int tileYPos = curTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitBox.height);
            return tileYPos + yOffset - 1;
        }
        else
            return curTile * Game.TILES_SIZE;
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData)
    {
        if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 2, lvlData) && !IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 2, lvlData))
            return false;
        return true;
    }
}