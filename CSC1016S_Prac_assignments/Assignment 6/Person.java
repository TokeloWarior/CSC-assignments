//TOKELO MAKOLOANE
//MKLTOK002
//03 OCTOBER 2021

//SUPER CLASS

public class Person
{
   public String name;
   public int age;
   public String gender;
   
   //an empty constructor
   public Person()
   {
      this("no name",0,"unknown");
   }
   
   //a constructor to initialize the instances
   public Person(String name, int age, String gender)
   {
      this.name = name;
      this.age = age;
      this.gender = gender;
      
   }
   
   //a constructor to initialize the instances from the object parameter
   public Person(Person object)
   {
      this.name = object.name;
      this.age = object.age;
      this.gender = object.gender;
   }
   
   //a method to set newname
   public void setName(String newname)
   {
      name = newname;
   }
   
   //a method to set newage
   public void setAge(int newage)
   {
      age = newage;
   }
   
   //a method to update gender
   public void setGender(String newgender)
   {
      gender = newgender;
   }
   
   //a method to extract name
   public String getName()
   {
      return name;
   }
   
   //a method to extract age
   public int getAge()
   {
      return age;
   }
   
   //a method to get gender
   public String getGender()
   {
      return gender;
   }
   
   //a method for string representation of the person
   public String toString()
   {
      return "Name: "+name+" Age: "+age+" Gender: "+gender;
   }
}