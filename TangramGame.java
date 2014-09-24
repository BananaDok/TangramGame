
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.util.ArrayList;

import java.lang.InterruptedException;


import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;

public class TangramGame extends Game
{

    Shapez current_shape;

    Shapez current_suggested_shape;

    Shapez old_suggested_shape;

    ImageBackground background;
    
    SpriteGroup SHAPE_GROUP;

     boolean first_click = true;

    long rotateTime = 0;
    
    Graphics2D g;

 

    CollisionManager hitChecker;

    SpriteGroup SUGGESTED_SHAPES;

    //would an array work better than an array list?
    ArrayList<Shapez> arr = new ArrayList<Shapez>();

    ArrayList<Shapez> arrPlaced = new ArrayList<Shapez>();

    ArrayList<Shapez> suggestedImages = new ArrayList<Shapez>();

    //this array will hold the suggested shapes once they are off the screen
    ArrayList<Shapez> oldSuggestedImages = new ArrayList<Shapez>();
    
    
    public void initResources()
    {

	Shapez greenTriangle = new Shapez(getImage("GreTri.png"), 710, 300);
	//add to array list
	arr.add(greenTriangle);

	Shapez blueTrapezoid = new Shapez(getImage("BluTra.png"), 710, 300);
	//add to array list
	arr.add(blueTrapezoid);

	Shapez purpleTriangle = new Shapez(getImage("PurTri.png"), 710, 300);
	//add to array list
	arr.add(purpleTriangle);

	Shapez redTriangle = new Shapez(getImage("RedTri.png"), 710, 300);
	//add to array list
	arr.add(redTriangle);

	Shapez orangeTriangle = new Shapez(getImage("OraTri.png"), 710, 300);
	//add to array list
	arr.add(orangeTriangle);

	Shapez yellowSquare = new Shapez(getImage("YelSqu.png"), 710, 300);
	//add to array list
	arr.add(yellowSquare);


	Shapez maroonTriangle = new Shapez(getImage("MarTri.png"), 710, 300);
	//add to array list
	arr.add(maroonTriangle);


	//now, we will add the suggested images
	// suggested image gifs found on 
	//http://www.cimt.plymouth.ac.uk/resources/puzzles/tangrams/tangset3.htm

	//these are sprites so that we can use sprite qualities on them

	Shapez house = new Shapez(getImage("house.gif"), 0, 0);
	suggestedImages.add(house);
	Shapez swan = new Shapez (getImage("swan.gif"), 710 ,300);
	suggestedImages.add(house);
	Shapez shoe = new Shapez(getImage("shoe.gif"),710 ,300);
	suggestedImages.add(shoe);
	Shapez four = new Shapez(getImage("four.gif"),710 ,300);
	suggestedImages.add(four);
	
	SUGGESTED_SHAPES = new SpriteGroup("Image Group");
	SUGGESTED_SHAPES.add(house);
	SUGGESTED_SHAPES.add(swan);
	SUGGESTED_SHAPES.add(shoe);
	SUGGESTED_SHAPES.add(four);


	
	background = new ImageBackground(getImage("wood2background.jpg"));

	SHAPE_GROUP = new SpriteGroup("Shape Group");
	SHAPE_GROUP.add(yellowSquare);
	SHAPE_GROUP.add(greenTriangle);
	SHAPE_GROUP.add(blueTrapezoid);
	SHAPE_GROUP.add(purpleTriangle);
	SHAPE_GROUP.add(redTriangle);
	SHAPE_GROUP.add(orangeTriangle);
	SHAPE_GROUP.add(maroonTriangle);

	hitChecker = new SimpleCollision2(this);
	hitChecker.setCollisionGroup(SHAPE_GROUP, SHAPE_GROUP);
       	   
    }

    public Shapez getCurrentShape()
    {
	return current_shape;
    }

    public boolean checkOverlap(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2)
    {
	if(x2 > x1 + w1)
	    {
		return false;
	    }
	if(x1 > x2 + w2)
	    {
		return false;
	    }
	if(y2 > y1 + h1)
	    {
		return false;
	    }
	if(y1 > y2 + h2)
	    {
		return false;
	    }	
	else
	    {
		return true;
	    }
    }


    public boolean checkLegalLocation(int x, int y)
    {
        for (int i = 0; i < arrPlaced.size(); i++)
	    {
		Shapez other = arrPlaced.get(i);
		if(checkOverlap( (int)current_shape.getX(), (int)current_shape.getY(), current_shape.getWidth(), current_shape.getHeight(), (int)other.getX(), (int)other.getY(), other.getWidth(), other.getHeight() ) )
		    {
			return false;
		    }
		else
		    {
			return true;
		    }
	    }
	return true;
	    
    }

    public void update(long elapsedTime)
    {


		if(arr.size() > 0 /*there are still objects in the array list*/)
		     {
	        
			 current_shape = arr.get(0);

			 if (checkLegalLocation(getMouseX(), getMouseY() ) )
			     {
				 current_shape.setLocation(getMouseX(), getMouseY());
			     }

			 //hold the upkey, while moving the shape, to ovveride location
			 if (keyDown(KeyEvent.VK_UP))

			     {
				 current_shape.setLocation(getMouseX(), getMouseY());
			     }

			if(click() && first_click)
			    {  
				System.out.println("yay");
				current_shape = arr.remove(0);
				first_click = false;
			    }

			if(bsInput.isMouseReleased(1))
			    {
				System.out.println("resetting first click");
				arrPlaced.add(current_shape);
				first_click = true;
			    }
			//where should this statement go?? what does it do??	
			SHAPE_GROUP.update(elapsedTime);
				     }
	
	//
	//Here, we want to rotate current_shape with the down-arrow key
       	//
		if(keyDown(KeyEvent.VK_DOWN))
		    {
			//got sleep code from StackOverflow.com
			try
			    {
				//rotate sprite 45 degrees
				current_shape.rotate();
				Thread.sleep(75);    
			    } 
			catch(InterruptedException ex)
			    {
				Thread.currentThread().interrupt();
			    }
		    }



		//Changing the suggested images did not quite work,
		//but the first image still generates, form the start of the game.

       	// //the Right-down key should change the suggested image

	// 	current_suggested_shape = suggestedImages.get(0);


	// 	if(keyDown(KeyEvent.VK_RIGHT))
	// 	    {
	// 		try
	// 		    {
	// 			current_suggested_shape.setLocation(0,0);
	// 			oldSuggestedImages.add(current_suggested_shape);
			
	// 			Thread.sleep(75);
	
		       	
	// 		    }
	// 		catch(InterruptedException exx)
	// 		    {
	// 			Thread.currentThread().interrupt();
	// 		    }
	
	// 	    }

	// 	//the left-down key will remove the previous suggested image
	// 	//(because we could not figure out how to do it within the right-down key if statement)

	// 	if(keyDown(KeyEvent.VK_LEFT))
	// 	    {
	// 		try
	// 		    {
	// 				suggestedImages.remove(0);suggestedImages.remove(0);
	// 		        oldSuggestedImages.get(0).setLocation(710,600);
	// 			Thread.sleep(75);
	// 			oldSuggestedImages.remove(0);
	// 			Thread.sleep(75);
	
		       	
	// 		    }
	// 		catch(InterruptedException exxx)
	// 		    {
	// 			Thread.currentThread().interrupt();
	// 		    }
	
	// 	    }
     }


    
    public void render(Graphics2D g)
    {
	background.render(g);
	SHAPE_GROUP.render(g);
        SUGGESTED_SHAPES.render(g);
	
    }

    public static void main(String[] args) throws InterruptedException
    {
	System.out.println("Welcome to virtual Tangram!");
	System.out.println("Directions:");
	System.out.println("1. Click to set down a shape and generate the next shape.");
	System.out.println("2. Press the down key to rotate the shape 45 degrees, clockwise.");
	System.out.println("3. Hold the up key, while moving the shape, if it gets stuck.");
	System.out.println("4. Try to create the suggested image with the shapes.");
	GameLoader game = new GameLoader();
	game.setup(new TangramGame(), new Dimension(700,700), false);
	game.start();


    }

}
