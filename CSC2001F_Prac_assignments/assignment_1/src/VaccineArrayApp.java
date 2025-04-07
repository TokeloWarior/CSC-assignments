//Tokelo Makoloane
//MKLTOK002
package com.Array.App;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VaccineArrayApp
{ 
    public static void main(String[] args)
    {
        int count = 0;
        Scanner input = new Scanner(System.in);     
        String[] stringArray = new String[1];    //array to store inputed countries.
        String[][] vaccineInfo = new String[9919][3]; //array to store info from the given vaccinations.csv file.
        String[] vaccineNumb = new String[9919];    //array to store the whole info about the countries and their vaccination numbers.
        String[] countryInfo = null;   //array to be stored in vaccineInfo array with country's name, date and vaccination numbers in it.
        int index1 = 0;
        int index2 = 0;
        int index3 = 0;
        Scanner content = null;          //an object created to access contents in the vaccination.csv using the Scanner class methods.
        System.out.println("Enter the date:");
        String date =  input.nextLine();
        System.out.println("Enter the list of countries (end with an empty line)");
        
        //this loop makes it possible to iteratively input countries until an empty line is input(ted)
        while(true)    
        {
            String country = input.nextLine();
            if(country.equals("")){break;}         //this will break the loop when an empty line is input(ted).
            
            if(index1 >= stringArray.length)      //this if statement is to make sure if the stringArray is full the code won't crash by...  
            {
                String[] copyStringArray = new String[index1+1];  //creating a copy of stringArray that has a size 1 greater than that of stringArray 
                for(int i = 0; i<stringArray.length; i++)
                {
                    copyStringArray[i] = stringArray[i];     //this will copy all elements of stringArray to copyStringArray 
                }
                stringArray = copyStringArray;     //it will iteratively define stringArray with copyStringArray, is like stringArray has infinite size/space
            }
            stringArray[index1] = country;    //iteratively append country into stringArray then after incrementing index1 for next position of country
            index1++;
        }
        
        try
        {
            content = new Scanner(new FileInputStream("vaccinations.csv"));
            
        }catch(FileNotFoundException e){
            System.out.println("File vaccinations.csv was not found or could not be open");
            System.exit(0);
        }
        
        while(content.hasNext())           //the while loop will stop reading the vaccination.csv file when it can't find a line with strings
        {
            countryInfo = content.nextLine().split(",");      //since countryInfo is in the loop this will iteratively split the nextLine in a file into a array
            if(index2 < vaccineInfo.length)
            {
                vaccineInfo[index2] = countryInfo;
                index2++;
            }
        }
        //loop to search through the outer array and check which inner arrays contain the input(ted) date
        while(index3<stringArray.length)
        {
            for(int i = 0;i<vaccineInfo.length;i++)
            {
               if(stringArray[index3].equals(vaccineInfo[i][0]))        //if the inputed country is equal the country in the file, check for the date...
               {
                  count++;
                  if(date.equals(vaccineInfo[i][1]))                   // if the date is now equal to the respective country's then.....
                  {      
                     count++;
                     if(vaccineInfo[i].length<3)                       //check if the data has a vaccine number, if it does out put it, if it does not output zero
                     {
                        vaccineNumb[index3] = stringArray[index3]+" = 0";
                        break;
                     }
                     vaccineNumb[index3] = stringArray[index3]+" = "+vaccineInfo[i][2];
                     break;
                  }
                  else{count++;
                      vaccineNumb[index3] = stringArray[index3]+" = <Not Found>";}     //else if there are no countries found that match with the date output not found.
               }
               else{
                   count++;
                   vaccineNumb[index3] = stringArray[index3]+" = <Not Found>";
              }
            }
            index3++;
        }
        
        System.out.println("The number of comparisons is: "+count);
        System.out.println();
        System.out.println("Results:");
        
        //a loop to output all the elements of vaccineNumb..
        for(int i = 0; i<index3; i++)
        {
            if(vaccineNumb[i] == null)
            {
               break;
            }
            System.out.println(vaccineNumb[i]);
        }    
    }
}
