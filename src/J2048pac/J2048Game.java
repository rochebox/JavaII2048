 package J2048pac;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
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
      
      //game[0][2].setBoxNumber(2);
     
  }
  
  public void resetBoxes(){
    
        for(int r = 0; r < 4; r++){
                for(int c = 0; c < 4; c++){
                   game[r][c].setMerge(false);
                  
                }
        }
    
    
    
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
        
        
        }  // done with the switch
        this.newBox();
        this.clearMergeData();
  }
  
  public void clearMergeData(){
    
        for(int r = 0; r < 4; r++){
                    for(int c = 0; c < 4; c++){
                      game[r][c].setMerge(false);
                      
                    }
        }
    
  }
  
  // these routines handle key pressed moves....for the whole board.
  public void moveRight(){
      System.out.println("Move Right");
      
      //Following algorithm on board....
      for(int row = 0; row < 4; row++){
         // int col = 2;
       
        // repeat this next process 3 times....
        for(int i = 0; i < 3; i++) {
              for(int col = 2; col >=0; col--) {
                 
                  if( game[row][col].getBoxNumber() > 0 )
                  {
                    
                         if( game[row][col +1].getBoxNumber() == 0 )   
                         {
                           game[row][col+1].setBoxNumber( game[row][col].getBoxNumber());
                           game[row][col].setBoxNumber(0);
                         }
                             //***********************The extra check for to handle if there has been a previous merge.
                             else if (  (game[row][col +1].getBoxNumber() == game[row][col].getBoxNumber())
                             && !game[row][col].checkMerge()   // *** EXTRA CHECK
                             && !game[row][col+1].checkMerge()   // ***EXTRA CHECK 
                             )
                       {
                                  //move the number over to col + 1
                                int n1 = game[row][col].getBoxNumber();
                                int n2 = game[row][col+1].getBoxNumber();
                                game[row][col+1].setBoxNumber(n1 + n2);
                                //game[row][col+1].doubleIt();
                                game[row][col].setBoxNumber(0);
                                //******** THIS ALSO HAS TO BE ADDED SO THAT WE REMEMBER THAT WE MERGED
                                game[row][col +1].setMerge(true);
                                game[row][col].setMerge(false);
                          }
                  }  // END OF IFS
              }   //END OF COL LOOP
        }  //END OF I LOOP
      } // END OF ROW LOOP
          
    
        repaint();
        this.resetBoxes();
        
      } // END OF METHOD
      
      
      
      
      
      
      
      
  
  
 public void moveLeft(){
      System.out.println("Move Left");
      
      //Following algorithm on board....
      for(int row = 0; row < 4; row++){
         // int col = 2;
       
        // repeat this next process 3 times....
        for(int i = 0; i < 3; i++) {
          for(int col = 1; col <4; col++) {
              if( game[row][col].getBoxNumber() > 0 )
              {
                
                     if( game[row][col -1].getBoxNumber() == 0 ) 
                     {
                       game[row][col-1].setBoxNumber( game[row][col].getBoxNumber());
                       game[row][col].setBoxNumber(0);
                     }
                         //***********************The extra check for to handle if there has been a previous merge.
                         else if (  (game[row][col -1].getBoxNumber() == game[row][col].getBoxNumber())
                         && !game[row][col].checkMerge()   // *** EXTRA CHECK
                         && !game[row][col-1].checkMerge()   // ***EXTRA CHECK 
                         )
                   {
                              //move the number over to col + 1
                            int n1 = game[row][col].getBoxNumber();
                            int n2 = game[row][col-1].getBoxNumber();
                            game[row][col-1].setBoxNumber(n1 + n2);
                            //game[row][col+1].doubleIt();
                            game[row][col].setBoxNumber(0);
                            //******** THIS ALSO HAS TO BE ADDED SO THAT WE REMEMBER THAT WE MERGED
                            game[row][col -1].setMerge(true);
                            game[row][col].setMerge(false);
                      }
                
              }
          } 
        }
      }
      repaint();
      this.resetBoxes();
  }
 
 
 public void moveDown(){
   
   System.out.println("Move Down");
   
   //Following algorithm on board....
   for(int col = 0; col < 4; col++){
    
    
     // repeat this next process 3 times....
     for(int i = 0; i < 3; i++) {
       for(int row = 2; row >=0; row--) {
           if( game[row][col].getBoxNumber() > 0 )
           {
             
                  if( game[row +1 ][col].getBoxNumber() == 0 ) 
                  {
                    game[row + 1][col].setBoxNumber( game[row][col].getBoxNumber());
                    game[row][col].setBoxNumber(0);
                  }
                      //***********************The extra check for to handle if there has been a previous merge.
                      else if (  (game[row + 1][col ].getBoxNumber() == game[row][col].getBoxNumber())
                      && !game[row][col].checkMerge()   // *** EXTRA CHECK
                      && !game[row + 1][col].checkMerge()   // ***EXTRA CHECK 
                      )
                {
                           //move the number over to col + 1
                         int n1 = game[row][col].getBoxNumber();
                         int n2 = game[row + 1][col].getBoxNumber();
                         game[row + 1][col].setBoxNumber(n1 + n2);
                         //game[row][col+1].doubleIt();
                         game[row][col].setBoxNumber(0);
                         //******** THIS ALSO HAS TO BE ADDED SO THAT WE REMEMBER THAT WE MERGED
                         game[row + 1][col ].setMerge(true);
                         game[row][col].setMerge(false);
                   }
             
           }
       } 
     }
   }
       
     
     repaint();
     this.resetBoxes();
   
 }
 
 public void moveUp(){
   
   
   System.out.println("Move Up");
   
   //Following algorithm on board....
   for(int col = 0; col < 4; col++){
      // int col = 2;
    
     // repeat this next process 3 times....
     for(int i = 0; i < 3; i++) {
       for(int row = 1; row <4; row++) {
           if( game[row][col].getBoxNumber() > 0 )
           {
             
                  if( game[row-1][col ].getBoxNumber() == 0 ) 
                  {
                    game[row-1][col].setBoxNumber( game[row][col].getBoxNumber());
                    game[row][col].setBoxNumber(0);
                  }
                      //***********************The extra check for to handle if there has been a previous merge.
                      else if (  (game[row-1][col ].getBoxNumber() == game[row][col].getBoxNumber())
                      && !game[row][col].checkMerge()   // *** EXTRA CHECK
                      && !game[row-1][col].checkMerge()   // ***EXTRA CHECK 
                      )
                {
                           //move the number over to col + 1
                         int n1 = game[row][col].getBoxNumber();
                         int n2 = game[row-1][col].getBoxNumber();
                         game[row-1][col].setBoxNumber(n1 + n2);
                         //game[row][col+1].doubleIt();
                         game[row][col].setBoxNumber(0);
                         //******** THIS ALSO HAS TO BE ADDED SO THAT WE REMEMBER THAT WE MERGED
                         game[row-1][col].setMerge(true);
                         game[row][col].setMerge(false);
                   }
             
           }
       } 
     }
   }
   repaint();
   this.resetBoxes();
 }


  public void keyReleased(KeyEvent e)
  {
    // TODO Auto-generated method stub
    
  }
  
public void newBox(){
  
        //if there is the possibility of making a new box we will make it
      
        // Part 1 -- See if its possible to add a box....  think about how to do that
        boolean possibleToAddABox = false;
  
        for(int r = 0 ; r < 4; r++){
              for(int c = 0 ; c < 4; c++){
                  if(game[r][c].getBoxNumber() == 0){
                    possibleToAddABox = true;
                  }
              }
    
        }
  
  
        // Part 2 -- If its possible the open space and put a new number in
      
        if(possibleToAddABox){
                boolean done = false;
                
                while(!done){
                  
                      int randRow = (int)(Math.random() * 4);
                      int randCol = (int)(Math.random() * 4);
                      
                      if(game[randRow][randCol].getBoxNumber()==0       ) {  //if 0 its open...
                        game[randRow][randCol].setBoxNumberInitial(2);
                        done = true;
                      }
                  
                }
        }  else {
          JOptionPane.showMessageDialog(null, "Game Over, People...");
        }
  
  
       repaint();
  
  
}
  
  
  
  
  
  
  
  
  
  
  
  
  
  public void newBox2(){
    
    boolean done = false;
    boolean thereIsAnEmptyOne = false;
    
    //Part1 look for empty one:
    for(int row = 0; row < 4; row++)
    {
          for(int col = 0; col < 4; col++)
          {
            if(game[row][col].getBoxNumber()== 0) {
              thereIsAnEmptyOne = true;
              break;
            }
          }
    }
    
    if(thereIsAnEmptyOne) {
        while(!done ){
          int randRow = (int) (Math.random() * 4);
          int randCol = (int) (Math.random() * 4);
          
          if(game[randRow][randCol].getBoxNumber()== 0){
            game[randRow][randCol].setBoxNumberInitial(2);
            done = true;
          }
          
        }
    } else {
      JOptionPane.showMessageDialog(null, "Game Over");
    }
    
    
    
  }
  
  
    
  

}
