import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;

public class GameBackground

{
    Image Tiles;
    int TilesWidth, TilesHeight;
    int Movement, Speed;
    ImageObserver Observe;

    public GameBackground(int spd, Image bgTile, ImageObserver obs)

    {
        Observe = obs;
        Tiles = bgTile;
        TilesWidth = Tiles.getWidth(obs);
        TilesHeight = Tiles.getHeight(obs);
        Movement = 0;
        Speed = spd;

    }
    
    public void drawBackground(int w, int h, Graphics2D g2)

    {
        int numX = (int) (w / TilesWidth);
        int numY = (int) (h / TilesHeight);
        
        for (int i = -1; i <= numY; i++)

        {
            for (int j = 0; j <= numX; j++)

            {
                g2.drawImage(Tiles, j * TilesWidth, i * TilesHeight + (Movement % TilesHeight), TilesWidth, TilesHeight, Observe);
            }
        }
        
    }
    
    public void updateBackground()

    {
        Movement += Speed;
    }
}
