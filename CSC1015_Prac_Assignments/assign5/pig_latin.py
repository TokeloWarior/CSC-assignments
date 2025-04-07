#Name: Tokelo Makoloane
#Student number: MKLTOK002
#Date: 17 April 2021

#A program that accepts a sentence as input and translates it to Pig Latin.

sentence  = input("Enter a sentence:\n")
sentence = sentence + " "
vowels = ('A','E','I','O','U','a','e','i','o','u')



index = 0
pig_latin=''
#Creating a loop where i can perform permutations on every word splitt(ed)
for length_sentence in range(len(sentence)):
    if sentence[length_sentence]==' ':
        split_sentence = sentence[index:length_sentence]
        index = length_sentence + 1
        if split_sentence[0] in vowels:              #condition if 1st letter of the word is a vowel
            pig_latin += split_sentence +'way' + " "
        else:
            position_consonants = 0                   #declares a variale for position of consonants 
            for consonants in split_sentence:
                if consonants not in vowels:
                    position_consonants += 1         #This will also include for consecutive position of the consonants in a word
                    continue                         #This means continue the iteration until now the are no position of consonants
                break                                #this means now if the position of the consonants are no longer existing break the iteration and print out the following
            pig_latin += split_sentence[position_consonants:] + "a" + split_sentence[:position_consonants] + "ay" + " "
print(pig_latin)  
#
#
#alternative method

#pig_latin = ""

#Creating a loop where i can perform permutations on every word splitt(ed)
#split_sentence = sentence.split(" ")
#for word in split_sentence:
#    if word[0] in vowels:                 #condition if 1st letter of the word is a vowel
#        pig_latin += word + 'way' + " " 
#    else:
#        position_consonants = 0           #declares a variale for position of consonants 
#        for consonants in word:
#            if consonants not in vowels:
#                position_consonants += 1  #This will also include for consecutive position of the consonants in a word
#                continue                  #This means continue the iteration until now the are no position of consonants
#            break                         #this means now if the position of the consonants are no longer existing break the iteration and print out the following
#        pig_latin += word[position_consonants:] + "a" + word[:position_consonants] + "ay" + " "
#print(pig_latin)  

  