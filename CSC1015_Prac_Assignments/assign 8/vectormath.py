#TOKELO MAKOLOANE
#22 MAY 2021
#to do basic vector calculations in 3 dimensions addition, dot product and normalization.

import math

Ax, Ay, Az = (input("Enter vector A:\n")).split()
Bx, By, Bz = (input("Enter vector B:\n")).split()

A = [(Ax), (Ay), (Az)]
B = [(Bx), (By), (Bz)]

Added_vectors = [eval(Ax)+eval(Bx),eval(Ay)+eval(By),eval(Az)+eval(Bz)]

dot_product = (eval(Ax)*eval(Bx))+(eval(Ay)*eval(By))+(eval(Az)*eval(Bz))

normA = math.sqrt(eval(Ax)**2 +eval(Ay)**2 +eval(Az)**2)

normB = math.sqrt(eval(Bx)**2 +eval(By)**2 +eval(Bz)**2)

print("A+B =",Added_vectors)
print("A.B =",dot_product)
print("|A| = {0:.2f}".format(normA))
print("|B| = {0:.2f}".format(normB))