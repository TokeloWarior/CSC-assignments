INTRODUCTION:

This assignment is aimed to understand the deep usage of registers and manipulation of the data they store,
In this case the data they store will be the PPM file data to manipulate the type of image we want to have.

CODE DESCRIPTION:

	Increase_brightness: 

		The code adds every pixel value of the RGB by 10, but can be changed to whatever value.
		How does it work, what it does it reads in the file specified at the variable "input_filename"
		at the top of the code, reads the file character by character and store the relevant file desciptions 
		in the placeholders. Then after it reads the max value of the pixels, it starts reading te pixel values
		character by character and convert every character string into an integer, the moment it finish reading 
		the whole number, the pixel is stored in a temporary register to add 10 (or whatever value specified) to
		it, after adding 10 (or whatever value specified) it takes that integer value and call the convert to 
		string function, after converting to string it stores the string into a "storage" string that i specified 
		with size (64 x 64 x 3 x 4), the 4 is because the assumption the the length of string to be appended has 
		length 4, 3 is for the RGB pixels respectively and the the (64 x 64) is for the number of each RGB pixel
		value. Now when the end of the file is reached, the file is closed and the "output_filename" is used to 
		open the file that will store the final contents of the new file. And the whole "storage" string is outputted.
	
	grey_scale:

		grey scale execute similarily as the increase_brightness code, the only difference is at the section of adding 10s, 
		because on that section of the code the grey scale code adds all three of the RGB values and integer divide the sum
		and the qouetient is stored in the same "storage" string. but now the storage string will have size (64 x 64 x 4) no 
		3 this time because we only storing the average of the 3 RGB pixel values.

How to run the codes:	(Please run my code on Windows, 10-11 prefared. with mac or linux it might not read the images properly)

	(i will only include both [CR] and [LF] images only since on windows the CR only seems to have bad end of file characters)

	firstly you have to add your own absolute file paths to run the code. I have commented every section of the code 
	based on the action it perform, so i will state them out to where you have to add your own absolute paths.
	
	1. On the section of "input_and_output_files" there are two variables, "input_filename" and "output_filename". 
	   I have have created two folders, "input_images" and "output_images". The input images folder that is where i 
	   will have the input images and the output images folder will be where we store the output images created.

	   remember to include those folders in your absolute path, 
	   eg. input_filename: .asciiz ".../input_images/input1.ppm 
	       output_filename: .asciiz ".../output_images/output1.ppm 
		
	   where the (...) represents your path from user to the image folders.
	
	2. Since QTSpim cannot create it's own PPM files or whatever file in that matter. i will include empty output.ppm files
	   in the output image folder so that you can use them to test the code, but if they run out, please create more .ppm files
	   and don't forget to include them on the paths when running the code.



NB: you can look at the "watch_me" video if i am not clear on this document. (if any problems please contact me, MKLTOK002@myuct.ac.za)