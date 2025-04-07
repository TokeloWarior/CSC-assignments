#TOKELO MAKOLOANE
#28-MAY-2021
#A program that can be used to determine if a given pattern matches a given word.

def match(pattern, word):
    #base case
    if len(pattern)==1:
        if pattern[0] == word[0]:
            return True
        else:
            if pattern[0]=="?":
                return True
        return False
    #base case
    elif len(pattern)==1:
            return False
    # Recursive case with a base case
    elif len(pattern)>len(word):
        if pattern[0] == word[0]:
            if pattern[-1]==word[-1]:
                if len(pattern[1:-1])==len(word[1:-1]):
                    return match(pattern, word)
                else:
                    return False
            else:
                return False
        else:
            return False
    # recursive case
    if pattern[0]=="?":
        pos =pattern.find("?")+1
        string_pp = pattern[pos:len(pattern)]
        string_ww = word[pos:len(word)]
        if string_pp==string_ww:
            return match(string_pp,string_ww)
        elif string_pp[-1]==string_ww[-1]:
            return match(string_pp,string_ww)
        else:
            if string_pp[0]==string_ww[0]:
                return match(string_pp,string_ww)
        return match(string_pp,string_ww)
    #recursive call 
    elif pattern[0]==word[0]:
        new_p = pattern[1:len(pattern)]
        new_w = word[1:len(word)]
        return match(new_p,new_w)
print(match('l???d','leead'))