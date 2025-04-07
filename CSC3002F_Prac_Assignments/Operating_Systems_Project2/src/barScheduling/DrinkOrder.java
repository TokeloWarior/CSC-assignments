package barScheduling;

import java.util.Hashtable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


public class DrinkOrder  {

    //DO NOT change the code below
    public enum Drink { 
        Beer("Beer", 10),
        Cider("Cider", 10),
        GinAndTonic("Gin and Tonic", 30),
        Martini("Martini", 50),
        Cosmopolitan("Cosmopolitan", 80),
        BloodyMary("Bloody Mary", 90),
        Margarita("Margarita", 100),
        Mojito("Mojito", 120),
        PinaColada("Pina Colada", 200),
        LongIslandIcedTea("Long Island Iced Tea", 300),
    	B52("B52", 500);
    	
    	
        private final String name;
        private final int preparationTime; // in minutes (changed to make it updated, change back to final when done testing)
        //private int time;
        

        Drink(String name, int preparationTime) {
            this.name = name;
            this.preparationTime = preparationTime;
        }

        public String getName() {
            return name;
        }

        public int getPreparationTime() {
            return preparationTime;
        }

        // //new method created
        // public void resetPreparationTime(){
        //     preparationTime = 0;
        // }
        // public void setTime(int time){
        //     preparationTime = time;
        // }

        @Override
        public String toString() {
            return name;
        }
    }
    
    private final Drink drink;
    private static final Random random = new Random();
    private int orderer;
    private AtomicBoolean orderComplete;

    //constructor
    public DrinkOrder(int patron) {
    	drink=getRandomDrink();
    	orderComplete = new AtomicBoolean(false);
    	orderer=patron;
    }
    
    public static Drink getRandomDrink() {
        Drink[] drinks = Drink.values();  // Get all enum constants
        int randomIndex = random.nextInt(drinks.length);  // Generate a random index
        return drinks[randomIndex];  // Return the randomly selected drink
    }
    

    public int getExecutionTime() {
        return drink.getPreparationTime();
    }
    
    //barman signals when order is done
    public synchronized void orderDone() {
    	orderComplete.set(true);
        this.notifyAll();
    }

    
    //new created method
    public synchronized boolean isOrderCompleted(){
        //this.notifyAll();
        return orderComplete.get();
    }
    public synchronized void OrderInQueue(){
        orderComplete.set(true);
    }
    // Method to update the remaining execution time of the order
    // public synchronized void updateRemainingExecutionTime() {
    //     // Decrement the remaining execution time by one time unit
    //     int num = drink.getPreparationTime();
    //     num--;
    //     drink.setTime(num);
    //     // Ensure that the remaining execution time is non-negative
    //     if (num < 0) {
    //         drink.resetPreparationTime();
    //     }
    // }


    //patrons wait for their orders
    public synchronized void waitForOrder() throws InterruptedException {
    	while(!orderComplete.get()) {
    		this.wait();
    	}
    }
    
    @Override
    public String toString() {
        return Integer.toString(orderer) +": "+ drink.getName();
    }
}