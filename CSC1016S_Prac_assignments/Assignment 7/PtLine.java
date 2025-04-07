//Tokelo Makoloane
//MKLTOK002
//16 October 2021
import java.lang.Math;

public class PtLine extends VectorObject
{
   
   private int x,y,bx,by;
   
   //a constructor to initialize the instatnces
   public PtLine(int id,int x,int y,int bx,int by)
   {
      super(id,x,y);
      this.x = x;
      this.y = y;
      this.bx = bx;
      this.by = by;
   }
   //the draw method to draw the diagonal lines 
   public void draw(char[][] matrix)
   { 
      boolean steep = Math.abs(this.by-this.y)>Math.abs(this.bx-this.x);
      int cx;
      //swaping coordinates
      if(steep){
         cx = this.x;
         //swaping the initial and new x with initial and new y respectively
         this.x = this.y;
         this.y = cx;
         cx = this.bx;
         this.bx = this.by;
         this.by = cx; 
      }
      //swaping coordinates
      if(this.x>this.bx){
         //swaping the initial x and new x also the initial y with new y
         cx = this.x;
         this.x = this.bx;
         this.bx = cx;
         cx = this.y;
         this.y = this.by;
         this.by = cx;
      }
      //setting the new y if initial y is less than new y
      int deltaX = this.bx - this.x;
      if (deltaX != 0) {
            int deltaY = Math.abs(this.by - this.y);
            double error = 0;
            double deltaError = (double) deltaY / (double) deltaX;
            int yStep;
            int ys = this.y;
            yStep = this.y < this.by ? 1 : -1;
            for (int xs = this.x; xs <= this.bx; xs++) {
                if (steep) {
                    matrix[xs][ys] = '*';
                } else {
                    matrix[ys][xs] = '*';
                }
                error += deltaError;
                if (error > 0.5) {
                    ys += yStep;
                    error--;
                }
            }
        } else {
            for ( int y1=0; y1<(this.by - this.y); y1++ )
                matrix[this.y+y1][this.x] = '*';
        }
   }
}