# Club Simulation
 Utilize synchronization mechanisms to ensure that the simulation adheres to specified synchronization constraints and maintains safety and liveness.

## Contents

- [Usage](#usage)


## Usage

1. Compile the code using 'javac':
---------------------------------
   ```shell
   > javac clubSimulation/*.java

2. Compile the code using 'make':
---------------------------------
   ```shell>>'directory of the make in my submitted file, the MKLTOK002_CSC2002S_PCP2 folder'
   > make

3.Run the application with command-line arguments:
--------------------------------------------------
   ```shell>>'directory of the file i have submitted, the run must be made outside the src and outside bin, but inside MKLTOK002_CSC2002S_PCP1 folder'
      (It might only run on linux only with this command, i don't use Microsoft windows)
   ---------------------------------------------------------------------------------------------------------------------------------------------------
   >>> java -cp bin/ clubSimulation.ClubSimulation <noClubgoers> <gridX> <gridY> <max> 
   ---------------------------------------------------------------------------------------------------------------------------------------------------
   ---replace <...> with your parameters---

   or...
   
   ```shell>>'directory of the makefile, in the MKLTOK002_CSC2002S_PCP2 folder'
   --------------------------------------------------------------------------------
   >>> make run ARGS="<noClubgoers> <gridX> <gridY> <max>"
   --------------------------------------------------------------------------------
   ---replace <...> with your parameters---
   
For any issues or questions, please contact TOKELO MAKOLOANE at MKLTOK002@myuct.ac.za
