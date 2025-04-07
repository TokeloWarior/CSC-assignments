public class Linkedlist1
{
   private class Node
   {
       private String item;
       private Node link;
      
      public Node(String item)
      {
         this.item = item;
         this.link = null;
      }
   }
   
   private Node head = null;
   private Node tail = null;
   
   public void addNode(String item  )
   {
      Node newNode = new Node(item); 
      if(head==null)
      {
         //If list is empty, both head and tail will point to new node    
         head = newNode;    
         tail = newNode;    
      }else{
         //newNode will be added after tail such that tail's link will point to newNode    
         tail.link = newNode;    
         //newNode will become new tail of the list    
         tail = newNode;    
      }
   }
   
   //display() will display all the nodes present in the list    
   public void display() {    
      //Node current will point to head    
      Node current = head;    
            
      if(head == null) {    
         System.out.println("List is empty");    
         return;    
      }    
      System.out.println("Nodes of singly linked list: "); 
      System.out.print("[");   
      while(current != null) {    
         //Prints each node by incrementing pointer  
           
         System.out.print(current.item + " ");    
         current = current.link;    
      }    
      System.out.println("]");    
    } 
    
    public static void main(String[] args)
    {
      Linkedlist1 list = new Linkedlist1();
      
      list.addNode("Tokelo");
      list.addNode("Kill");
      list.addNode("india");
      list.addNode("mambo");
      
      list.display();
    }   
}