import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.*;

public class KoalaBrGame extends JApplet implements Runnable

{
    private Thread Thread;
        
    public static Dimension winDim = new Dimension(647, 521);
       
    Graphics2D graphics2D;
    private BufferedImage Bimg,screenBuffer;

    ImageObserver Observer;
    static GameBackground gameBackground;
    
    private static final KoalaBrGame GAME = new KoalaBrGame();

    static Image BgTile, Koalas;
    boolean GameOver;
    private final GameEvents GeoBv;
    static KoalaBears Koala1, Koala2, Koala3;
    protected GameWall gameWall;
    
    public KoalaBrGame()

    {
        this.GeoBv = new GameEvents();
    }


    @Override
    public void init()

    {
        GameOver = false;
        Observer = this;
        
        try

        {
            BgTile = ImageIO.read(new File("Resources/Background2.bmp"));
        }
        catch (IOException ex)

        {
            Logger.getLogger(KoalaBrGame.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        gameBackground = new GameBackground(0, BgTile, Observer);
        
                
        screenBuffer = new BufferedImage(winDim.width, winDim.height, BufferedImage.TYPE_INT_BGR);
        
                
        this.setFocusable(true);
                
        try

        {
            this.gameWall = new GameWall();
            this.gameWall.setWallMap();
        }

        catch (IOException ex)

        {
            Logger.getLogger(KoalaBrGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void addKoala(int x, int y)

    {
        try

        {
            Koalas = ImageIO.read(new File("Resources/Koala.png"));
        }

        catch (IOException ex)

        {
            System.out.println(ex.getMessage());
        }
        
        KoalaBears K = new KoalaBears(Koalas, x, y, 0, 0, KoalaBears.getNum());
        
        GameControl c2 = new GameControl(K, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        this.addKeyListener(c2);
        this.GeoBv.addObserver(K);
        this.setFocusable(true);
        
    }
    
    public static KoalaBrGame getInstance()

    {
        return GAME;
    }
    
    @Override
    public void start()

    {
        Thread = new Thread(this);
        Thread.setPriority(Thread.MIN_PRIORITY);
        Thread.start();
    }
    
    @Override
    public void run()

    {
        Thread me = Thread.currentThread();
        while (Thread == me)

        {
            repaint();
            try

            {
                Thread.sleep(25);
            }

            catch (InterruptedException e)

            {
                break;
            }
        }
        Thread = null;
    }

    @Override
    public void paint(Graphics g)

    {
        graphics2D = createGraphics2D(winDim.width, winDim.height);
        drawMap(winDim.width, winDim.height, graphics2D);
        drawObjects(winDim.width, winDim.height, graphics2D);
        graphics2D.dispose();

        g.drawImage(Bimg, 0, 0, this);
    }

    public void drawMap(int w, int h, Graphics2D g2)

    {
        gameBackground.drawBackground(w, h, g2);
        
        this.gameWall.doDrawing(g2);
        
    }

    private static boolean changeLevel = false;
    public static void setChangeLevel(boolean x)

    {
        changeLevel = x;
    }

    public void drawObjects(int w, int h, Graphics2D g2)

    {
             
        for (KoalaBears K : KoalaBears.getKoalas())

        {
            if(K.isActive())

            {
                K.update(GeoBv, this);
                K.draw(g2, this);
            }

            if (changeLevel == true)

            {
                changeLevel = false;
                break;
            }
        }
    } 

    public Graphics2D createGraphics2D(int w, int h)

    {
        graphics2D = null;

        if (Bimg == null || Bimg.getWidth() != w || Bimg.getHeight() != h)

        {
            Bimg = (BufferedImage) createImage(w, h);
        }

        graphics2D = Bimg.createGraphics();
        graphics2D.setBackground(getBackground());
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.clearRect(0, 0, w, h);
        return graphics2D;
    }
    
    public static void main(String[] args)

    {
        GAME.init();
        
        JFrame f = new JFrame("Koala Game");
        
        f.addWindowListener(new WindowAdapter()
        {
        });
        f.getContentPane().add("Center", GAME);
        f.pack();
        f.setSize(winDim);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
          
        GAME.start();
        
    }
}
