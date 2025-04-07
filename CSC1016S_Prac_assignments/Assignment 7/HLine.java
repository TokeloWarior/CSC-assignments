//Tokelo Makoloane
//MKLTOK002
//16 October 2021

public class HLine extends VectorObject
{
   private int len;
   
   //a constructor to initialize the instatnces
   public HLine(int id,int x,int y,int Hlen)
   {
      super(id,x,y);
      len = Hlen;
   }
   
   //the draw method to draw the horizontal lines
   public void draw(char[][] matrix)
   {
      //this will sametime yield the initial position of the coordinates
      for(int i=0;i<len;i++){
         matrix[y][x+i]='*';  
      }
   }
}