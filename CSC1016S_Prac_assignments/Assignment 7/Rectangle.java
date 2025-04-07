//Tokelo Makoloane
//MKLTOK002
//16 October 2021

public class Rectangle extends VectorObject
{
   private int xLen, yLen;
   
   public Rectangle(int id,int x,int y,int NxLen,int NyLen)
   {
      super(id,x,y);
      xLen = NxLen;
      yLen = NyLen;
   }
   
   //a method to draw rectangle starting at the specified x and y coordinate
   public void draw(char[][] matrix)
   {
      for (int i=0;i<yLen;i++){
         for (int j =0;j<xLen;j++){
            matrix[y+i][x+j]= '*';
         }
      }
   }
}