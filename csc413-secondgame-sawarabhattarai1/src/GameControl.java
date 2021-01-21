import javax.xml.crypto.KeySelectorException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Observable;

public class GameControl extends Observable implements KeyListener

{
    private KoalaBears Bears;
    private final int Up;
    private final int Down;
    private final int Right;
    private final int Left;
    

    public GameControl(KoalaBears bears, int up, int down, int left, int right)

    {
        this.Bears = bears;
        this.Up = up;
        this.Down = down;
        this.Right = right;
        this.Left = left;
        
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {

    }

    @Override
    public void keyPressed(KeyEvent ke)

    {
        int keyPressed = ke.getKeyCode();

        if (keyPressed == Up)

        {
            this.Bears.toggleUpPressed();
        }

        if (keyPressed == Down)

        {
            this.Bears.toggleDownPressed();
        }

        if (keyPressed == Left)

        {
            this.Bears.toggleLeftPressed();
        }

        if (keyPressed == Right)

        {
            this.Bears.toggleRightPressed();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent ke)

    {
        int keyReleased = ke.getKeyCode();

        if (keyReleased  == Up)

        {
            this.Bears.unToggleUpPressed();
        }

        if (keyReleased == Down)

        {
            this.Bears.unToggleDownPressed();
        }

        if (keyReleased  == Left)

        {
            this.Bears.unToggleLeftPressed();
        }

        if (keyReleased  == Right)

        {
            this.Bears.unToggleRightPressed();
        }
        
    }
}
