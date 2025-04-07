//TOKELO MAKOLOANE
//MKLTOK002
//03 October 2021

//Subclass

public class Car extends Vehicle
{
   public int passengers;
   public double carWeight;
   
   //an empty constructor
   public Car()
   {
      this(0,"",null,0,0.0);
   }
   
   //a non empty constructor to initialize the instances
   public Car(int numCylinders, String maker, Student owner, 
                                     int passengers, double carWeight)
   {
      super(numCylinders, maker, owner);
      this.passengers = passengers;
      this.carWeight = carWeight;
   }
   
   public String toString()
   {
      return maker+", "+numCylinders+" cylinders, "+"owned by "+
            owner.getName()+", "+passengers+" seater, "+"weighs "+
            carWeight+" kg";
   }
}