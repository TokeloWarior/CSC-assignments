# TOKELO MAKOLOANE
# 08 JUNE 2021
# that asks the user to enter a word length and a filename.The program will search the EnglishWords.txt file for sets of words that are the same length and are anagrams of each other and will write the results to a file with the given filename

length = eval(input("Enter word length:\n"))
print("Searching...")
filename = input("Enter file name:\n")
print("Writing results...\n")
array1=[]
array2=[]

with open(filename,'w',encoding='utf-8') as file2:
    file = open('EnglishWords.txt','r')
    while not 'START' in next(file):             # This will read all the words after the word START
        pass
    for lines in file:
        lines = lines.strip('\n')
        lines = lines.lower()
        if length == len(lines):                 # declaring for words that are the same length as input value
            array1.append(lines)
    for word in sorted(array1):
        leng = (array1).index(word)              # this is to find the position of word iteratively in the array
        for i in range(leng+1,len(array1)):      # this is to compare each word iteratively incremented by 1(meaning no need to repeat comparison if complete)
            if sorted(word) == sorted(array1[i]):#this is to check for anagrams
                file2.write(str([word,array1[i]])+'\n')      #this will write the information to an inputed file    
    file.close()
print("Operation Complete!!!")