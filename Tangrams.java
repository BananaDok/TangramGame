import javax.swing.JFrame;

public class Tangrams
{
  public static void main(String[] args)
  {
    JFrame frame = new JFrame();
    frame.setSize(500, 500);
    frame.setTitle("Tangrams!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // SpiritAnimalComponent e = new SpiritAnimalComponent();
    Shapes triangle = new Shapes(LowerRightTriangle.png, 100, 100);
    image x = triangle.makeSprite();

    //frame.add(e);
    frame.add(x);


     frame.setVisible(true);
  }
}
