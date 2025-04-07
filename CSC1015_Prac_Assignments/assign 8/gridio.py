#Tokelo Makoloane
#21 MAY 2021
#A program that allows a user to input integer values and query a 2-dimensional array of size 9x9.

array=[]
print("Enter an array:")
while len(array)!=9:
    WORD=input()    
    LIST=list(WORD)
    array.append(LIST)
x, y=input("Enter coordinates:\n").split()   
while eval(x)!=-1 and eval(y)!=-1:      #This will make sure after the user enter -1 -1 as x and y the loop end
    value = array[eval(x)][eval(y)]
    print("Value =",value)
    x, y=input("Enter coordinates:\n").split()
else:
    print("DONE")