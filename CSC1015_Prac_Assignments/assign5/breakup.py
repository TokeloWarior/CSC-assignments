#Name: Tokelo Makoloane
#Student number: MKLTOK002
#Date: 17 April 2021

#A program that accepts a sentence as input and thats outputs it as a comma-separated list of lowercase words.

#A user must enter a desired sentence

sentence = input("Enter a sentence:\n")


lower = sentence.lower()
new_spaces = (", ")
print("The word list:",end=' ')
print(lower.replace(" ",new_spaces),end='.')   #REPLACING SPACES WITH A COMMA AND A SPACE
#
#
#Alternative method

#This will give a loop of all splitt(ed) words in the inputed sentence then at the end of each word it will end with a comma and then joined again to form a new sentence

#print("The word list:",end=' ')
#split_sentence = sentence.split()
#joined_sentence = ", ".join(split_sentence)
#print(joined_sentence.lower(),end=".")


    
    
