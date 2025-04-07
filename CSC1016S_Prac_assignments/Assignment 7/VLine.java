//Tokelo Makoloane
//MKLTOK002
//16 October 2021

public class VLine extends VectorObject
{
   private int len;
   
   //a constructor to initialize the instatnces
   public VLine(int id,int x,int y,int vlen)
   {
      super(id, x, y);
      len = vlen; 
   }
   
   //the draw method to draw the vertical lines
   public void draw(char[][] matrix)
   {
      //this will sametime yield the initial position of the coordinates
      for(int i=0;i<len;i++){
         matrix[y+i][x]='*';  
      }
   }
}
