#Tokelo Makoloane
#21 MAY 2021
#Program to print out values entered buy the user but not duplicates

print("Enter strings (end with DONE):")
word_list=[]
SET =set()
ordered_words=[]
words = input()
while words!='DONE':       #This will make sure after the user enter DONE the loop end
    word_list.append(words)
    words=input()
print()
print("Unique list:")
for index in (word_list):
    if index not in SET:
        SET.add(index)
        ordered_words.append(index)
for word in ordered_words:
    print(word)
        
    