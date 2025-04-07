# TOKELO MAKOLOANE
# 05-JUNE-2021
# A program that searches a file for anagrams of a given word, printing the results in alphabetical order.

print("***** Anagram Finder *****")
array1=[]
array2=[]
try:
    with open('EnglishWords.txt','r') as file: 
        name = input('Enter a word:\n')      
        while not 'START' in next(file):                # This will read all the words after the word START
            pass
        for lines in file:
            lines = lines.strip('\n')                   # This will delete all the spaces between the lines
            lines = lines.lower()
            array1.append(lines)
        for i in range(len(array1)):
            if(sorted(name)==sorted(array1[i])):        # This compares for anagrams for each position of the array
                if(name!=array1[i]):
                    array2.append(array1[i])
        if(len(array2)!=0):
            print(sorted(array2))
        
        else:
            print("Sorry, anagrams of '"+name+"' could not be found.")  
except IOError:
    print("Sorry, could not find file 'EnglishWords.txt'.")

    
    
