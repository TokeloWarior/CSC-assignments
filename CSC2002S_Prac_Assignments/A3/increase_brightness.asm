
	#---------------------------file_description------------------------------#
	# The code is done by me and only me, it can both work on MARS and QTSpim #
	# the approach is thoughrly explained in the readme file				  #
	# 																	      #
	# (i used WINDOWS 10 - DELL LATITUDE 3310 to run the code in QTSpim)	  #
	#																	 	  #
	# Author: TOKELO MAKOLOANE												  #
	# STUDENT NUMBER: MKLTOK002												  #
	#																		  #
	# REFERENCE: GEEKSFORGEEKS									  			  #
	#																		  #		
	# PS: ChatGPT doesn't know MIPS (._.) 									  #
	###########################################################################

.data
	
	#------input_and_output_files--------
	
	input_filename:	.asciiz "C:/Users/TOKELO MAKOLOANE/OneDrive - University of Cape Town/2023 CURRICULUM/csc2002s/A3/final_code/input_images/house_64_in_ascii_crlf.ppm"
	
	output_filename:.asciiz "C:/Users/TOKELO MAKOLOANE/OneDrive - University of Cape Town/2023 CURRICULUM/csc2002s/A3/final_code/output_images/output.ppm"
	
	#------------------------------------
	
	#-----dimensions-------
	rows:			.word 4
	cols:			.word 4
	maxValue:		.word 4
	#----------------------
	
	#--brightning_factors---
	redFactor:		.word 10
	greenFactor:	.word 10
	blueFactor:		.word 10
	#-----------------------
	
	#-----buffer_string------
	row_String:		.space 10
	cols_String:	.space 10
	maxValue_str:	.space 10
	string_driver:	.space 10
	pixel_value:	.space 10
	#------------------------
	
	#-----------------------output_and_input_prompts-------------------------
	average_prompt_1:	.asciiz "Average pixel value of the original image:\n"
	average_prompt_2:	.asciiz "\nAverage pixel value of new image:\n"
	#------------------------------------------------------------------------
	
	#------space_holders------
	header:			.space 256
	lines:			.space 256
	dummy:			.space 256
	properties:		.space 256
	#-------------------------
	
	error_message:	.asciiz "Invalid file format\n"
	output_header:	.asciiz "P3\n# Image created by program\n"
	space:			.asciiz " "
	newline:		.asciiz "\n"
	storage:		.space 49152
	
	.text
	
	main:
	
		
		#-------------------------------------read file properties----------------------------------------
		# Open input file
		li $v0, 13       								# syscall code for open file
		la $a0, input_filename
		li $a1, 0        								# flags: 0 for read
		li $a2, 0        								# mode: ignored
		syscall
		move $s0, $v0    								# save file descriptor to $s0
		
		la $s2, header
		li $t2, 0
		# Read file header
		Header: 
			li $v0, 14       							# syscall code for read file
			move $a0, $s0
			la $a1, lines
			li $a2, 1       							# read 10 bytes
			syscall
			
			lb $t4, lines 								# Load the read character into $t4
			
			li $t5, 10  								# ASCII code for CR character(\r), Check if it's a newline character
			beq $t4, $t5, newline_found
			
			li $t5, 13  								# ASCII code for CR character(\r), Check if it's a newline character
			beq $t4, $t5, newline_found
  
			sb $t4, ($s2)								# Add the character to the current line buffer
			  
			addi $t2, $t2, 1							# Increment buffer index
			addi $s2, $s2, 1
			j Header
			
		newline_found:
			sb $zero, header($t2) 						# Null-terminate the current line buffer
		
		# Parse file header
		la $t0, header
		lb $t1, 0($t0)   								# check first character
		li $t2, 'P'
		bne $t1, $t2, invalid_format

		lb $t1, 1($t0)   								# check second character
		li $t2, '3'
		bne $t1, $t2, invalid_format
	
		la $s2, dummy
		li $t2, 0
		continue5:
			j comments
		comments:
			li $v0, 14       							# syscall code for read file
			move $a0, $s0
			la $a1, lines
			li $a2, 1       							# read 10 bytes
			syscall
			
			lb $t4, lines 								# Load the read character into $t4
			
			li $t5, 10  								# ASCII code for newline, Check if it's a newline character
			beq $t4, $t5, comment_line

			li $t5, 13  								# ASCII code for newline, Check if it's a newline character
			beq $t4, $t5, comment_line
	  
			sb $t4, ($s2)
			
			addi $t2, $t2, 1
			addi $s2, $s2, 1
			
			j comments
			
		comment_line:
			sb $zero, dummy($t2)
		
		
		li $t2, 0		 								# Initializing $t2 to 0 (will store the result)
		li $t1, 10       								# Initializing $t1 to 10 (base for decimal numbers)
		Rows:
			li $v0, 14       				 			# syscall code for read file
			move $a0, $s0
			la $a1, lines
			li $a2, 1       							# read 10 bytes
			syscall
			
			lb $t4, lines
		
			li $t5, 10  								# ASCII code for newline, Check if it's a newline character
			beq $t4, $t5, continue
			
			li $t5, 13  								# ASCII code for newline, Check if it's a newline character
			beq $t4, $t5, continue
		
			li $t5, 32  								# ASCII code for space, Check if it's a space character
			beq $t4, $t5, done_rows
			
			sub $t4, $t4, 48							# Convert the character to an integer (assuming ASCII digits)
			
			mul $t2, $t2, $t1 							# Multiply the current result by 10
			
			add $t2, $t2, $t4							# Add the digit to the result
			
			j Rows

		continue:
			j Rows
		
		done_rows:
			sw $t2, rows
		
			li $t2, 0		 # Initializing $t2 to 0 (will store the result)
			li $t1, 10       # Initializing $t1 to 10 (base for decimal numbers)
		
			
		proceed:
			j Cols
			
		Cols:
		
			li $v0, 14       				 			# syscall code for read file
			move $a0, $s0
			la $a1, lines
			li $a2, 1       							# read 10 bytes
			syscall
			
			lb $t4, lines
			
			li $t5, 32  								# ASCII code for space, Check if it's a space character
			beq $t4, $t5, proceed
			
			li $t5, 10  								# ASCII code for newline, Check if it's a newline character
			beq $t4, $t5, done_cols
			
			li $t5, 13  								# ASCII code for space, Check if it's a space character
			beq $t4, $t5, done_cols
			
			sub $t4, $t4, 48							# Convert the character to an integer (assuming ASCII digits)
			
			mul $t2, $t2, $t1 							# Multiply the current result by 10
			
			add $t2, $t2, $t4							# Add the digit to the result
			
			j Cols
			
		done_cols:
			sw $t2, cols

			li $t2, 0		 							# Initializing $t2 to 0 (will store the result)
			li $t1, 10       							# Initializing $t1 to 10 (base for decimal numbers)
		
		continue1:
			j MaxPixel
		
		MaxPixel:
		
			li $v0, 14       				 			# syscall code for read file
			move $a0, $s0
			la $a1, lines
			li $a2, 1       							# read 10 bytes
			syscall
			
			lb $t4, lines
		
			li $t5, 10  								# ASCII code for newline, Check if it's a newline character
			beq $t4, $t5, done_max
			
			li $t5, 13  								# ASCII code for space, Check if it's a space character
			beq $t4, $t5, done_max
			
			sub $t4, $t4, 48							# Convert the character to an integer (assuming ASCII digits)
			
			mul $t2, $t2, $t1 							# Multiply the current result by 10
			
			add $t2, $t2, $t4							# Add the digit to the result
			
			j MaxPixel
			
		done_max:
			sw $t2, maxValue
			li $t2, 0
			li $t1, 0       							# Initializing $t1 to 0 (for future usage)
	#--------------------------------------------------------------------------------------------------------
		
		
	#----------------------------converting_rows_colums_maxvalue_to_strings----------------------------------
		lw $a0, rows									# Load the integer into $a0
		la $a1, row_String								# Load the address of the string buffer into $a1
		
		jal convert_to_string							# Call the int_to_string function
		
		lw $a0, cols
		la $a1, cols_String
		
		jal convert_to_string
		
		lw $a0, maxValue
		la $a1, maxValue_str
		
		jal convert_to_string
		
		# reversing the string_for_rows
		la $a0, row_String
		la $a1, string_driver
		
		jal reverse_string
		
		la $a0 row_String
		la $a1 string_driver
		
		jal replace_string
		
		la $a0 string_driver
		jal clear_string
		
		# reversing the string_for_cols
		la $a0, cols_String
		la $a1, string_driver
		
		jal reverse_string
		
		la $a0 cols_String
		la $a1 string_driver
		
		jal replace_string
		
		la $a0 string_driver
		jal clear_string
		
		# reversing the string_for_rows
		la $a0, maxValue_str
		la $a1, string_driver
		
		jal reverse_string
		
		la $a0 maxValue_str
		la $a1 string_driver
		
		jal replace_string
		
		la $a0 string_driver
		jal clear_string
	#--------------------------------------------------------------------------------------------------------
		
		li $t2, 0
		li $t1, 10       								# Initializing $t1 to 10 (base for decimal numbers)
		li $t6, 0										# sum for the original pixels
		li $t7, 0										# sum for the brightened pixels
		
		la $s1, storage
		
		continue2:
			j RED_pixel_value
			
		RED_pixel_value:
	
			li $v0, 14       				 			# syscall code for read file
			move $a0, $s0
			la $a1, lines
			li $a2, 1       							# read 10 bytes
			syscall
			
			beq $v0, $zero, close_file					# Check for end of line
			
			lb $t4, lines
			
			li $t5, 10  								# ASCII code for newline, Check if it's a newline character
			beq $t4, $t5, store_RED
			
			li $t5, 13  								# ASCII code for newline, Check if it's a newline character
			beq $t4, $t5, store_RED
			
			sub $t4, $t4, 48							# Convert the character to an integer (assuming ASCII digits)
			
			mul $t2, $t2, $t1 							# Multiply the current result by 10
			
			add $t2, $t2, $t4							# Add the digit to the result
			
			j RED_pixel_value
		
		store_RED:
			
			add $t6, $t6, $t2
			lw $t9, redFactor
			add $t2, $t2, $t9
			
			lw $t9, maxValue
			bgt $t2, $t9, maxV
			
			add $t7, $t7, $t2
			
			move $a0, $t2								# Load the integer into $a0
			la $a1,	pixel_value							# Load the address of the string buffer into $a1
			jal convert_to_string						# Call the int_to_string function
			
			la $a0, pixel_value
			la $a1, string_driver
			jal reverse_string
			
			la $a0, string_driver
			jal concat
			
			la $a0 string_driver
			jal clear_string
			
			la $a0 pixel_value
			jal clear_string
			
			li $t2, 0
			li $t1, 10       							# Initializing $t1 to 10 (base for decimal numbers)
			
			j continue3
			
			maxV:
				
				 
				add $t7, $t7, $t9	
				
				move $a0, $t9								# Load the integer into $a0
				la $a1,	pixel_value							# Load the address of the string buffer into $a1
				jal convert_to_string						# Call the int_to_string function
			
				la $a0, pixel_value
				la $a1, string_driver
				jal reverse_string
			
				la $a0, string_driver
				jal concat
				
				la $a0 string_driver
				jal clear_string
				
				la $a0 pixel_value
				jal clear_string      		
				
				
				li $t2, 0
				li $t1, 10       						# Initializing $t1 to 10 (base for decimal numbers)
		
		continue3:
		j Green_pixel_value
			
		Green_pixel_value:
		
			li $v0, 14       				 			# syscall code for read file
			move $a0, $s0
			la $a1, lines
			li $a2, 1       							# read 10 bytes
			syscall
			
			lb $t4, lines
		
			li $t5, 10  								# ASCII code for newline, Check if it's a newline character
			beq $t4, $t5, store_Green
		
			li $t5, 13  								# ASCII code for newline, Check if it's a newline character
			beq $t4, $t5, store_Green
			
			
			sub $t4, $t4, 48							# Convert the character to an integer (assuming ASCII digits)
			
			
			mul $t2, $t2, $t1 							# Multiply the current result by 10
			
			add $t2, $t2, $t4							# Add the digit to the result
			
			j Green_pixel_value
		
		store_Green: 
			
			add $t6, $t6, $t2
			
			lw $t9, greenFactor
			add $t2, $t2, $t9
			
			lw $t9, maxValue
			bgt $t2, $t9, maxV2
			
			add $t7, $t7, $t2
			
			move $a0, $t2								# Load the integer into $a0
			la $a1,	pixel_value							# Load the address of the string buffer into $a1
			jal convert_to_string						# Call the int_to_string function
			
			la $a0, pixel_value
			la $a1, string_driver
			jal reverse_string
			
			la $a0, string_driver
			jal concat
	
			la $a0 string_driver
			jal clear_string
			
			la $a0 pixel_value
			jal clear_string
			
			li $t2, 0
			li $t1, 10       							# Initializing $t1 to 10 (base for decimal numbers)
			
			j continue4
	
			maxV2:
				add $t7, $t7, $t9
				
				move $a0, $t9								# Load the integer into $a0
				la $a1,	pixel_value							# Load the address of the string buffer into $a1
				jal convert_to_string						# Call the int_to_string function
			
				la $a0, pixel_value
				la $a1, string_driver
				jal reverse_string
			
				la $a0, string_driver
				jal concat
				
				la $a0 string_driver
				jal clear_string
				
				la $a0 pixel_value
				jal clear_string      		
			
				
				li $t2, 0
				li $t1, 10       	  
		
		
		continue4:
			j Blue_pixel_value
	
		Blue_pixel_value:
		
			li $v0, 14       				 			# syscall code for read file
			move $a0, $s0
			la $a1, lines
			li $a2, 1       							# read 10 bytes
			syscall
			
			lb $t4, lines
		
			li $t5, 10  								# ASCII code for newline, Check if it's a newline character
			beq $t4, $t5, store_Blue
			
			li $t5, 13  								# ASCII code for newline, Check if it's a newline character
			beq $t4, $t5, store_Blue
			
			sub $t4, $t4, 48							# Convert the character to an integer (assuming ASCII digits)
			
			mul $t2, $t2, $t1 							# Multiply the current result by 10
			
			add $t2, $t2, $t4							# Add the digit to the result
			
			j Blue_pixel_value
		
			store_Blue: 
				add $t6, $t6, $t2
				lw $t9, greenFactor
				add $t2, $t2, $t9
				
				lw $t9, maxValue
				bgt $t2, $t9, maxV3
				
				add $t7, $t7, $t2
				
				move $a0, $t2								# Load the integer into $a0
				la $a1,	pixel_value							# Load the address of the string buffer into $a1
				jal convert_to_string						# Call the int_to_string function
				
				la $a0, pixel_value
				la $a1, string_driver
				jal reverse_string

				la $a0, string_driver
				jal concat
				
				la $a0 string_driver
				jal clear_string
				
				la $a0 pixel_value
				jal clear_string
				
				li $t2, 0
				li $t1, 10       							# Initializing $t1 to 10 (base for decimal numbers)
				
				j RED_pixel_value
				
			maxV3:
				add $t7, $t7, $t9
			
				move $a0, $t9								# Load the integer into $a0
				la $a1,	pixel_value							# Load the address of the string buffer into $a1
				jal convert_to_string						# Call the int_to_string function
			
				la $a0, pixel_value
				la $a1, string_driver
				jal reverse_string
			
				la $a0, string_driver
				jal concat
				
				la $a0 string_driver
				jal clear_string
				
				la $a0 pixel_value
				jal clear_string      		
				
				
				li $t2, 0
				li $t1, 10       	
				
		j RED_pixel_value
	
	
	#-----------------------------------wirte_to_file--------------------------------------------------------
		write:
			
			# Open the output file
			li $v0, 13       							# syscall code for open file
			la $a0, output_filename
			li $a1, 1        							# flags: 1 for write
			li $a2, 0        							# mode: ignored
			syscall
			move $s0, $v0    							# save file descriptor to $s0

			# Write image header
			li $v0, 15
			move $a0, $s0
			la $a1, output_header
			li $a2, 30
			syscall
			
			# Write image dimensions
			
			la $a0, row_String
			jal length_of_String2
			
			move $t0, $v0
			
			li $v0, 15
			move $a0, $s0
			la $a1, row_String
			move $a2, $t0
			syscall
			
			
			li $v0, 15
			move $a0, $s0
			la $a1, space
			li $a2, 1
			syscall
			
			la $a0, cols_String
			jal length_of_String2
			
			move $t0, $v0
		
			li $v0, 15
			move $a0, $s0
			la $a1, cols_String
			move $a2, $t0
			syscall
			
			li $v0, 15
			move $a0, $s0
			la $a1, newline
			li $a2, 1
			syscall
			
			
			li $v0, 15
			la $a1, maxValue_str
			li $a2, 3
			syscall
			
			
			li $v0, 15
			move $a0, $s0
			la $a1, newline
			li $a2, 1
			syscall
			
			# write the pixel values to file
			
			la $a0, storage
			jal length_of_String
			
			move $t0, $v0
			
			li $v0, 15
			move $a0, $s0
			la $a1, storage
			move $a2, $t0
			syscall
			
			j Exit_code
	#-------------------------------------------------------------------------------------------------------
	
	close_file:
		
		li $v0, 16          						# Load the system call code for close file
		move $a0, $s0      						 	# File descriptor
		syscall
		
		sb $zero, ($s1)								# null terminate
	
		j write
	
	Exit_code:
		
		li $v0, 16          						# Load the system call code for close file
		move $a0, $s0      						 	# File descriptor
		syscall
		
		lw $t8, rows
		lw $t9, cols
		
		mul $t0, $t8, $t9
		mul $t0, $t0, 3
		mul $t0, $t0, 255
		
		li $v0, 4
		la $a0, average_prompt_1   					# Load the average into $f12
		syscall
		
		mtc1 $t6, $f0        						# Move the integer to an FPU register
		cvt.s.w $f0, $f0 
		
		mtc1 $t0, $f1        						# Move the integer to an FPU register
		cvt.s.w $f1, $f1 
		
		div.s $f0, $f0, $f1 
		
		
		li $v0, 2
		mov.s $f12, $f0   							# Load the average into $f12
		syscall
		
		li $v0, 4
		la $a0, average_prompt_2   					# Load the average into $f12
		syscall
		
		mtc1 $t7, $f0        						# Move the integer to an FPU register
		cvt.s.w $f0, $f0 
		
		mtc1 $t0, $f1        						# Move the integer to an FPU register
		cvt.s.w $f1, $f1 
		
		div.s $f0, $f0, $f1 
		
		li $v0, 2
		mov.s $f12, $f0   							# Load the average into $f12
		syscall
		
		# Exit program
		li $v0, 10       							# syscall code for program exit
		syscall
		
	#-----------------------------function_to_convert_int_to_str----------------------------------------
	
		convert_to_string:
			
			# $a0 - Integer to convert
			# $a1 - Address of the string buffer
			
			addi $a2, $zero, 10  						# Set the base (decimal)
				
			convert_loop:
				divu $a0, $a0, $a2  					# Divide $a0 by 10, result in $a0, remainder in $t0
				mfhi $t0            					# Move the remainder to $t0
				
				addi $t0, $t0, 48   					# Convert remainder to ASCII ('0' + remainder)
				
				sb $t0, ($a1)							# Store the ASCII character in the string buffer
				addi $a1, $a1, 1   						# Move to the next character in the string buffer

				bnez $a0, convert_loop  				# Repeat the loop if $a0 is not zero

				sb $zero, ($a1)							# Null-terminate the string
				
			jr $ra
			
	#----------------------------------------------------------------------------------------------------	
	
	#-----------------------------function_to_calculate_length_of_string---------------------------------
			length_of_String2:
			
				li $v0, 0							# couter for the size of the string
				
				lb $t1, ($a0)						# Load byte/char of string1
				
				length2: beqz $t1, complete2
					
					addi $a0, $a0, 1 
					lb $t1, ($a0)
					
					addi $v0, $v0, 1
					j length2
					
			complete2:
				jr $ra
	#----------------------------------------------------------------------------------------------------
	
	#-----------------------------function_to_calculate_length_of_string---------------------------------
			length_of_String:
			
				li $v0, -1							# couter for the size of the string
				
				lb $t1, ($a0)						# Load byte/char of string1
				
				length1: beqz $t1, complete
					
					addi $a0, $a0, 1 
					lb $t1, ($a0)
					
					addi $v0, $v0, 1
					j length1
					
			complete:
				jr $ra
	#----------------------------------------------------------------------------------------------------
	
	#-----------------------------function_to_reverse_string---------------------------------------------
	
		reverse_string:
		
			# $a0 - string to reverse
			# $a1 - target_string
			
			li $t0, -1							# couter for the size of the string
			#li $t3, -1
			
			lb $t1, ($a0)						# Load byte/char of string1
			
			length:	beqz $t1, reverse1
				
				addi $a0, $a0, 1 
				lb $t1, ($a0)
				
				addi $t0, $t0, 1
				addi $t3, $t3, 1
				j length
			
			reverse1:
			
				addi $a0, $a0, -1				# Move increment of string to the last char of string
				
				j reverse
			
			reverse: bltz $t0, done				# Stop the loop after checking the string
				lb $t2, ($a0)					# Load char of string1 in reverse
				
				sb $t2, ($a1)					# Store them in target_string
				
				
				addi $a1, $a1, 1				# Increment target_string pointer
				
				addi $t0, $t0, -1				# Decrement the counter
				addi $a0, $a0, -1				# Decrement the target_string pointer
				
				j reverse
		done:
			sb $zero, ($a1)	
			jr $ra
			
	#----------------------------------------------------------------------------------------------------
	
	#---------------------------------function_to_clear_string-------------------------------------------
	
		clear_string:
		
			# $a0 - Address of the string to clear

			la $t0, ($a0)      						# Load the address of the string
			clear_loop:
				lb $t1 ,($t0)
				sb $zero, ($t0)    					# Store a null character in the string
				addi $t0, $t0, 1   					# Move to the next character
				bnez $t1, clear_loop 				# Repeat until a null character is stored

		jr $ra
		
	#----------------------------------------------------------------------------------------------------
	
	#---------------------------------function_to_clear_string_final-------------------------------------------
	
		clear_string2:
		
			# $a1 - Address of the string to clear

			la $t0, ($a1)      						# Load the address of the string
			clear_loop1:
				lb $t1 ,($t0)
				sb $zero, ($t0)    					# Store a null character in the string
				addi $t0, $t0, 1   					# Move to the next character
				bnez $t1, clear_loop1 				# Repeat until a null character is stored

		jr $ra
		
	#----------------------------------------------------------------------------------------------------
	
	#--------------------------------------concat_to_string----------------------------------------------
		
		concat:
			
			# $a0 - Address of pixel value
			# $s1 - Address of storage buffer
			
			concat_str:
				lb $t2, ($a0)					# Load char of string1 in reverse
				beqz $t2 , finished
				sb $t2, ($s1)					# Store them in target_string
				
					
				addi $s1, $s1, 1				# Increment target_string pointer
					
				addi $a0, $a0, 1
			j concat_str
			
		finished:	
			la $t2, newline
			lb $t8, ($t2)
			sb $t8, ($s1)
			addi $s1, $s1, 1
			
			jr $ra
	#----------------------------------------------------------------------------------------------------
	
	#---------------------------------function_t0_replace_string-----------------------------------------
	
		replace_string:
		
			# $a0 - Address of the old string
			# $a1 - Address of the new string

			
			la $t0, ($a0)   						# Address of the old string
			la $t1, ($a1)   						# Address of the new string

			replace_loop:
				lb $t2, ($t1)   					# Load a character from the new string
				beqz $t2, done_replace  					# If null terminator, we are done
				sb $t2, ($t0)   					# Copy the character to the old string
				addi $t0, $t0, 1  					# Move to the next character in the old string
				addi $t1, $t1, 1  					# Move to the next character in the new string
				j replace_loop

		done_replace:
			sb $zero, ($t0)							# Null-terminate the old string
			jr $ra
			
	#----------------------------------------------------------------------------------------------------
	
		invalid_format:
			# Handle invalid file format
			li $v0, 4
			la $a0, error_message
			syscall

			li $v0, 16          						# Load the system call code for close file
			move $a0, $s0      						 	# File descriptor
			syscall
			
			# Exit program
			li $v0, 10       							# syscall code for program exit
			syscall
