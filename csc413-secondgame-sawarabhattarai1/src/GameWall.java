import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class GameWall extends GameObject

{

    private static BufferedReader GameMap;
   
    private static int MapHeight;
    private static int MapWidth;

    private static char[][] MapWall;

    private static BufferedImage Wall, Wall2, Exit, TNT, Detonator, Rescued, RescuedKoalaBr, Win;

    //BufferedReader level;
   // String filename;

        int width = 647;
        int height = 521;
        
        static int LevelNumber = 1;
        static boolean GameWin = false;
        static boolean Detonated = false;
    
        static KoalaBrGame Game = KoalaBrGame.getInstance();

    public GameWall() throws IOException

    {
        Wall = ImageIO.read(new File("Resources/Wall.png"));
        Wall2 = ImageIO.read(new File("Resources/wall1.gif"));
        TNT = ImageIO.read(new File("Resources/TNT.png"));
        Exit = ImageIO.read(new File("Resources/Exit1.png"));
        Detonator = ImageIO.read(new File("Resources/Detonator.png"));
        Rescued = ImageIO.read(new File("Resources/Rescued.png"));
        RescuedKoalaBr = ImageIO.read(new File("Resources/Koala.png"));
        Win = ImageIO.read(new File("Resources/Congratulation.gif"));
             
        
        String line;
        try {
            
            GameMap = new BufferedReader(new FileReader("Resources/level0.txt"));
            line = GameMap.readLine();
            MapWidth = line.length();
            MapHeight = 0;
            while (line != null) {
                MapHeight++;
                MapWidth = line.length();
                line = GameMap.readLine();
                
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        MapWall = new char[MapHeight][MapWidth];
        GameMap = new BufferedReader(new FileReader("Resources/level0.txt"));
    }

    public static void setWallMap() throws IOException

    {
        String line;
        line = GameMap.readLine();

        for (int row = 1; row < MapHeight; row++)

        {
            line = GameMap.readLine();
            
            for (int col = 0; col < MapWidth; col++)

            {
                MapWall[row][col] = line.charAt(col);
                
                if(line.charAt(col)=='p')

                {
                    Game.addKoala(col * 40, row * 40);
                }
            }
            
        }

    }

    public static char[][] getMapWall()

    {
        return MapWall;
    }

    public static void setUpdatedWallMap(char[][] newWallMap)

    {
        MapWall = newWallMap;
    }

    public static int getMapHeight()

    {
        return MapHeight;
    }

    public static int getMapWidth()

    {
        return MapWidth;
    }

    public static int wallWidth()

    {
        return Wall2.getWidth();
    }

    public static int wallHeight()

    {
        return Wall2.getHeight();
    }
    
    public static void nextLevel() throws FileNotFoundException, IOException

    {
        KoalaBrGame.setChangeLevel(true);
        LevelNumber++;
        Detonated = false;

        if(LevelNumber ==2 )

        {
            MapWall = new char[MapHeight][MapWidth];
            GameMap = new BufferedReader(new FileReader("Resources/level1.txt"));
            setWallMap();
            //System.out.println(Koala.getNum());
        } else if(LevelNumber == 3) {
            GameWin = true;
        }
    }
    
    public static void repeatLevel()

    {
        Detonated = false;

        for (int row = 1; row < MapHeight; row++)

        {
            for (int col = 0; col < MapWidth; col++)

            {
                if(MapWall[row][col]=='p')

                {
                    Game.addKoala(col * 40, row * 40);
                }
            }
            
        }
    }
    
    public static void detonated()

    {
        Detonated = true;

        for (int row = 1; row < MapHeight; row++)

        {
            for (int col = 0; col < MapWidth; col++)

            {
                if(MapWall[row][col]=='t')

                {
                    MapWall[row][col]=' ';
                }
            }
            
        }
    }

    public void doDrawing(Graphics g)

    {
        Graphics2D graphic2D = (Graphics2D) g;
        
        graphic2D.drawImage(Rescued, 0, 0, obs);

        for(int i = 0; i < KoalaBears.numRescued(); i++)

        {
            graphic2D.drawImage(RescuedKoalaBr, Rescued.getWidth()+20 + i*45, 3, obs);
        }
        
        g.setColor(Color.white);
        Font font = g.getFont().deriveFont(30.0f);
        g.setFont(font);
        
        if(LevelNumber < 3)

        {
            g.drawString("LEVEL " + Integer.toString(LevelNumber), 500, 35);
        } else if (LevelNumber == 3) {
            g.drawString("YOU WIN!", 450, 35);
        }
        
        
        for (A = 0; A < MapWidth; A++)

        {
            for (B = 1; B < MapHeight; B++)

            {
                char c = MapWall[B][A];

                if (c == '0')

                {
                    BufferedImage img = Wall.getSubimage(0*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == '1')

                {
                    BufferedImage img = Wall.getSubimage(1*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == '2')

                {
                    BufferedImage img = Wall.getSubimage(2*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == '3')

                {
                    BufferedImage img = Wall.getSubimage(3*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == '4')

                {
                    BufferedImage img = Wall.getSubimage(4*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == '5')

                {
                    BufferedImage img = Wall.getSubimage(5*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == '6')

                {
                    BufferedImage img = Wall.getSubimage(6*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == '7')

                {
                    BufferedImage img = Wall.getSubimage(7*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == '8')

                {
                    BufferedImage img = Wall.getSubimage(8*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == '9')

                {
                    BufferedImage img = Wall.getSubimage(9*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == 'a')

                {
                    BufferedImage img = Wall.getSubimage(10*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == 'b')

                {
                    BufferedImage img = Wall.getSubimage(11*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == 'c')

                {
                    BufferedImage img = Wall.getSubimage(12*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == 'd')

                {
                    BufferedImage img = Wall.getSubimage(13*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == 'e')

                {
                    BufferedImage img = Wall.getSubimage(14*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == 'f')

                {
                    BufferedImage img = Wall.getSubimage(15*40, 0, 40, 40);
                    graphic2D.drawImage(img, A * Wall2.getWidth(), B * Wall2.getHeight(), obs);
                }

                if (c == 't')

                {
                    graphic2D.drawImage(TNT, A * TNT.getWidth(), B * TNT.getHeight(), obs);
                }

                if (c == 'x')

                {
                    graphic2D.drawImage(Exit, A * Exit.getWidth(), B * Exit.getHeight(), obs);
                }

                if (c == 'w')

                {
                    BufferedImage img;

                    if(Detonated == true)

                    {
                        img = Detonator.getSubimage(40, 0, 40, 40);
                    } else {
                        img = Detonator.getSubimage(0, 0, 40, 40);
                    }

                    graphic2D.drawImage(img, A * Exit.getWidth(), B * Exit.getHeight(), obs);
                }
            }
        }
        
        if (GameWin == true)

        {
            graphic2D.drawImage(Win, width /2 - Win.getWidth()/2, height /2 - Win.getHeight()/2, obs);
        } 

    }
}
