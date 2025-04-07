//Tokelo Makoloane
//Practice generic class for Arraylist 

import java.lang.reflect.Array;
//a generic class to create a generic array
public class Arraylist<T extends Comparable>
{
   private T[] array;   //this will be used to instantiate the array of type T
   private int size;   //this will be used for the size of the array
   private int index=0;
   private  T[] copyArray;
   
   //a constructor that will instantiate the array with typecasting
   public Arraylist(Class<T> a,int capacity)
   { 
      this.array = (T[])Array.newInstance(a ,capacity);
      this.size = capacity;
   } 
   
   //a method to be used to add the relevant elements of type T in the array
   public void Add(T element)
   {  
      if(index >= array.length)
      {
         T[] copyA = (T[])Array.newInstance(array.getClass().getComponentType() ,index+1);;
         for(int i =0;i<array.length;i++)
         {
            copyA[i]=array[i];
         }
         array = copyA;
      }
      array[index] = element;
      index++;
   }
   
   //a methd to add an element at the certain position
   public void addAt(T element, int Index)
   {  
     //  int pos = 0;
//       if(Index >= array.length)
//       {
//          T[] copyA = (T[])Array.newInstance(array.getClass().getComponentType() ,Index+1);;
//          for(int i =0;i<array.length;i++)
//          {
//             copyA[i]=array[i];
//             pos = i;
//          }
//          array = copyA;
//       }
//       array[Index] = element;
//       Index++;
//       array[Index] = array[pos];
   }
   
   //a method to get the index of a certain element in the array
   public int IndexOf(T element)
   {
      int index =0;
      for(int i=0;i<trim().length;i++)
      { 
         if(trim()[i].compareTo(element)==0)
            index = i;
      }
      return index;
   }
   
   //a method to get the elements of an array on their respective index
   public T Get(int index)
   {
      return trim()[index];
   }
   
   //a method to return the size of the array with non-null elements
   public int Size()
   {
      int count = 0;
      for(T i:this.array)
      {
         if(i!=null){count++;}
      }
      return count;
   }
   
   //a method to remove elements equal e in the array
   public void remove(T e)
   {
   //it replace the removed elements with that in the following position 
      for (int i = 0; i < this.size; i++){
         if (e == array[i]) {
            for (int j = i; j < this.size - 1; j++){
                array[j] = array[j + 1]; 
            }
            this.size--;
         }
      }
   }
   
   //a method to check if an element exist in the array
   public boolean check(T e)
   {
      boolean bool = false;
      for (int i = 0; i < this.size; i++)
         if (e == array[i])
            bool = true;
      return bool; 
   }
   
   //a method to check if the array is empty
   public boolean empty()
   {
      return Size()==0;
   }
   
   //a hidden method to trim array to the size equal the amount of elements it has
   private T[] trim()
   { 
      int count = 0;
      for(T i:(T[])this.array)
      {
         if(i!=null){count++;}
      }
      copyArray = (T[])Array.newInstance(array.getClass().getComponentType() ,count);
      int index=0;
      for(T i:(T[])this.array)
      {
         if(i!=null){
            copyArray[index++]=i;
         }
      }
      return copyArray;
   
   }
   
   public Boolean equal(Arraylist<T> otherArray)
   {
   /**
      a method to check if the arrays are equal in length and have the 
      same elements even if they are not in order and also sort the array
      in accending order.
   **/
      boolean bool = false;
      int count=0;
      Sort(otherArray);
      Sort(this);
      if(trim().length==(otherArray.trim().length))
      {
         for(int i = 0;i<otherArray.trim().length;i++){
            if((this.array[i].compareTo(otherArray.trim()[i]))==0){
               count++;
            } 
         }
      } 
      if(count==otherArray.trim().length){
         bool = true;
      }  
      return bool;   
   }
    
    //a hidden method to sort an array on accending order
   public void Sort(Arraylist<T> nonSort)
   {
      /**
      this method will use selection sort to the array,
      what it does is that it first creat a locator index (partitions),
      then after it creat a tracker index, to track elements that will be
      less than the locator index elements, and if it finds one then they swap.
      **/ 
      for(int outerloop = 0;outerloop<nonSort.trim().length;outerloop++){
         int locator = outerloop;                 
         for(int innerloop = outerloop+1;innerloop<nonSort.trim().length;innerloop++){
            if((nonSort.trim()[innerloop].compareTo(nonSort.trim()[locator]))<0){
               locator = innerloop;
            }
         }
         //this is how to swap elements in an array
         T temp = nonSort.array[locator];
         nonSort.array[locator] = nonSort.array[outerloop];
         nonSort.array[outerloop] = temp; 
      }
   } 
         
   //a tostring method
   public String toString()
   {  
      if (empty()==true)
      {
         return "[]";
      }
      System.out.print("[");
      for(int i = 0;i<Size()-1;i++)
      {
         System.out.print(trim()[i]+", ");
      }
      return array[Size()-1]+"]";
   }    
}