import java.awt.*;
import java.awt.event.*;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;

import com.golden.gamedev.util.*;

public class Shapez extends Sprite
{
    double movementSpeed = 0;

    public Shapez(java.awt.image.BufferedImage image, int xLocation, int yLocation)
	{
	    //how do we scale the image??

	    super(image, xLocation, yLocation);

	}
    public void setMovementSpeed(double newSpeed)
    {
	movementSpeed = newSpeed; 
    }

    public void rotate()
    {
	java.awt.image.BufferedImage rotatedImage = ImageUtil.rotate(getImage(), 45);
	setImage(rotatedImage);
    }
    
    // public void hitSomething()
    // {
    // 	System.out.println("BOOM");
    // }
    
}
