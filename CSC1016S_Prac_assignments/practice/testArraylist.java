//test Arraylist class

//main class
public class testArraylist
{
   //main method
   public static void main(String[] args)
   {
      Arraylist<Integer> Array = new Arraylist<Integer>(Integer.class,1000);
      //Arraylist<Integer> A = new Arraylist<Integer>(Integer.class,100);
      Integer[] arr = {1,23,2,1,2,11,90,5,3,1,1,3,0,120,34,56,65,21,32,23,80,11,19,7};
      //System.out.println(Array.Get(1));
      for(int i=0;i<arr.length;i++){   
         Array.Add(arr[i]);
      }
      System.out.println(Array.Size());
      //Array.addAt("wolaWola",10);
      //Array.Sort(Array);
      //System.out.println(Array.IndexOf(arr[3]));
      Array.Sort(Array);
      System.out.println(Array);
      //System.out.println(A);
   }
}