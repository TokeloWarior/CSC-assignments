
#Transforming a date and time given in a compact 24-hour format to a long form 12-hour format.
x = input("Enter the date and time (yyyy-mm-dd hh:mm):\n")
time_suffix = ''
month_name = ''
day_suffix = ''

#slicing the string x into parts, and converting them from strings to integer
year = (str(x)[:4])
month = (str(x)[5:7])     
day = int(str(x)[8:10])
hours = (str(x)[11:13])
minutes = (str(x)[14:16])


#Making a condition for month date to output it's respective month name
if month=='01':
    month_name='January'
elif month=='02':
    month_name='February'
elif month=='03':
    month_name='March'
elif month=='04':
    month_name='April'
elif month=='05':
    month_name='May'
elif month=='06':
    month_name='June'
elif month=='07':
    month_name='July'
elif month=='08':
    month_name='August'
elif month=='09':
    month_name='September'
elif month=='10':
    month_name='October'
elif month=='11':
    month_name='November'
elif month=='12':
    month_name='December'

#Making a condition for which suffix if appropriate for a certain day   
if 4<= day <=20 or 24<= day <=30:
    day_suffix='th'
elif day==1 or day==21 or day==31:
    day_suffix='st'
elif day==2 or day==22:
    day_suffix='nd'
elif day==3 or day==23:
    day_suffix='rd'

#Making a condition for time whether it is (am/pm)    
if '01'<= hours <= '11':
    time_suffix='am'
elif hours =='12':
    time_suffix='pm'
    hours = '12'    
elif  hours =='13':
    time_suffix='pm'
    hours = '1'
elif hours =='14':
    time_suffix='pm'
    hours = '2'    
elif hours=='15':
    time_suffix='pm'
    hours = '3'
elif hours=='16':
    time_suffix='pm'
    hours = '4' 
elif hours=='17':
    time_suffix='pm'
    hours = '5'
elif hours=='18':
    time_suffix='pm'
    hours = '6'
elif hours=='19':
    time_suffix='pm'
    hours = '7' 
elif hours=='20':
    time_suffix='pm'
    hours = '8'
elif hours=='21':
    time_suffix='pm'
    hours = '9' 
elif hours=='22':
    time_suffix='pm'
    hours = '10'
elif hours=='23':
    time_suffix='pm'
    hours = '11'
elif hours=='00':
    time_suffix='am 21'
    hours = '12'    

print(int(hours),minutes,sep=':',end=' ')
print(time_suffix,' on the ',day,day_suffix,sep='',end=' ')
print('of',month_name,end=' ')
print("'",str(year)[2:4],sep='')