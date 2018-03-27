 package J2048pac;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class J2048Game extends JPanel implements KeyListener
{
  public static final int SQUARES_ON_SIDE = 4;
  int pWidth;
  int pHeight;
  //Color bColor = new Color(209, 191, 175);
  //Color bColor = new Color(239, 143, 57);
  Color bColor = Color.DARK_GRAY;
  Border bored =  BorderFactory.createRaisedBevelBorder();
 GameBox [][] game = new GameBox[SQUARES_ON_SIDE][SQUARES_ON_SIDE];
 
 
  
  public J2048Game(int w, int h)
  {
      pWidth = w;
      pHeight = h;
    
      this.setSize(pWidth, pHeight);
      this.setBackground(bColor);
      this.setBorder(bored);
      
      int border = 20;
      int gameBoxW = (pWidth - (border*5)) / 4;
      int gameBoxH = (pHeight - (border*5)) / 4;
      //System.out.println("gameBoxW is: " + gameBoxW + ", and gameBoxH is " + gameBoxH);
      //System.out.println("pWidth is: " + pWidth + ", and pHeight " + pHeight);
      
      //make the game squares
      for(int row = 0 ; row < 4; row++){
          int boxYLoc = border + ((gameBoxH + border) * row);
 
          for(int col = 0 ; col < 4; col++){
            int boxXLoc = border + ((gameBoxW + border) * col);
            game[row][col]  = new GameBox(boxXLoc, boxYLoc, gameBoxW, gameBoxH );
            
          }  
      }
      
      // This is at the bottom of the J2048Game Constructor...
      this.addKeyListener(this);
      this.setFocusable(true);   //<------Pls Add this magical command....:-)
      
      this.setUpGame();
      
      game[0][2].setBoxNumber(2);
     
  }
  
 
  
    public void setUpGame( )
    {
          boolean done = false;
          int count = 0;
          while(!done)
          {
                int randRow = (int)(Math.random() * 4);
                int randCol = (int)(Math.random() * 4);
                
                if(  game[randRow][randCol].getBoxNumber() == 0 )
                {
                  game[randRow][randCol].setBoxNumberInitial( 2 ) ;
                  count++;
                }
                
              if(count >= 2) done = true;
              
          }
    }
    
  
  public void paintComponent(Graphics g)
  {
    
        g.setColor(bColor);
        g.fillRect(0, 0, pWidth, pHeight);
        int loopStop = this.SQUARES_ON_SIDE;
        for(int row = 0; row < loopStop; row++)
        {
              for(int col = 0; col < loopStop; col++)
              {
                  game[row][col].drawBox(g);
              }
          
          
        }
    
    
  }

  public void keyTyped(KeyEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  public void keyPressed(KeyEvent e)
  {
    
    // right  39
    // left 37
    //up 38
    // down 40
        // TODO Auto-generated method stub
       // System.out.println("Hey you pressed a key and  the keyCode is " + e.getKeyCode());
        
        //if(e.getKeyCode() == 39) moveRight();
        
        switch(e.getKeyCode()){
                      case 39:  moveRight();
                                      break;
                                      
                      case 37:  moveLeft();
                                      break;
                                      
                      case 38:  moveUp();
                                      break;
                     
                      case 40:  moveDown();
                                      break;
        
                      default:
                                    javax.swing.JOptionPane.showMessageDialog(null, "HEY WRONG KEY");
                                     break;
        
        
        }
    
    
    
  }
  // these routines handle keypressed moves....for the whole board.
  public void moveRight(){
      System.out.println("Move Right");
      
      //Following algorithm on board....
      for(int row = 0; row < 4; row++){
          int col = 2;
          if(game[row][col].getBoxNumber() > 0){
            
            if(game[row][col +1].getBoxNumber() == game[row][col].getBoxNumber()
                || game[row][col +1].getBoxNumber() == 0){
                    //move the number over to col + 1
                  int n1 = game[row][col].getBoxNumber();
                  int n2 = game[row][col+1].getBoxNumber();
                  game[row][col+1].setBoxNumber(n1 + n2);
                  game[row][col].setBoxNumber(0);
            }
            
          }
        
        repaint();
        
      }
      
      
      
      
      
      
      
      
  }
  
 public void moveLeft(){
      System.out.println("Move Left");
  }
 
 
 public void moveUp(){
    System.out.println("Move Up");
 }
 
 public void moveDown(){
   System.out.println("Move Down");

 }


  public void keyReleased(KeyEvent e)
  {
    // TODO Auto-generated method stub
    
  }
  
  
    
  

}
