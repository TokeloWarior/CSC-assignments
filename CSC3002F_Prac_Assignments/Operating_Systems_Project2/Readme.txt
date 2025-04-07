Bar Scheduling Simulation
------------------------------------------------------------------------------------------------------------------------------------------------
This Java program simulates a busy bar where patrons arrive at random times throughout the evening and place orders for drinks. The orders are filled by Andre the Barman, who operates using different scheduling algorithms.
------------------------------------------------------------------------------------------------------------------------------------------------


To compile and run the program using the provided Makefile, follow these steps:
...............................................................................
1. Ensure you have Java Development Kit (JDK) installed on your system.

2. Open a command prompt or terminal.

3. Navigate to the directory containing the Java source files and the Makefile.

4. Compile the Java classes using the following command:
   >make

This will compile the Java source files and generate the corresponding class files in the 'bin' directory.
................................................................................

-------------------------------------------------------------------------
5. Run the simulation with default arguments using the following command:
   >make run

This will run the simulation with default parameters, such as 100 patrons and the First Come First Served (FCFS) scheduling algorithm.

Alternatively, you can run the simulation with custom arguments using the following command:
   >make run ARGS="100 1"

Replace "100 1" with the desired number of patrons and scheduling algorithm type (0 for FCFS, 1 for Shortest Job First).
-------------------------------------------------------------------------

6. To remove compiled files, run:
   >make clean

For any questions or issues, please contact Tokelo Makoloane at MKLTOK002@myuct.ac.za.
