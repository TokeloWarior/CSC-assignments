// TOKELO MAKOLOANE
// MKLTOK002
// 02 OCTOBER 2021

//SUPER CLASS

public class Shape
{
   public String name;
   public String colour;
   
   //an empty constructor.
   public Shape()
   {
      this("no name","no colour");  
   }
   
   //a non empty constructor to initialize the instances
   public Shape(String name, String colour)
   {
      this.name = name;
      this.colour = colour;
   } 
   
   //a non empty constructor to initialize the instances
   public Shape(Shape object)
   {
      this.name = object.name;
      this.colour = object.colour;
   }
   
   //a method that sets new name.
   public void setName(String newname)
   {
      this.name = newname;
   }
   
   //a method that sets new colour.
   public void setColour(String newcolour)
   {
      this.colour = newcolour;
   }
   
   //a method to get the name of the shape.
   public String getName()
   {
      return name;
   }
   
   //a method to get the colour 
   public String getColour()
   {
      return colour;
   }
   
   //a method for string representation of the name and colour.
   public String toString()
   {
      return name +" "+colour;
   }
}