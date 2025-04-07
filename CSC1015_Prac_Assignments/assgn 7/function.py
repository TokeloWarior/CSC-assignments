
#a program to draw a text-based graph of a mathematical function f(x)

import math                 #for using some math functions
    
variable = (input("Enter a function f(x):\n"))

if variable[:9]=='math.sqrt':
    variable='math.sqrt(abs(x))'                #is to make the sqrt to be defined for all column values
inputs = str(variable).replace('x', 'column')

for row in range(10,-11,-1):             #This means i want my function to increase from left to right....and is for the x-axis
    for column in range(-10,11):         #this is for the y-axis
         
        function = round(eval(inputs))          #the function to be defined as integer      
            
        if column == 0 and row ==0 and row != function :
            print("+",end='')
        elif row == 0 and row != function:
            print("-",end='')
        elif column == 0 and row != function:
            print("|",end='')
        elif row == function:
            print("o",end='')
        else:
            print(" ",end='')
    print()        

    




