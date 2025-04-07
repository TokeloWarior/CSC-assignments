# TOKELO MAKOLOANE
# 03-JULY-2021
#A program that can be used to determine if a given pattern matches a given word.

def match(pattern, word):
    #base case
    if len(pattern)==1 and len(word)!=1:
        if pattern[0] == "*" or pattern[0] =="?":
            return True
        else:
            return False
    elif pattern[0]=="*" and len(pattern)!=1:
        if pattern[0]=="*":
            index = pattern.find("*")+1            
            string_p = pattern[index:len(pattern)]
            if len(word)>=len(string_p):
                i = pattern[index]
                neo1 = word.find(i)
                string_w = word[neo1:len(word)]
                if string_p in string_w:
                    index = pattern.find("*")+1 
                    char_yy = pattern[index]
                    neo = word.find(char_yy)
                    string_p = pattern[index:len(pattern)]
                    i = word.find(string_p)
                    if i==-1:
                        return False
                    else:
                        string_w = word[i:len(word)]
                        return match(string_p,string_w)                    
                else:
                    return match(string_p,string_w) 
            else:
                return False
        else:
            string_p = pattern[index:len(pattern)]
            string_w = word[index:len(word)]
            return match(string_p,string_w)
    else:
        #recursive case
        if pattern[0]=="?":
            pos = pattern.find("?")+1
            string_pp = pattern[pos:len(pattern)]
            string_ww = word[pos:len(word)]
            return match(string_pp,string_ww)
        #base case
        elif len(pattern)==1 and len(word)==1:
            if pattern[0] == "*" or pattern[0] == "?" or pattern[0] == word[0]:
                
                return True
        
            else:
                return False
        elif len(pattern)==1 and len(word)!=1:
            if pattern[0] == "*" or pattern[0] =="?":
                return True
            else:
                return False
        else:
            #recursive case
            if pattern[0]==word[0]:
                new_p = pattern[1:len(pattern)]
                new_w = word[1:len(word)]
                return match(new_p,new_w)
            #base case
            elif len(pattern)==1:
                return False
