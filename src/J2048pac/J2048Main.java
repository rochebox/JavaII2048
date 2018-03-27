package J2048pac;

import javax.swing.JFrame;

public class J2048Main
{

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub
    JFrame f = new JFrame(" 2048 -- Join the numbers and get the 2048 Tile");
    f.setSize(600, 600);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //J2048Game g = new J2048Game(600, 600-20);
    J2048Game g = new J2048Game(600, 600-20);
    f.add(g);
    f.setVisible(true);

    
    
  }

}
