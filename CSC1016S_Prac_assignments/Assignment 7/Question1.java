//Tokelo Makoloane
//MKLTOK002
//12 October 2021
import java.util.ArrayList;
import java.util.Scanner;

public class Question1
{
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      Base store = new Base();
      Base screen = new Screen();
      Base box = new Box();
      Base acc  = new Accessories();
      System.out.println("Welcome to Great International Technology");
      System.out.println("MENU: add (B)ox, add (S)creen, add (A)ccessories, (D)elete, (L)ist, (Q)uit");
      String option = input.next().toLowerCase(); //input for all commands
      while(!option.equals("q")) //if the input is "q" the loop will terminate
      {
         if(option.equals("b"))
         {
            System.out.println("Enter the serial number");
            input.nextLine();
            String s = (input.nextLine());
            System.out.println("Enter the manufacturer");
            String m = (input.nextLine());
            System.out.println("Enter the colour");
            String c = (input.nextLine());
            System.out.println("Enter the amount of memory (MB)");
            long mm =(input.nextLong());
            store.Add(new Box(s,m,c,mm));        //this will add the box instance in the array
            System.out.println("Done");
            System.out.println("MENU: add (B)ox, add (S)creen, add (A)ccessories, (D)elete, (L)ist, (Q)uit");
         }
         else if(option.equals("s"))
         {
            System.out.println("Enter the serial number");
            input.nextLine();
            String s = (input.nextLine());
            System.out.println("Enter the manufacturer");
            String m = (input.nextLine());
            System.out.println("Enter the colour");
            String c = (input.nextLine());
            System.out.println("Enter the screen size in inches");
            long S = (input.nextLong());
            store.Add(new Screen(s,m,c,S));       //this will add the box instance in the array
            System.out.println("Done");
            System.out.println("MENU: add (B)ox, add (S)creen, add (A)ccessories, (D)elete, (L)ist, (Q)uit");
         }
         else if(option.equals("a"))
         {
            System.out.println("Enter the serial number");
            input.nextLine();
            String s = (input.nextLine());
            System.out.println("Enter the manufacturer");
            String m  = (input.nextLine());
            System.out.println("Enter the colour");
            String c = (input.nextLine());
            store.Add(new Accessories(s,m,c));    //this will add the box instance in the array
            System.out.println("Done");
            System.out.println("MENU: add (B)ox, add (S)creen, add (A)ccessories, (D)elete, (L)ist, (Q)uit");
         }
         else if(option.equals("d"))
         {
            System.out.println("Enter the serial number");
            input.nextLine();
            String serial = input.nextLine();
            if(box.Check(serial)==true || screen.Check(serial)==true || acc.Check(serial)==true)
            {
               store.deleteItem(serial);
               System.out.println("Done");
               System.out.println("MENU: add (B)ox, add (S)creen, add (A)ccessories, (D)elete, (L)ist, (Q)uit");
            }
            else{
               System.out.println("Not found");
               System.out.println("MENU: add (B)ox, add (S)creen, add (A)ccessories, (D)elete, (L)ist, (Q)uit");
            }
            
         }
         else if(option.equals("l"))
         {
            for(Base i : Base.Array()){
               System.out.println(i);
            }
            System.out.println("Done");
            System.out.println("MENU: add (B)ox, add (S)creen, add (A)ccessories, (D)elete, (L)ist, (Q)uit");
         }
         else{
            System.out.println("Not found");
            System.out.println("MENU: add (B)ox, add (S)creen, add (A)ccessories, (D)elete, (L)ist, (Q)uit");
         }
         option = input.next().toLowerCase();
      }
   }
}


//base class
class Base
{
   private String serialNumber;
   private String manufacturer;
   private String colour;
   private static ArrayList<Base> arrayOfboxes;
   
   //a empty and non-empty constructor to initialize instances
   public Base()
   {
      this("","","");
      arrayOfboxes = new ArrayList<Base>();
   }
   
   public Base(String serialNumber,String manufacturer,String colour)
   {
      this.serialNumber = serialNumber;
      this.manufacturer = manufacturer;
      this.colour = colour;
   }
   
   //add method to add elements of Base type in an arraylist
   public void Add(Base instance)
   {
      arrayOfboxes.add(instance);  
   } 
   
   //a method to delete a Base instance given a serial number
   public void deleteItem(String serial)
   {
      arrayOfboxes.removeIf(object -> (object.getSerialnum().equals(serial)));
   }
   
   //methods to get the instance variables
   protected String getSerialnum(){return serialNumber;}
   protected String getManufac(){return this.manufacturer;}
   protected String getColour(){return this.colour;}
   
   //a method to check if a serial number exist in a Base instance
   public boolean Check(String serial)
   {  boolean bool = false;
      if (arrayOfboxes.isEmpty()){
         return false;
      }
      else{
         for(Base i:arrayOfboxes){
            if(i.getSerialnum().equals(serial)){
               bool = true;
            }
         }
         return bool;
      }    
   }
   
   //a method to return arrayofboxes
   public static ArrayList<Base> Array()
   {
      return arrayOfboxes;
   }
   
   //String representation of Base
   public String toString()
   {
      return serialNumber+", "+manufacturer+", "+colour;
   }
}

//subclass
class Accessories extends Base
{
   //a empty and non-empty constructor to initialize the instances
   public Accessories(){
      this("","","");
   }
   public Accessories(String serialNumber,String manufacturer,String colour)
   {
      super(serialNumber,manufacturer,colour);
   }
   
   //string representation of Accessories
   public String toString()
   {
      return "Accessories: "+super.toString();
   }
}
//subclass
class Box extends Base
{
   private long amountOfmemory;
   
   //a empty and non-empty constructor for initializationg
   public Box(){
      this("","","",0);
   }
   public Box(String serialNumber,String manufacturer,String colour,
                                                  long amountOfmemory)
   {
      super(serialNumber,manufacturer,colour);
      this.amountOfmemory = amountOfmemory;
   }
   
   //string representation of Box
   public String toString()
   {
      return "Box: "+super.toString()+", "+this.amountOfmemory;
   }
}
//subclass
class Screen extends Base
{
   public long size;
   //empy constructor
      public Screen(){
         this("","","",0);
      }
   //constructor to initialize the instances
   public Screen(String serialNumber,String manufacturer,String colour,
                                                            long size){
      super(serialNumber,manufacturer,colour);
      this.size = size;
   }
      
   //string representation of screen
   public String toString(){
      return "Screen: "+getSerialnum()+", "+getColour()+", "+
               getManufac()+", "+this.size;
   }

}