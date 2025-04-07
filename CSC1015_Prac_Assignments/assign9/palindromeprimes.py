# TOKELO MAKOLOANE
# 31-MAY-2021
# A program to find all palindromic primes between two integers N, M, supplied as input

import sys
sys.setrecursionlimit (30000)

def prime(N,primes = 2):
    if N > 1:
        if N == 2:
            return True
        if N % primes != 0:
            return True
    else:
        return False
    return prime(N,primes + 1)
def palindrome(N,M):
    if prime(N) == True:
        if str(N) == str(N)[::-1]:
            print(N)
    if N > (M-1):
        return False
    return palindrome(N+1,M)

N = eval(input("Enter the starting point N:\n"))
M = eval(input("Enter the ending point M:\n"))
print("The palindromic primes are:")
palindrome(N,M)
  