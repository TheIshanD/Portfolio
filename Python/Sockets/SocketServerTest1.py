import socket
import sys

clientnumber=1
HOST = ''   # Symbolic name meaning all available interfaces
PORT = 8888 # Arbitrary non-privileged port
 
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print ('Socket created')
 
try:
    s.bind((HOST, PORT))
except (socket.error , msg):
    print ('Bind failed. Error Code : ' + str(msg[0]) + ' Message ' + msg[1])
    sys.exit()
print ('Socket bind complete')   


def clientThread(conn1):
    
    data = conn1.recv(1024)
 #   if not data:
  #      break  
    print("Client: " + data.decode())
    z=input("ME: ")
    conn1.sendall(z.encode())

for i in range(clientnumber):
    s.listen(10000000)
    print ('Server socket now listening '+str(i+1))
    conn, addr = s.accept()

conn.send("Hello from Server, Welcome to the chat.".encode())
print("Chat Commencing... Waiting for Client")
print("")
while True:
#    print ( ' Server Connected with ' + addr[0] + ':' + str(addr[1])
    clientThread(conn)
conn.close()


s.close()
