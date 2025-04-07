package barScheduling;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/*
 Barman Thread class.
 */

public class Barman extends Thread {

   
   private CountDownLatch startSignal;
   private BlockingQueue<DrinkOrder> orderQueue;
   private PriorityBlockingQueue<DrinkOrder> PriorityorderQueue;
   private int schedAlg;
   
   
   
   Barman(  CountDownLatch startSignal,int schedAlg) {
   	this.schedAlg = schedAlg;
   	this.startSignal=startSignal;
   	if (schedAlg==0){
   		this.orderQueue = new LinkedBlockingQueue<>();
   	}
   	else{ 
   		this.PriorityorderQueue = new PriorityBlockingQueue<>(10,new DrinkOrderComparator());
   	}
       
   }
   
   
   public void placeDrinkOrder(DrinkOrder order) throws InterruptedException {
       
   	if (schedAlg==0){
   		orderQueue.put(order);
   	}
   	else{ 
   		PriorityorderQueue.put(order);
   	}
    }
   
   
   public void run() {
   	try {
   		DrinkOrder nextOrder;
   		
   		startSignal.countDown(); //barman ready
   		startSignal.await(); //check latch - don't start until told to do so
   		if(schedAlg == 0){
   			while(true) {
   				nextOrder=orderQueue.take();
   				System.out.println("---Barman preparing order for patron "+ nextOrder.toString());
   				sleep(nextOrder.getExecutionTime()); //processing order
   				System.out.println("---Barman has made order for patron "+ nextOrder.toString());
   				nextOrder.orderDone();
   			}
   		}
   		else{
   			while (true) {
   				//System.out.println("Current order queue: " + PriorityorderQueue);
   				// Get the next order from the queue
   			 	nextOrder = PriorityorderQueue.take();
   				
   				System.out.println("---Barman preparing order for patron "+ nextOrder.toString());
   				// Process the order
   				//processOrder(nextOrder);
   				sleep(nextOrder.getExecutionTime()); //processing order
   				System.out.println("---Barman has made order for patron "+ nextOrder.toString());
   				nextOrder.orderDone();
   			}
   		}
   
   	} catch (InterruptedException e1) {
   		System.out.println("---Barman is packing up ");
   	}
   }
   
   // private void processOrder(DrinkOrder order) {
   
   // 	//System.out.println("Shortest job First Scheduling");
   // 	// Process the order
   // 	try {
   // 		// Simulate processing time
   		
   // 		//order.orderDone();
   // 		// Check if the order is completed
   // 		if (order.isOrderCompleted()) {
   // 			sleep(order.getExecutionTime());
   // 			System.out.println("---Barman has made order for patron " + order.toString());
   // 			order.orderDone();
   // 			// Place the order back in the queue if not completed
   // 			//System.out.println("Order back in the queue");
   // 			//order.updateRemainingExecutionTime();
   // 			//PriorityorderQueue.put(order);
   			
   // 		} else {
   // 			//System.out.println("---Barman has made order for patron " + order.toString());
   // 			//order.orderDone(); // Notify other threads that the order is completed
   // 			System.out.println("Order back in the queue");
   // 			//order.updateRemainingExecutionTime();
   // 			PriorityorderQueue.put(order);
   // 			//order.OrderInQueue();
   // 		}
   // 	} catch (InterruptedException e1) {
   // 		// Handle interruption
   // 		System.out.println("---Barman is packing up ");
   // 	}
   // }
   
   //inner class to use the comparator for the Priority queue
   // Custom comparator for DrinkOrder based on the number of drinks
   private static class DrinkOrderComparator implements Comparator<DrinkOrder> {
   	@Override
   	public int compare(DrinkOrder order1, DrinkOrder order2) {
   		// Compare orders based on the number of drinks
   		//System.out.println("inside the compare method");
   		return Integer.compare(order1.getExecutionTime(), order2.getExecutionTime());
   	}
   }
}


