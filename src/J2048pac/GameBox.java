package J2048pac;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

public class GameBox
{
      //public static final Color EMPTY = Color.GRAY;
     // public static final Color TWO = new Color()
      //public static final Color SIXTY4 = Color.RED;
     // public static final Color  SIXTEEN= Color.ORANGE;
      
      private Color[][] colorList = new Color[12][2];
      
      int boxW, boxH;
      int boxXLoc, boxYLoc;
      int boxNumber;
      int boxColorIndex;   //new variable added to give the
      int textHeight = 75;
      Font myFont = new Font("Arial", Font.BOLD, textHeight);
  
  
      public GameBox(int x, int y, int w, int h){
          boxW = w;
          boxH = h;
          boxXLoc = x;
          boxYLoc = y;
          boxNumber = 0;
          boxColorIndex = 0;  //boxColorIndex is the location on the list for the box colors
          
          loadBoxColors();  //this generates the list of box colors
        
      }
      
      
      public void loadBoxColors()
      {
        //0
        colorList[0][0] = Color.GRAY; 
        colorList[0][1] = Color.GRAY;  
        //2
        colorList[1][0] = new Color( 239, 228, 219);  //background color....
        colorList[1][1] = new Color( 119, 110, 102);  //text color
        //4
        colorList[2][0] = new Color( 238, 224, 203);
        colorList[2][1] = new Color( 119, 110, 102);
        //8
        colorList[3][0] = new Color( 245, 176, 128);
        colorList[3][1] = new Color( 248, 246, 243);
        //16
        colorList[4][0] = new Color( 249, 148, 106);
        colorList[4][1] = new Color( 249, 246, 242);
        //32
        colorList[5][0] = new Color( 251, 123, 101);
        colorList[5][1] = new Color( 249, 236, 230);       
        //64
        colorList[6][0] = new Color( 251, 92, 68);
        colorList[6][1] = new Color( 250, 246, 243);  
        //128
        colorList[7][0] = new Color( 238, 205, 126);
        colorList[7][1] = new Color( 250, 246, 242);  
        //256
        colorList[8][0] = new Color( 238, 202, 112);
        colorList[8][1] = new Color( 249, 246, 242);   
        //512
        colorList[9][0] = new Color( 237, 198, 99);
        colorList[9][1] = new Color( 249, 245, 240);  
        //1024
        colorList[10][0] = new Color( 238, 195, 88);
        colorList[10][1] = new Color( 249, 246, 242);  
        //2048
        colorList[11][0] = new Color( 255, 58, 66);
        colorList[11][1] = new Color( 251, 246, 243);         
      }
    
      
      public void drawBox(Graphics g){
         
          Graphics2D graphics2 = (Graphics2D) g;
          graphics2.setColor(colorList[boxColorIndex][0]);
          graphics2.fillRoundRect(
              boxXLoc, 
              boxYLoc, 
              boxW, 
              boxH, 20, 20);
          
          if(boxNumber > 0 )
          {
            
            g.setFont(myFont);
            g.setColor(colorList[boxColorIndex][1]);
            
            int textWidth = g.getFontMetrics().stringWidth(Integer.toString(boxNumber));
            // textHeight is in the class fields
            g.drawString(
                Integer.toString(boxNumber), 
                boxXLoc + ((boxW-textWidth)/2), 
                boxYLoc + (boxH/2)   + (textHeight/3) 
                );
          }
        
      }
    
      public void setBoxNumberInitial(int newNum)
      {
          boxNumber = newNum;
          boxColorIndex++;
      }
      
      public void doubleIt()
      {
          boxNumber *= 2;
          boxColorIndex++;
      }
      
      
      public int getBoxNumber()
      {
           return boxNumber;
      }
      
      public void setBoxNumber(int newNumber){
           boxNumber = newNumber;
           boxColorIndex++;
           if(boxNumber == 0){
             boxColorIndex = 0;
           }
      }

}
