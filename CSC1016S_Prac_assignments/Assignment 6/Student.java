//TOKELO MAKOLOANE
//MKLTOK002
//03 OCTOBER 2021

//SUBCLASS

public class Student extends Person
{
   public String school;
   public String study;
   public int year;
   public String hobbies;
   
   //a empty constructor.
   public Student()
   {
      super();
      this.school = "";
      this.study = "";
      this.year = 0;
      this.hobbies = "";
   }
   
   //a non empty constructor to initialize the instances
   public Student(String name,int age,String gender,String school,
                       String study,int year,String hobbies)
   {
      super(name, age, gender);
      this.school = school;
      this.study = study;
      this.year = year;
      this.hobbies = hobbies; 
   } 
   
   //a non empty constructor to initialize the instances
   public Student(Student object)
   {
      super(object);
      this.school = object.school;
      this.study = object.study;
      this.year = object.year;
      this.hobbies = object.hobbies; 
   } 
   
   //a method that sets institution
   public void setSchool(String newschool)
   {
      school = newschool;
   }
   
   //a method that sets programme of study
   public void setStudy(String newstudy)
   {
      study = newstudy;
   }
   
   //a method that sets year of study
   public void setYear(int newyear)
   {
      year = newyear;
   }
   
   //a method that sets hobbies
   public void setHobby(String newhobby)
   {
      hobbies = newhobby;
   }
   
   //a method that extract institution
   public String getSchool()
   {
      return school;
   }
   
   //a method that extract the programme of study
   public String getStudy()
   {
      return study;
   }
   
   //a method that extract the year of study.
   public int getYear()
   {
      return year;
   }
   
   //a method that extract the hobbies
   public String getHobby()
   {
      return hobbies;
   }
   
   //a method for string representation of the Student
   public String toString()
   {
      return "Name of student: "+getName()+"\n"+"Age: "+getAge()+"\n"+
      "Gender: "+getGender()+"\n"+"Name of institution: "+school+"\n"+
      "Year of study: "+year+"\n"+"Programme of study: "+study+"\n"+
      "Hobbies: "+hobbies;                          
   }
}