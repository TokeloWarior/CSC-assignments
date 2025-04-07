//TOKELO MAKOLOANE
//MKLTOK002
//03 October 2021

//testclass
import java.util.Scanner;

public class Question2
{
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      System.out.println("Enter the vehicle manufacturer:");
      String manufacturer1 = input.nextLine();
      System.out.println("Enter the name of the vehicle owner:");
      Student Owner1 = new Student(input.nextLine(),0,"","","",0,"");
      System.out.println("Enter the number of cylinders in the engine:");
      int num1 = input.nextInt();
      Vehicle motomobile = new Vehicle(num1,manufacturer1,Owner1);
      System.out.println(motomobile);
       System.out.println();
       
      System.out.println("Enter the Car manufacturer:");
      input.nextLine();
      String manufact = input.nextLine();
      System.out.println("Enter the name of the car owner:");
      Student Owner2 = new Student(input.nextLine(),0,"","","",0,"");
      System.out.println("Enter the number of cylinders in the engine:");
      int num2 = input.nextInt();
      System.out.println("Enter the car seating capacity:");
      int seats = input.nextInt();
      System.out.println("Enter the weight of the car:");
      double weight = input.nextDouble();
      Car car = new Car(num2, manufact, Owner2, seats, weight);
      System.out.println(car);
      System.exit(0);
      
   }
}