//TOKELO MAKOLOANE
//MKLTOK002
//03 October 2021

//superclass

public class Vehicle
{
   public int numCylinders;
   public String maker;
   public Student owner;
   
   //an empty constructor
   public Vehicle()
   {
      this(0,"",null);
   }
   
   //a constructor to initialize the instances
   public Vehicle(int numCylinders, String maker, Student owner)
   {
      this.numCylinders = numCylinders;
      this.maker = maker;
      this.owner = owner;
   } 
   
   //a method for string representation of the vehicle
   public String toString()
   {
      return maker+", "+numCylinders+" cylinders, "+"owned by "+owner.getName();
   }
}