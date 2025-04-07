//M. M. Kuttel 2024 mkuttel@gmail.com

package barScheduling;
// the main class, starts all threads


import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SchedulingSimulation {
	static int noPatrons=100; //number of customers - default value if not provided on command line
	static int sched=0; //which scheduling algorithm, 0= FCFS
			
	static CountDownLatch startSignal;
	public static ArrayList<Integer> turnaround_time = new ArrayList<>();
	
	static Patron[] patrons; // array for customer threads
	static Barman Andre;
	static FileWriter writer;
	//static FileWriter writer_2;

	public  void writeToFile(String data) throws IOException {
	    synchronized (writer) {
	    	writer.write(data);
	    }
	}

	public static void turnaround_time(int time){
		synchronized(turnaround_time){
			turnaround_time.add(time);
		}
	}

	private static int sumArray(ArrayList<Integer> arrayList){
		int sum = 0;
        for (int num : arrayList) {
            sum += num;
        }
		return sum;
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		
		

		//deal with command line arguments if provided
		if (args.length==1) {
			noPatrons=Integer.parseInt(args[0]);  //total people to enter room
		} else if (args.length==2) {
			noPatrons=Integer.parseInt(args[0]);  //total people to enter room
			sched=Integer.parseInt(args[1]); 
		}
		
		writer = new FileWriter("turnaround_time_"+Integer.toString(sched)+".txt", false); //dont forget to change back the noPatrons to sched
		writer.write("---------turnaround_time_for_each_Patron---------\n");
		Patron.fileW=writer;

		startSignal= new CountDownLatch(noPatrons+2);//Barman and patrons and main method must be raeady
		
		//create barman
        Andre= new Barman(startSignal,sched); 
     	Andre.start();
  
		// Track metrics
		long totalTurnaroundTime = 0;
		long totalWaitingTime = 0;
		long totalResponseTime = 0;
		int totalPatronsServed = 0;
		long arrivalTime = 0;
		long processingStartTime = 0;
		// Record start time of simulation
		long simulationStartTime = System.nanoTime();	

	    //create all the patrons, who all need access to Andre
		patrons = new Patron[noPatrons];
		for (int i=0;i<noPatrons;i++) {

			arrivalTime = System.nanoTime() - simulationStartTime; //Track arrival time

			patrons[i] = new Patron(i,startSignal,Andre);

			processingStartTime = System.nanoTime(); // Track start time of processing

			patrons[i].start();
			
			
		}
		
		System.out.println("------Andre the Barman Scheduling Simulation------");
		System.out.println("-------------- with "+ Integer.toString(noPatrons)+" patrons---------------");

      	startSignal.countDown(); //main method ready
      	

      	//wait till all patrons done, otherwise race condition on the file closing!
      	for (int i=0;i<noPatrons;i++) {
			patrons[i].join();

			// Record completion time
			long completionTime = System.nanoTime() - simulationStartTime;

			// Calculate metrics
			//totalTurnaroundTime += completionTime - arrivalTime;
			totalWaitingTime += completionTime - arrivalTime;
			//totalResponseTime += processingStartTime - arrivalTime; // Assuming first drink served immediately
			totalPatronsServed++;
		}

		// Calculate elapsed time
		long simulationEndTime = System.nanoTime();
		long elapsedTimeInNanos = simulationEndTime - simulationStartTime;
		double elapsedTimeInSeconds = elapsedTimeInNanos / 1_000_000_000.0; // Convert nanoseconds to seconds


		// Calculate throughput
		double throughput = totalPatronsServed / (double)elapsedTimeInSeconds;

		// Calculate average turnaround time, waiting time, and response time
		//double averageTurnaroundTime = totalTurnaroundTime / (double) totalPatronsServed;
		//System.out.println(turnaround_time);
		double Arived = TimeUnit.NANOSECONDS.toMillis(arrivalTime);
		double averageTurnaroundTime = (sumArray(turnaround_time)/(double)totalPatronsServed)/1_000.0;
		double averageWaitingTime = (totalWaitingTime / (double) totalPatronsServed)/1_000_000_000.0;
		double averageResponseTime = ((sumArray(turnaround_time)-Arived) / (double) totalPatronsServed)/1_000.0;

		DecimalFormat df = new DecimalFormat("#.###");
		//FileWriter writer_2 = new FileWriter("Metrices_for_Algorithm_"+Integer.toString(sched)+".txt", false);
		// Print metrics
		writer.write("\n---------Scheduling Simulation metrices---------\n");
		writer.write("Throughput: "+df.format(throughput)+" patrons per second\n");
		writer.write("Average Turnaround Time: "+df.format(averageTurnaroundTime)+" seconds\n");
		writer.write("Average Waiting Time: "+df.format(averageWaitingTime)+" seconds\n");
		writer.write("Average Response Time: "+df.format(averageResponseTime)+" seconds");
		// writer.write("\n---------Scheduling Simulation metrices---------\n");
		// writer.write(df.format(throughput)+"\n");
		// writer.write(df.format(averageTurnaroundTime)+"\n");
		// writer.write(df.format(averageWaitingTime)+"\n");
		// writer.write(df.format(averageResponseTime));

    	System.out.println("------Waiting for Andre------");
    	Andre.interrupt();   //tell Andre to close up
    	Andre.join(); //wait till he has
      	writer.close(); //all done, can close file
      	System.out.println("------Bar closed------");
	}

}