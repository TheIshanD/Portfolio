#handling errors in python socket programs
import time
from tkinter import *
import sys 
import socket   #for sockets
import sys  #for exit
root = Tk()
fmswobgernbonrb=0
def show():
    p = password.get() #get password from entry
    if p == "qwert":
        print("Accepted")
        print("")
        global fmswobgernbonrb
        fmswobgernbonrb=1
        root.destroy()
    else:
        print("Wrong Password-Try Again")
        print("")
        p = password.get()

  
password = StringVar() #Password variable
passEntry = Entry(root, textvariable=password, show='*').pack() 
submit = Button(root, text='Accept Password',command=show).pack()
root.mainloop()

#root.mainloop() 
if fmswobgernbonrb==1:
    try:
        #create an AF_INET, STREAM socket (TCP)
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    except (socket.error, "Failed to create socket"):
        sys.exit(); 
    print ('Socket Created')

    # Get IP address
    host = 'canyonvista.roundrockisd.org'
    host = '192.168.1.68'
    try:
        remote_ip = socket.gethostbyname( host )
    except socket.gaierror:
        print ('Hostname could not be resolved. Exiting')
        sys.exit()
         
    print ('Ip address of ' + host + ' is ' + remote_ip)


    #Connect to remote server  based on host and port
    port=8888
    s.connect((remote_ip , port)) 
    print ('Client Socket Connected to ' + host + ' on ip ' + remote_ip)
    print(" ")


    #Now receive data
    while True:
        reply = s.recv(4096)
        print("Server: "+ reply.decode())
        x=input("ME: ")
       # print("")
        s.sendall(x.encode())
    s.close()
else:
    print("You don't get to play.")
