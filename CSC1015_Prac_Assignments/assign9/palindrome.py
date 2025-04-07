#TOKELO MAKOLOANE
#28-MAY-2021
# A program that uses a recursive function to calculate whether or not a string is a palindrome (reads the same if reversed).
 
def palindrome(string):
    # Base case
    if len(string) < 2:    # this accommodate  also for 2 or less lettered words as a string containing one letter is a palindrome by default     
        return True
    
    # A recursive case
    if string[0] == string[len(string)-1]:         # this is to check whether the last letter and the first letter are equal if so it...
        return palindrome(string[1:len(string)-1]) # returns the string with the first and last letters removed to check recursively for palindrome
    else:
        return False        # otherwise if everything in the recursive case and the base case is not met it will return False


string = (input("Enter a string:\n"))

if palindrome(string) == True:      # this condition is to make sure that for all the True conditions of the function it should print the following
    print("Palindrome!")
else:
    print("Not a palindrome!")
    