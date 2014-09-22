
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.util.ArrayList;


import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;

public class TangramGame extends Game
{

    Shapez current_shape;

    ImageBackground background;
    
    SpriteGroup SHAPE_GROUP;

     boolean first_click = true;

    //would an array work better than an array list?
    ArrayList<Shapez> arr = new ArrayList<Shapez>();
    
    
    public void initResources()
    {

	Shapez greenTriangle = new Shapez(getImage("GreTri.png"), 100, 300);
	//add to array list
	arr.add(greenTriangle);

	Shapez blueTrapezoid = new Shapez(getImage("BluTra.png"), 100, 300);
	//add to array list
	arr.add(blueTrapezoid);

	Shapez purpleTriangle = new Shapez(getImage("PurTri.png"), 100, 300);
	//add to array list
	arr.add(purpleTriangle);

	Shapez redTriangle = new Shapez(getImage("RedTri.png"), 100, 300);
	//add to array list
	arr.add(redTriangle);

	Shapez orangeTriangle = new Shapez(getImage("OraTri.png"), 100, 300);
	//add to array list
	arr.add(orangeTriangle);

	Shapez yellowSquare = new Shapez(getImage("YelSqu.png"), 100, 300);
	//add to array list
	arr.add(yellowSquare);


	Shapez maroonTriangle = new Shapez(getImage("MarTri.png"), 100, 300);
	//add to array list
	arr.add(maroonTriangle);


	//is this still necessary here?
	//current_shape = arr.remove(0);
	
	background = new ImageBackground(getImage("bluebackground.jpg"));

	SHAPE_GROUP = new SpriteGroup("Shape Group");
	SHAPE_GROUP.add(yellowSquare);
	SHAPE_GROUP.add(greenTriangle);
	SHAPE_GROUP.add(blueTrapezoid);
	SHAPE_GROUP.add(purpleTriangle);
	SHAPE_GROUP.add(redTriangle);
	SHAPE_GROUP.add(orangeTriangle);
	SHAPE_GROUP.add(maroonTriangle);

       	   
    }

 

    public void update(long elapsedTime)
    {
	//how do we make thi loop work??



		while(arr.size() > 0 /*there are still objects in the array list*/)
		     {
	       		current_shape = arr.get(0);
	
			current_shape.setLocation(getMouseX(), getMouseY());


			if(click() && first_click)
			    {  
				System.out.println("yay");
				current_shape = arr.remove(0);
				first_click = false;
			    }

			if(bsInput.isMouseReleased(0))
			    {
				first_click = true;
				current_shape = arr.remove(0);
			    }
			
        
		     }


	SHAPE_GROUP.update(elapsedTime);
	

    	

	// if(keyDown(KeyEvent.VK_DOWN))
	//     {
	// 	//rotate sprite 45 degrees
	// 	//suggested code from StackOverflow.com
	//      //current_shape.setRotation(1.5);
	//     }

	


     }
    
    public void render(Graphics2D g)
    {
	background.render(g);
	SHAPE_GROUP.render(g);
    }

    public static void main(String[] args) throws InterruptedException
    {
	GameLoader game = new GameLoader();
	game.setup(new TangramGame(), new Dimension(700,700), false);
	game.start();
    }

}
