import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class SimpleCollision2 extends BasicCollisionGroup {
    TangramGame myGame;
    public SimpleCollision2(TangramGame _myGame)
    {
	super();
	myGame = _myGame;
	//	pixelPerfectCollision = true;
    }



   public void collided(Sprite s1, Sprite s2) {
	   System.out.println("COLLISION DETECTED in collided method!");

	   Shapez h = (Shapez)s1;


	   Shapez e = (Shapez)s2;

	   if( myGame.getCurrentShape().equals( e))
	       {
		   System.out.println("current shape is e");
		   
	       }
	   else if( myGame.getCurrentShape().equals( h))
	       {
		   System.out.println("current shape is h");
	       }

   }

}
