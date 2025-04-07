#TOKELO MAKOLOANE
#22 MAY 2021
# to count the number of votes for each political party in an election.

print('Independent Electoral Commission')
print("-"*32)
print('Enter the names of parties (terminated by DONE):')

word_list=[]
votes = {}
words = input()
while words!='DONE':       #This will make sure after the user enter DONE the loop end
    word_list.append(words)
    words=input()

for inputs in sorted(word_list):
    if inputs in votes:
        votes[inputs] += 1
    else:
        votes[inputs] = 1
print()
print("Vote counts:")
for keys in votes:
    print("{0:<10} -".format(keys),votes[keys])



  
