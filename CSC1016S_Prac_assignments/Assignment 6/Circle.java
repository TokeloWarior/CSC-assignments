//TOKELO MAKOLOANE
//MKLTOK002
//02 OCTOBER 2021

//SUBCLASS

public class Circle extends Shape
{
   public double radius;
    
   //empty constructor.
   public Circle()
   {
      super();
      radius = 0;
   }
   
   //a non empty constructor to initialize the instances
   public Circle(String name, String colour, double radius)
   {
      super(name, colour);
      this.radius = radius;
   }
   
   //a non empty constructor to initialize the instances
   public Circle(Circle object)
   {
      super(object);
      this.radius = object.radius;
   }
   
   //a method that sets new radius
   public void setRadius(double newradius)
   {
      if(newradius <= 0)
      {
         System.out.println("radius should be greator and nor zero");
      }
      else
         this.radius = newradius;
   }
      
   //a method that get the radius of the circle
   public double getRadius()
   {
      return radius;
   }

   //a method for string representation of the shape
   public String toString()
   {
      return (getName()+" "+getColour()+" Radius "+radius); 
   }
}