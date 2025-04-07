//TOKELO MAKOLOANE
//MKLTOK002
//02 OCTOBER 2021

//SUBCLASS

public class Rectangle extends Shape
{
   public double length;
   public double width;
    
   //empty constructor.
   public Rectangle()
   {
      super();
      length = 0;
      width = 0; 
   }
   
   //a non empty constructor to initialize the instances
   public Rectangle(String name, String colour, double length, double width)
   {
      super(name, colour);
      this.length = length;
      this.width = width;
   }
   
   //a non empty constructor to initialize the instances
   public Rectangle(Rectangle object)
   {
      super(object);
      this.length = object.length;
      this.width = object.width;
   }
   
   //a method that sets new length
   public void setLength(double newlength)
   {
      if(newlength <= 0)
      {
         System.out.println("length should be greator and nor zero");
      }
      else
         this.length = newlength;
   }
   
   //a method to set new width
   public void setWidth(double newwidth)
   {
      if(newwidth <= 0)
         {
            System.out.println("width should be greator and nor zero");
         }
         else
            this.width = newwidth;
   }
   
   //a method that get the length of the shape
   public double getLength()
   {
      return length;
   }
   
   //a method that get the width of the shape
   public double getWidth()
   {
      return width;
   }
   
   //a method for string representation of the shape
   public String toString()
   {
      return (getName()+" "+getColour()+" Length "+length+" Width "+width); 
   }
}