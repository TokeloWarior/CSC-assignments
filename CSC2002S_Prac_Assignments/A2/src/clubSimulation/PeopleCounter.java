package clubSimulation;
import java.util.concurrent.atomic.*;
import clubSimulation.ClubSimulation;

public class PeopleCounter {
	private AtomicInteger peopleOutSide; //counter for people arrived but not yet in the building
	private AtomicInteger peopleInside; //counter for patrons inside club
	private AtomicInteger peopleLeft; //counter for patrons who have left the club
	private AtomicInteger maxPeople; //maximum patrons allowed in the club at one time
	private final int maxCapacity; // maximum capacity of the club

	PeopleCounter(int max) {
		peopleOutSide= new AtomicInteger(0);
		peopleInside=new AtomicInteger(0);
		peopleLeft=new AtomicInteger(0);
		maxPeople=new AtomicInteger(max);
      maxCapacity = ClubSimulation.noClubgoers;
	}
		
	public int getWaiting() {
		return peopleOutSide.get();
	}

	public int getInside() {
		return peopleInside.get();
	}
	
	public int getTotal() {
		return (peopleOutSide.get()+peopleInside.get()+peopleLeft.get());
	}

	public int getLeft() {
		return peopleLeft.get();
	}
	
	public int getMax() {
		return maxPeople.get();
	}
	
//---------------------------------------
	//someone arrived outside
	// public void personArrived() {
// 		peopleOutSide.getAndIncrement();
// 	}
//---------------------------------------

   //someone arrived outside
   /**
    *this id to make sure people who have arrived, left the club 
     and those who are inside the club will accumulate to the number of club goers
    */
    public void personArrived() {
        synchronized (peopleOutSide) {
            if (peopleOutSide.get() < (maxCapacity - (peopleInside.get()+peopleLeft.get()))) {
                peopleOutSide.getAndIncrement();
            }
        }
    }
   

   /**
    *this is create a different thread that will work with people waiting outside
      it increments the number of people waiting respective to what thredas of patrons
      are active to enter club.
    */
    public void startWaitingIncrement() {
        Thread waitingIncrementThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // thread should have 1 second sleep to initiate mutual exclusion
                    personArrived(); // Increment the counter
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        waitingIncrementThread.start();
    }
    
//---------------------------------------------- 
	//someone got inside
	// synchronized public void personEntered() {
// 		peopleOutSide.getAndDecrement();
// 		peopleInside.getAndIncrement();
// 	}
//----------------------------------------------

   //someone got inside
   /**
    *here it works with @startWaitingIncrement() to make sure that people outside counter
     won't go to zero when we keep decrementing the number of people inside the club
     -it is synchronized to avoid @startWaitingIncrement() thread and the main threads from
      accessing the @personEntered() method at the same time.
    */
   synchronized public void personEntered() {
        int currentWaiting = peopleOutSide.getAndDecrement();
        if (currentWaiting > 0) {
            peopleInside.getAndIncrement();
        }
    }
                     

	//someone left
	synchronized public void personLeft() {
		peopleInside.getAndDecrement();
		peopleLeft.getAndIncrement();
		
	}
	//too many people inside
	synchronized public boolean overCapacity() {
		if(peopleInside.get()>=maxPeople.get())
			return true;
		return false;
	}
	
	//not used
	synchronized public void resetScore() {
		peopleInside.set(0);
		peopleOutSide.set(0);
		peopleLeft.set(0);
	}
}
