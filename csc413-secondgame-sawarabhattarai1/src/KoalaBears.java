import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class KoalaBears extends GameObject implements Observer

{
    private final int R = 5;
    private int VX;
    private int VY;
    private short Angle;
    private BufferedImage image;
    private boolean PressedUp;
    private boolean PressedDown;
    private boolean PressedRight;
    private boolean PressedLeft;

    private final int width = 647;
    private final int height = 521;

    int ID;

    boolean end = false;

    boolean Active = true;
    int Exit = 0;

    static int NumberRescued = 0;

    private static ArrayList<KoalaBears> Koalas = new ArrayList();

    private static BufferedImage DeadKoala;

    int NumberOfDead = 0;

    public KoalaBears(Image image, int x, int y, int VX, int VY, int ID)

    {
        this.A = x;
        this.B = y;
        this.VX = VX;
        this.VY = VY;
        this.Angle = 0;

        this.image = (BufferedImage) image;
        SizeB = SizeA = image.getHeight(obs);

        this.Dead = false;
        try

        {
            DeadKoala = ImageIO.read(new File("Resources/DeadKoala.png"));
        }

        catch (IOException ex)

        {
            Logger.getLogger(KoalaBears.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.ID = ID;
        Koalas.add(this);

    }

    public void setX(int x)

    {
        this.A = x;
    }

    public void setY(int y)

    {
        this.B = y;
    }

    public void setVX(int VX)

    {
        this.VX = VX;
    }

    public void setVY(int VY)

    {
        this.VY = VY;
    }

    public void setAngle(short angle)

    {
        this.Angle = angle;
    }

    public short getAngle()

    {
        return this.Angle;
    }

    public void setImage(BufferedImage image)

    {
        this.image = image;
    }

    public void toggleUpPressed()

    {
        this.PressedUp = true;
    }

    public void toggleDownPressed()

    {
        this.PressedDown = true;
    }

    public void toggleRightPressed()

    {
        this.PressedRight = true;
    }

    public void toggleLeftPressed()

    {
        this.PressedLeft = true;
    }

    public void unToggleUpPressed()

    {
        this.PressedUp = false;
    }

    public void unToggleDownPressed()

    {
        this.PressedDown = false;
    }

    public void unToggleRightPressed()

    {
        this.PressedRight = false;
    }

    public void unToggleLeftPressed()

    {
        this.PressedLeft = false;
    }

    @Override
    public void update(Observable o, Object o1)

    {

        if (this.PressedUp)

        {
            this.moveUp();
        }

        if (this.PressedDown)

        {
            this.moveDown();
        }

        if (this.PressedLeft)

        {
            this.moveLeft();
        }

        if (this.PressedRight)

        {
            this.moveRight();
        }

    }

    private void moveLeft()

    {
        VX = R;
        if (!this.collideWithWall(A - VX, B) && !this.collideWithKoala(A - VX, B))

        {
            A -= VX;
            Angle = 90;
        }

        checkBorder();

    }

    private void moveRight()

    {
        VX = R;
        if (!this.collideWithWall(A + VX, B) && !this.collideWithKoala(A + VX, B))

        {
            A += VX;
            Angle = -90;
        }

        checkBorder();
    }

    private void moveUp()

    {
        VY = R;
        if (!this.collideWithWall(A, B - VY) && !this.collideWithKoala(A, B - VY))

        {
            B -= VY;
            Angle = 180;
        }

        checkBorder();
    }

    private void moveDown()

    {
        VY = R;
        if (!this.collideWithWall(A, B + VY) && !this.collideWithKoala(A, B + VY))

        {
            B += VY;
            Angle = 0;
        }

        checkBorder();
    }

    private void checkBorder()

    {
        if (A <= 0)

        {
            A = 0;
        }

        if (A >= (width - SizeA))

        {
            A = (width - SizeA);
        }

        if (B <= 0)

        {
            B = 0;
        }

        if (B >= (height - 2 * SizeB))

        {
            B = (height - 2 * SizeB);
        }
    }

    @Override
    public String toString()

    {
        return "x=" + A + ", y=" + B + ", angle=" + Angle;
    }

    public boolean collideWithWall(int x, int y)

    {
        Rectangle rect = new Rectangle(x + 4, y + 4, this.getWidth() - 8, this.getHeight() - 8);

        for (int row = 0; row < GameWall.getMapHeight(); row++)

        {

            for (int col = 0; col < GameWall.getMapWidth(); col++)

            {

                if (GameWall.getMapWall()[row][col] != ' ' && GameWall.getMapWall()[row][col] != 'p')

                {
                    Rectangle walls = new Rectangle(col * GameWall.wallWidth(), row * GameWall.wallHeight(),
                            GameWall.wallWidth(), GameWall.wallHeight());

                    if (rect.intersects(walls))

                    {
                        if (GameWall.getMapWall()[row][col] == 'x')

                        {
                            Exit++;
                            return false;

                        }
                        else if (GameWall.getMapWall()[row][col] == 't')

                        {
                            this.image = DeadKoala;
                            this.Dead = true;
                            this.image = DeadKoala;
                            
                        } else if (GameWall.getMapWall()[row][col] == 'w')

                        {
                            GameWall.detonated();
                        }
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean collideWithKoala(int x, int y)

    {
        Rectangle rect = new Rectangle(x, y, this.getWidth(), this.getHeight());

        for (int i = 0; i < Koalas.size(); i++)

        {
            if (Koalas.get(i).isActive())

            {
                Rectangle other = new Rectangle(Koalas.get(i).getA(), Koalas.get(i).getB(),
                        Koalas.get(i).getWidth(), Koalas.get(i).getHeight());
                if (rect.intersects(other) && !(i == ID))

                {
                    return true;
                }
            }

        }
        return false;

    }

    public void draw(Graphics g, ImageObserver obs)

    {

        if (this.show == true)

        {

            if (this.Dead)

            {
                this.image = DeadKoala;
            }

            AffineTransform rotation = AffineTransform.getTranslateInstance(A, B);
            rotation.rotate(Math.toRadians(Angle), image.getWidth() / 2, image.getHeight() / 2);
            Graphics2D graphic2D = (Graphics2D) g;
            graphic2D.drawImage(image, rotation, null);
        }

        if (this.Dead == true && NumberOfDead < 20)

        {
            NumberOfDead++;

            if (NumberOfDead == 15)

            {
                KoalaBrGame.setChangeLevel(true);
                NumberRescued = 0;
                Koalas.clear();
                
                GameWall.repeatLevel();

                try

                {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ex)

                {
                    Logger.getLogger(KoalaBears.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (Exit == 8)

        {
            this.show = false;
            
            this.Active = false;
            NumberRescued++;
            
            if (NumberRescued == Koalas.size())

            {
                NumberRescued = 0;
                Koalas.clear();

                try

                {
                    GameWall.nextLevel();
                } catch (IOException ex)

                {
                    Logger.getLogger(KoalaBears.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

    public boolean isActive()

    {
        return Active;
    }

    public static void addKoala(KoalaBears k)

    {
        Koalas.add(k);
    }

    public static ArrayList<KoalaBears> getKoalas()

    {
        return Koalas;
    }

    public static int getNum()

    {
        return Koalas.size();
    }

    public static int numRescued()

    {
        return NumberRescued;
    }
}
