import java.awt.*;
import java.awt.geom.*;

public class Shapes
{
    Image image;
    int xLocation;
    int yLocation;

    public Shapes(java.awt.image.BufferedImage _image, int _xLocation, int _yLocation)
	{
	    image = _image;
	    xLocation = _xLocation;
	    yLocation = _yLocation;
		
	}
    //this is supposed to be a method

    public void makeSprite()
    {
	//are we using the sprite methods properly??

        Sprite shape  = new Sprite(image);
	shape.setPosition(xLocation, yLocation);
	shape.paint();
    }

}
