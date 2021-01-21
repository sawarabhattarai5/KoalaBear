import java.awt.*;
import java.awt.image.*;

public abstract class GameObject

{

    Image Image;
    int A, B, SizeA, SizeB;
    //xDirection, yDirection;

    ImageObserver obs;
    Dimension mapDim;
    
    boolean show = true;
    boolean Dead;
    
       
    
    public void draw(Graphics g, ImageObserver obs)

    {

        if (show == true)

        {
            g.drawImage(Image, A, B, obs);
            this.obs = obs;
        }
    }

        
    public int getA()

    {
        return this.A;
    }
    
    public int getB()

    {
        return this.B;
    }
    
    public int getWidth()

    {
        return this.SizeA;
    }
    
    public int getHeight()

    {
        return this.SizeB;
    }
}
