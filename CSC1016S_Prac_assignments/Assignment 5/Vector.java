// TOKELO MAKOLOANE
// MKLTOK002
// 23 September 2021
import java.lang.Math.*;

class Vector
{  
   private double x = 0;
   private double y = 0;

   //this will initialize the double instance variables to respective values
   public Vector(double x, double y)
   {
      this.x = x;
      this.y = y;
   }
  
   //obtain the magnitude of inputted vectors.
   public double getMagnitude()
   {
      return Math.sqrt(Math.pow(this.x,2) + Math.pow(this.y,2)); 
   }
   
   //obtain the addition of two vectors.
   public Vector add(Vector v)
   {
      double vx = this.x + v.x;
      double vy = this.y + v.y;
      return new Vector(vx, vy);
   } 
   
   //obtain the multiplication between a vector and scalar;
   public Vector multiply(double scalar)
   {
      return new Vector(scalar*this.x, scalar*this.y);
   }
   
   //obtain the dot product between vectors.
   public double dotProduct(Vector v)
   {
      return this.x*v.x + this.y*v.y;
   }
   
   //this will represent all input int values in a vector format.
   public String toString()
   {
      return "v = (" + String.format("%.2f", (double)this.x) +", "+ String.format("%.2f", (double)this.y)+")";
    
   }
}