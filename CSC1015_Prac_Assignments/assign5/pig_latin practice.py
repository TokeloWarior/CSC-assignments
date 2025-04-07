sentence = input("Enter a sentence:\n")
vowels = "AEIOUaeiou"

pig_latin = ""

#Creating a loop where i can perform permutations on every word splitt(ed)
split_sentence = sentence.split(" ")
for word in split_sentence:
    if word[0] in vowels:                 #condition if 1st letter of the word is a vowel
        pig_latin += word + 'way' + " " 
    else:
        position_consonants = 0           #declares a variale for position of consonants 
        for consonants in word:
            if consonants not in vowels:
                position_consonants += 1  #This will also include for consecutive position of the consonants in a word
                continue                  #This means continue the iteration until now the are no position of consonants
            break                         #this means now if the position of the consonants are no longer existing break the iteration and print out the following
        pig_latin += word[position_consonants:] + "a" + word[:position_consonants] + "ay" + " "
print(pig_latin)  

