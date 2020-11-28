import random

import time
print("Welocme to the CALCULATOR!")
print("")
time.sleep(1)
c=0
def exp(a,b):
   c=a**b
   print(c)
def div(a,b):
   c= a/b
   print(c)
def mult(a,b):
   c= a*b
   print(c)
def add(a,b):
   c= a+b
   print(c)
def sub(a,b):
   c= a-b
   print(c)
while True:
   x=input("What function do you need? (exp)(div)(mult)(add)(sub)? ")
   if x == "exp":
      y=input("What is the base? ")
      d=input("What is the exponent? ")
      print(exp(float(y),float(d)))
   if x == "div":
      y=input("What is the dividend? ")
      d=input("What is the divisor? ")
      print(div(int(y),int(d)))
   if x == "mult":
      y=input("What is the first term? ")
      d=input("What is the second term? ")
      print(mult(int(y),float(d)))
   if x == "add":
      y=input("What is the first term? ")
      d=input("What is the second term? ")
      print(add(int(y),int(d)))
   if x == "sub":
      y=input("What is the first term? ")
      d=input("What is the second term? ")
      print(sub(int(y),int(d)))

